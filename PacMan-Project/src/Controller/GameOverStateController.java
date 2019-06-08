package Controller;

import Entity.State;
import Helper.ButtonController;
import UI.UIGameOver;
import UI.UIMenu;

public class GameOverStateController extends StateController {
    UIGameOver uiGameOver;

    ButtonController playAgainController;

    public GameOverStateController(UIGameOver uiGameOver) {
        this.uiGameOver = uiGameOver;
        State.setCurrentStateController(this);
        init();
    }

    public void init(){
        playAgainController = new ButtonController(uiGameOver.getPlayAgainButton());
    }

    @Override
    public void updateState() {
        if (playAgainController.isClicked()){
            State.setCurrentStateController(new MenuStateController(new UIMenu(1,"menu",1,"jpg")));
        }
    }

    @Override
    public boolean checkInput() {
        return false;
    }
}
