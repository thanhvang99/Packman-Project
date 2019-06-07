package Controller;

import Entity.State;
import UI.UIState;

public abstract class StateController {


    public StateController(UIState uiState) {
        State.setCurrentUI(uiState);
    }

    public abstract void updateState();

    public abstract boolean checkInput();

}
