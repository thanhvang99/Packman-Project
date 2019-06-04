import org.lwjgl.util.Color;

import javax.swing.*;

import static org.lwjgl.opengl.GL11.*;

public class UI {
    private int blockHeight;
    private int blockWidth;
    private int startX,startY;
    private Graph g;

    public UI(Graph g,int blockHeight,int blockWidth){
        this.g = g;
        this.blockHeight = blockHeight;
        this.blockWidth = blockWidth;
        startX = 50;
        startY = 50;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,1080,960,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
    }

    public void render(){
        Maze maze = g.getMaze();
        for(int i=0;i<maze.getRow();i++){
            for(int j=0;j<maze.getColumn();j++){
                if(maze.valueAt(i,j)==0){ // This is normal
                    glColor3f(0,0,1);
                    drawBlock(i,j);
                }else if(maze.valueAt(i,j)==1){ // This is wall
                    glColor3f(0,1,0);
                    drawBlock(i,j);
                }else{
                    glColor3f(1,0,0);
                    drawBlock(i,j);
                }
            }
        }
    }

    public void drawBlock(int row,int column){
        int x = startX + blockWidth*column;
        int y = startY + blockHeight*row;
        glRectf(x,y,x+blockWidth,y+blockHeight);
    }
}
