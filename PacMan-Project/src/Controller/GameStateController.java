package Controller;

import Entity.State;

public class GameStateController extends StateController {


    public GameStateController(State state) {
        super(state);
        setCurrentController(this);
    }

    @Override
    public void updateState() {

    }

    @Override
    public boolean checkInput() {
        return false;
    }

}
