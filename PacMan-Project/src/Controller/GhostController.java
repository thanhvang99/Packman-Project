package Controller;


import Entity.GameObject;
import Entity.Ghost;

import java.util.ArrayList;

public class GhostController extends EntityController{
    private ArrayList<Ghost> list;

    public GhostController(ArrayList<Ghost> list){
        this.list = list;
        GameObject.addController(this);

    }
    @Override
    public void move() {

    }
    @Override
    public void update() {

    }
}
