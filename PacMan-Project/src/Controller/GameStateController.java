package Controller;

import UI.UIEntity;

public class GameStateController extends StateController {

    private UIEntity uiEntity;

    public GameStateController(UIEntity uiEntiity) {
        super(uiEntiity);
//        setCurrentController(this);

    }

    @Override
    public void updateState() {

    }

    @Override
    public boolean checkInput() {
        return false;
    }

}
