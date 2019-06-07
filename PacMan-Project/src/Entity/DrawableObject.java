package Entity;

import org.lwjgl.util.Rectangle;

public abstract class DrawableObject extends GameObject {
    private int row,column;
    private int width,height;
    private int x_pixel,y_pixel;
    public DrawableObject(int row,int column,int width,int height,GameObject.TYPE type){
        super(row,column,width,height,column*30,row*30,type);

    }
    public abstract void setAnimation(String keyName,int numberOfFrames,int fps,String format);
    public abstract Animation getAnimation();

}
