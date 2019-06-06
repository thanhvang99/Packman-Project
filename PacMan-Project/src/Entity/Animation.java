package Entity;

import App.GameService;
import org.newdawn.slick.opengl.Texture;
public class Animation extends Game {
    private long currentTime;
    private long lastTime;
    private long delta;
    private int pointer;
    private Texture[] frames;
    private int fps;
    public Animation(int amountOfFrames,int fps,String key,String format){
        currentTime = 0;
        lastTime = 0;
        delta = 0;
        this.fps = fps;
        frames = new Texture[amountOfFrames];
        for(int i=0;i<amountOfFrames;i++){
            frames[i] = GameService.loadTexture(i+"_"+key,format);
        }

    }
    public void setFrames(Texture[] frames) {
        this.frames = frames;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public Texture[] getFrames() {
        return frames;
    }

    public int getPointer() {
        return pointer;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public long getDelta() {
        return delta;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }
}
