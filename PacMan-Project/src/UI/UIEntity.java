package UI;


import Entity.Animation;
import Entity.Entity;
import Entity.Ghost;
import Entity.PacMan;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_QUADS;

public class UIEntity {
    private ArrayList<Entity> entities;
    private Graph graph;

    public UIEntity(ArrayList<Entity> entities){
        super();
        this.entities = entities;
        for( Entity e : entities ){
            e.addUI(this);
        }

    }
    public void render(){
        for ( Entity e : entities ){
            Animation animation = e.getAnimation();
            animation.getFrames()[animation.getPointer()].bind();
            draw(e);
        }

    }
     private void draw(Entity e){
        int x_pixel = e.getColumn() * 30;
        int y_pixel = e.getHeight() * 30;
        int width = e.getWidth();
        int height = e.getHeight();
        glBegin(GL_QUADS);
        glTexCoord2d(0, 0);
        glVertex2i(x_pixel, y_pixel);
        glTexCoord2d(1, 0);
        glVertex2i(x_pixel + width, y_pixel);
        glTexCoord2d(1, 1);
        glVertex2i(x_pixel + width, y_pixel + height);
        glTexCoord2d(0, 1);
        glVertex2i(x_pixel, y_pixel + height);
        glEnd();
    }
}
