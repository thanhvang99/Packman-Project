package Controller;

import Entity.State;

public abstract class StateController {

    protected State state;

    public StateController(State state) {
        this.state = state;
    }

    public abstract void updateState();

    public abstract boolean checkInput();

    public void setCurrentController(StateController stateController) {
        State.currentStateController = stateController;
    }
}
