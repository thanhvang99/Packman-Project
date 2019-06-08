package Controller;

import Entity.State;
import UI.UIGameOver;

public class GameStateController extends StateController {

    private UIGameOver uiEntity;

    public GameStateController(UIGameOver uiEntiity) {
        super(uiEntiity);
        this.uiEntity = uiEntiity;
        State.setCurrentStateController(this);

    }

    @Override
    public void updateState() {
        double current = System.currentTimeMillis();
        if (System.currentTimeMillis()-current >= 5*1000){
            UIGameOver uiMenu = new UIGameOver(1,"gameover",1,"jpg");
            GameOverStateController menuController= new GameOverStateController(uiMenu);
        }

    }

    @Override
    public boolean checkInput() {
        return false;
    }

}
