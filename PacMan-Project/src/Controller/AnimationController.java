package Controller;

import Entity.Animation;

import java.util.ArrayList;

public class AnimationController extends ObjectController{

    private ArrayList<Animation> animations;

    public AnimationController(ArrayList<Animation> animations) {
       this.animations = animations;
       for(Animation animation: animations) {
           animation.addController(this);
       }
    }

    @Override
    public void update() {
        bind();
    }

    private void bind() {


    }
}
