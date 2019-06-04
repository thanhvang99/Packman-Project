import edu.princeton.cs.algs4.In;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.opengl.GL11.*;

public class Maze {
    // 1 --> Wall, 0 --> Normal, 2 --> Moved
    public enum TYPE{
        WALL,DOT,NORMAL;
        private Texture wall= load("wall","png"),
                dot= load("dot","png"),
        normal= null;
        public static TYPE toType(int num){
            switch(num){
                case 1: return WALL;
                case 0: return DOT;
                default: return NORMAL;
            }
        }
        public Texture getTexture(){
            switch(this){
                case WALL: return wall;
                case DOT: return dot;
                default: return normal;
            }
        }
        public int getWidth(){
            switch(this){
                case WALL:
                case DOT:
                case NORMAL:
                    return 35;
                default:
                    return 0;

            }
        }
        public int getHeight(){
            switch(this){
                case WALL:
                case DOT:
                case NORMAL:
                    return 35;
                default:
                    return 0;

            }
        }
    }
    public static final int startX_pixel = 20,startY_pixel = 20;
    private TYPE[][] array;
    private int row;
    private int column;
    public Maze(In in){
        column = in.readInt();
        row = in.readInt();
        array = new TYPE[row][column];
        buildMaze(in);
    }
    private void buildMaze(In in){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                array[i][j] = TYPE.toType(in.readInt());
            }
        }
    }

    public int convertToIndex1D(int currentRow,int currentColumn){
        return currentRow*column + currentColumn;
    }
    public int getRowFromIndex1D(int index){return (int) Math.floor(index/column);}
    public int getColumnFromIndex1D(int index){return (index-(int)(Math.floor(index/column)*column));}

    public void display(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                System.out.print(array[i][j]+ " ");
            }
            System.out.println();
        }

    }
    public int getRow(){return row;}
    public int getColumn(){return column;}
    public TYPE[][] getMap(){return array;}
    public TYPE valueAt(int row,int column){return array[row][column];}
    public void updateValueAt(int row,int column,TYPE type){
        array[row][column] = type;

    }
    public static Texture load(String key,String type){
        try {
            return TextureLoader.getTexture(type,new FileInputStream(new File("images/"+key+"."+type)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void draw(int row,int column){
        TYPE type = array[row][column];
        int x = type.getWidth()*column + startX_pixel;
        int y = type.getHeight()*row + startY_pixel;
        switch(type){

            case WALL:
                type.getTexture().bind();
                glBegin(GL_QUADS);
                glTexCoord2d(0,0);
                glVertex2i(x,y);
                glTexCoord2d(3,0);
                glVertex2i(x+type.getWidth(),y);
                glTexCoord2d(3,3);
                glVertex2i(x+type.getWidth(),y+type.getHeight());
                glTexCoord2d(0,3);
                glVertex2i(x,y+type.getHeight());
                glEnd();
                break;
            case DOT:
                type.getTexture().bind();
                drawCircleOutline(x+20,y+20,5);
                break;
            case NORMAL:
                break;
        }

    }
    void drawCircleOutline(int x_pos,int y_pos,int r)
    {
        float angle, radian, x, y,tx,ty,xcos,ysin;       // values needed by drawCircleOutline

        glBegin(GL_POLYGON);

        for (angle=0.0f; angle<360.0; angle+=2.0)
        {
            radian = (float) (angle * (Math.PI/180.0f));

            xcos = (float)cos(radian);
            ysin = (float)sin(radian);

            x = xcos * r  + x_pos;
            y = ysin * r  + y_pos;

            tx = (float) (xcos * 0.5 + 0.5);
            ty = (float) (ysin * 0.5 + 0.5);

            glTexCoord2f(tx, ty);
            glVertex2f(x, y);
        }

        glEnd();
    }



}
