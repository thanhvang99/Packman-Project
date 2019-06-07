package Entity;

import Controller.ObjectController;
import UI.UIEntity;

import java.util.ArrayList;

public abstract class Game {
    private static ArrayList<ObjectController> objectControllers = new ArrayList<ObjectController>();
    private static ArrayList<UIEntity> UIObjects = new ArrayList<UIEntity>();
    public Game(){
    }
    public void addController(ObjectController controller){
        objectControllers.add(controller);
    }
    public void addUI(UIEntity ui){
        UIObjects.add(ui);
    }
    public static void notifyController(){
        for(ObjectController controller : objectControllers){
            controller.update();
        }

    }
    public static void notifyUI(){
        for( UIEntity entity : UIObjects ){
            entity.render();
        }

    }
    public static ArrayList<ObjectController> getObjectControllers(){return objectControllers;}
}
