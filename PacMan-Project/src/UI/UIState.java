package UI;

import Entity.State;

public class UIState {
    protected State state;

    public UIState(State state) {
        this.state = state;

    }

    public void setUIState(UIState uiState){
        State.currentUI = uiState;
    }
}
