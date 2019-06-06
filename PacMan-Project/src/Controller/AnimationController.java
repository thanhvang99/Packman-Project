package Controller;

import App.GameService;
import Entity.Animation;

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
            long currentTime = animation.getCurrentTime();
            long lastTime = animation.getLastTime();
            long delta = animation.getDelta();

            animation.setCurrentTime(GameService.getTimeInMillisecond());
            animation.setDelta(delta + (currentTime - lastTime));

            if (animation.getDelta() >= animation.getFps() * 1000) {
                animation.setDelta(animation.getDelta() - animation.getFps() * 1000);
                animation.setPointer(animation.getPointer() + 1);
            }

            if (animation.getPointer() >= animation.getFrames().length) animation.setPointer(0);
            animation.setLastTime(animation.getCurrentTime());
        }
    }
}
