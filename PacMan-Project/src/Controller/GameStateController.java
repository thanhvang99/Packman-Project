package Controller;

import Entity.State;
import UI.UIGame;

public class GameStateController extends StateController {

    private UIGame uiEntity;

    public GameStateController(UIGame uiEntiity) {
        super(uiEntiity);
        this.uiEntity = uiEntiity;
        State.setCurrentStateController(this);

    }

    @Override
    public void updateState() {

    }

    @Override
    public boolean checkInput() {
        return false;
    }

}
