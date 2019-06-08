package UI;


import Entity.*;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.opengl.GL11.*;
import static org.newdawn.slick.opengl.renderer.SGL.GL_QUADS;

public class UIEntity extends UIState{
    private ArrayList<Ghost> listGhosts;
    private Graph graph;
    private Entity pac;


    public UIEntity(Entity pac,ArrayList<Ghost> listGhosts, Graph graph){
        super();
        this.graph = graph;
        this.listGhosts = listGhosts;
        this.pac = pac;

        GameObject.addUI(this);

    }
    public void render(){
        // Render entities
        for (  Ghost g : listGhosts ){
            Animation animation = g.getAnimation();
            animation.getFrames()[animation.getPointer()].bind();
            draw(g);
        }


        // Render Graph
        Node[][] nodes = graph.getNodes();
        for(int i=0;i<graph.getRow();i++){
            for(int j=0;j<graph.getColumn();j++) {
                Animation animation = nodes[i][j].getAnimation();
                animation.getFrames()[animation.getPointer()].bind();
                if (nodes[i][j].getType() == Node.TYPE.DOT) drawCircleOutline(nodes[i][j]);
                else if( nodes[i][j].getType() == GameObject.TYPE.WALL ) draw(nodes[i][j]);
            }
        }
        // Render Pac
        Animation animation = pac.getAnimation();
        animation.getFrames()[animation.getPointer()].bind();
        draw(pac);
    }
     private void draw(DrawableObject e){
        int x_pixel = e.getColumn() * 30;
        int y_pixel = e.getRow() * 30;
        int width = e.getWidth();
        int height = e.getHeight();
        int multipleNumber = e.getMultipleNumber();
        glBegin(GL_QUADS);
        glTexCoord2d(0, 0);
        glVertex2i(x_pixel, y_pixel);
        glTexCoord2d(multipleNumber, 0);
        glVertex2i(x_pixel + width, y_pixel);
        glTexCoord2d(multipleNumber, multipleNumber);
        glVertex2i(x_pixel + width, y_pixel + height);
        glTexCoord2d(0, multipleNumber);
        glVertex2i(x_pixel, y_pixel + height);
        glEnd();
    }
    private void drawCircleOutline(DrawableObject e)

    {
        int x_pixel = e.getColumn() * 30 + 20;
        int y_pixel = e.getRow() * 30 + 20;
        int width = e.getWidth();
        int height = e.getHeight();
        int r = 5;
        float angle, radian, x, y,tx,ty,xcos,ysin;       // values needed by drawCircleOutline

        glBegin(GL_POLYGON);

        for (angle=0.0f; angle<360.0; angle+=2.0)
        {
            radian = (float) (angle * (Math.PI/180.0f));

            xcos = (float)cos(radian);
            ysin = (float)sin(radian);

            x = xcos * r  + x_pixel;
            y = ysin * r  + y_pixel;

            tx = (float) (xcos * 0.5 + 0.5);
            ty = (float) (ysin * 0.5 + 0.5);

            glTexCoord2f(tx, ty);
            glVertex2f(x, y);
        }

        glEnd();
    }
}
