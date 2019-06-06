package Controller;

import App.GameService;
import Entity.Animation;
import org.lwjgl.Sys;

import java.util.ArrayList;

public class AnimationController extends ObjectController{
    private ArrayList<Animation> animations;

    public AnimationController(ArrayList<Animation> animations){
       this.animations = animations;
       for(Animation animation : animations) {
           animation.addController(this);
       }
    }

    @Override
    public void update() {
        bind();

    }
    private void bind(){
        for( Animation animation : animations ) {

            animation.setCurrentTime(GameService.getTimeInMillisecond());
            animation.setDelta((animation.getDelta()+(animation.getCurrentTime() - animation.getLastTime())));
            System.out.println(animation.getDelta());


            if (animation.getDelta() >= 1000/animation.getFps()) {
                animation.setDelta(animation.getDelta() - 1000/animation.getFps());
                animation.setPointer(animation.getPointer() + 1);
            }

            if (animation.getPointer() >= animation.getFrames().length) animation.setPointer(0);
            animation.setLastTime(animation.getCurrentTime());
        }
    }
}
