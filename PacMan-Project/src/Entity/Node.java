package Entity;

import App.GameService;
import Controller.AnimationController;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;


public class Node extends DrawableObject{
    // Default value for width and height = 30;
    // Default width and height;
    private Animation animation;

    public Node(int row,int column,TYPE type){
        super(row,column,30,30,type);

    }
    public Node(int row,int column,int valueFromText){
        super(row,column,30,30,valueFromText == 0 ? TYPE.DOT : TYPE.WALL);
        switch(getType()){
            case WALL: setAnimation("wall",1,1,"png");setMultipleNumber(3); break;
            case DOT: setAnimation("dot",1,1,"png");break;
        }
    }

    @Override
    public void setAnimation(String keyName, int numberOfFrames, int fps, String format) {
        animation = new Animation(numberOfFrames,fps,keyName,format);
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX_pixel(),getY_pixel(),getWidth(),getHeight());
    }
}

