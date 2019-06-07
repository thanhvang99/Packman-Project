package Controller;


import Entity.Game;

public abstract class EntityController extends ObjectController{
    private Game g;
    public EntityController(Game g){
        this.g = g;
        g.addController(this);
    }
    public abstract void move();
}
