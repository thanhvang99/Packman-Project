package Controller;

import Entity.Entity;

public class EntityController extends ObjectController{
    private Entity e;
    public EntityController(Entity e){
        super();
        this.e = e;
        e.addController(this);
    }
    @Override
    public void update() {

    }
}
