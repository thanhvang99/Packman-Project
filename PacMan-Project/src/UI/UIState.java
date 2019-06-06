package UI;

import Entity.MenuState;
import Entity.State;

public class UIState {
    private State state;

    public UIState(State state){
        this.state = state;
        state.add(this);
    }
}
