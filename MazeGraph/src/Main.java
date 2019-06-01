import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    private static long lastFrame;
    public static void main(String[] args){
        init();
        run();

    }
    public static void init(){
        try {
            Display.setDisplayMode(new DisplayMode(1080,960));
            Display.setTitle("Graph");
            Display.create();
        } catch (Exception e) {
            e.printStackTrace();
            Display.destroy();
        }
    }
    public static void run(){
        Maze maze = new Maze(new In("maze.txt"));
        Graph g = new Graph(maze);
        UI ui = new UI(g,40,45);
        BreathFirstPath bfp = new BreathFirstPath(g,0);
        Stack<Integer> path = bfp.pathTo(maze.convertToIndex1D(21,16));
        long delta = 0;
        lastFrame = getTime();
        while(!Display.isCloseRequested()){
            long currentTime = getTime();
            delta += (currentTime - lastFrame);
            lastFrame = getTime();
            if(delta >= 500 && !path.isEmpty()){
                int number = path.pop();
                int row = maze.getRowFromIndex1D(number);
                int column = maze.getColumnFromIndex1D(number);
                maze.updateValueAt(row,column,3);
                delta =0;
            }
            glClear(GL_COLOR_BUFFER_BIT);
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                Display.destroy();
                System.exit(1);
            }

            ui.render();
            Display.update();
            Display.sync(60);
        }

    }
    public static long getTime(){
        return (Sys.getTime()*1000)/Sys.getTimerResolution();
    }
}
