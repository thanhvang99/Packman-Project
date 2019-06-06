package App;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GameService {
    public static Texture loadTexture(String key, String format){
        try {
            return TextureLoader.getTexture(format,new FileInputStream(new File("images/"+key+"."+format)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static long getTimeInMillisecond(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
