import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Animation {
    private int pointer = 0;
    private Texture[] frames;
    private double fps;
    private long lastTime ;
    private long currentTime = 0;
    private long delta = 0;

    public Animation(){};
    public Animation(int amountFrames,int fps,String key){
        this.fps = fps;
        frames = new Texture[amountFrames];

        for(int i=0; i < amountFrames;i++){
            frames[i] = load(i+"_"+key);
        }
        lastTime = getTime();
    }
    public void bind(){
        currentTime = getTime();
        delta += currentTime - lastTime;

        if(delta >= 1000/fps){

            delta -= 1000/fps ;
            pointer++;
        }
        if(pointer >= frames.length)    pointer=0;
        lastTime = getTime();
        frames[pointer].bind();
    }
    public Texture load(String key){
        try {
            return TextureLoader.getTexture("GIF",new FileInputStream(new File("images/"+key+".gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public long getTime(){
        return (Sys.getTime()*1000)/Sys.getTimerResolution();
    }

}
