package Entity;

import Controller.ObjectController;

import java.util.ArrayList;

public abstract class Game {
    private static ArrayList<ObjectController> objectControllers;
    public void add(ObjectController object){
        objectControllers.add(object);
    }
}
