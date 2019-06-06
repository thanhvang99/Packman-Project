package Controller;

import Entity.State;

public class StateController {
    private State state;

    public StateController(State state){
        this.state = state;
        state.add(this);
    }
}
