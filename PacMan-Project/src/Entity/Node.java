package Entity;

import App.GameService;
import Controller.AnimationController;
import org.newdawn.slick.opengl.Texture;

public class Node extends DrawableObject{
    // Default value for width and height = 30;
    public enum TYPE{
        WALL,DOT;
    }
    // Default width and height;
    private TYPE type;
    private Animation animation;

    public Node(int row,int column,TYPE type){
        super(row,column,30,30);
        this.type = type;

    }
    public Node(int row,int column,int valueFromText){
        super(row,column,30,30);
        switch(valueFromText){
            case 1: type = TYPE.WALL; setAnimation("wall",1,1,"png");setMultipleNumber(3); break;
            case 0: type = TYPE.DOT; setAnimation("dot",1,1,"png");break;
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

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}

