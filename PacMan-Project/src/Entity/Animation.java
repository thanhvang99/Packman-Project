package Entity;

import App.GameService;
import org.newdawn.slick.opengl.Texture;
public class Animation extends Game {
    private long currentTime;
    private long lastTime;
    private long delta;
    private Texture[] frames;
    public Animation(int amountOfFrames,String key,String format){
        currentTime = 0;
        lastTime = 0;
        delta = 0;
        frames = new Texture[amountOfFrames];
        for(int i=0;i<amountOfFrames;i++){
            frames[i] = GameService.loadTexture(key,format);
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

    private int pointer;

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

    public void setDelta(long delta) {
        this.delta = delta;
    }
}
