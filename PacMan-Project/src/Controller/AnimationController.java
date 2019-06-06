package Controller;

import Entity.Animation;

public class AnimationController extends ObjectController{
    private Animation animation;

    public AnimationController(Animation animation){
       this.animation = animation;
       animation.add(this);
    }

    @Override
    public void update() {


    }
    private void bind(){


    }
}
