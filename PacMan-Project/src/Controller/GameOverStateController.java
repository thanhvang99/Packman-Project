package Controller;

import Entity.State;

public class GameOverStateController extends StateController {

    public GameOverStateController(State state) {
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
