import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Animation {
    private long lastTime,currentTime,delta;
    private int pointer;
    private int fps;
    private Texture[] frames;

    public Animation(int amountOfFrames,int fps,String key){
        lastTime = 0;
        currentTime = 0;
        delta = 0;
        pointer = 0;
        this.fps = fps;
        frames = new Texture[amountOfFrames];
        for(int i=0; i<amountOfFrames;i++){
            frames[i] = load(i+"_"+key,"gif");
        }
    }
    public void bind(){
        currentTime = getTime();
        delta += currentTime - lastTime;

        if(delta >= fps*1000){
            delta -= fps*1000;
            pointer++;
        }
        if(pointer >= frames.length) pointer =0;
        lastTime = currentTime;
        frames[pointer].bind();


    }
    private long getTime(){return Sys.getTime()*1000/Sys.getTimerResolution();}
    private Texture load(String key,String type){
        try {
            return TextureLoader.getTexture(type,new FileInputStream(new File("images/"+key+"."+type)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
