import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class UI {
    private Maze maze;
    private int blockHeight;
    private int blockWidth;
    private int startX,startY;
    private Graph g;
    private Ghost ghost;

    public UI(Graph g,int blockHeight,int blockWidth){
        this.g = g;
        this.blockHeight = blockHeight;
        this.blockWidth = blockWidth;
        startX = 20;
        startY = 20;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,650,820,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        ghost = new Ghost(0,0,Ghost.DIRECTION.LEFT,1,g);

    }
    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        glEnable(GL_TEXTURE_2D);
         maze = g.getMaze();
        for(int i=0;i<maze.getRow();i++){
            for(int j=0;j<maze.getColumn();j++){
                maze.draw(i,j);
            }
        }
        ghost.draw();
    }
    public void update(){
        ghost.update();

    }
}
