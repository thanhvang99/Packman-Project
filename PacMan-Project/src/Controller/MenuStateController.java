package Controller;

import Entity.State;

public class MenuStateController extends StateController {

    public MenuStateController(State state) {
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
