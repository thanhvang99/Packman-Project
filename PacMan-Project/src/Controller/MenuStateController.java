package Controller;

import Entity.State;
import Helper.ButtonController;
import UI.UIMenu;
import org.lwjgl.opengl.Display;

public class MenuStateController extends StateController {

    private UIMenu uiMenuState;

    ButtonController startController;
    ButtonController exitController;

    public MenuStateController(UIMenu uiMenuState) {
        this.uiMenuState = uiMenuState;
        State.setCurrentStateController(this);
        init();
    }

    public void init() {
        startController = new ButtonController(uiMenuState.getStart());
        exitController = new ButtonController((uiMenuState.getExit()));
    }

    @Override
    public void updateState() {
        if (startController.isClicked()) {
//            UIGameOver uiGameOver = new UIGameOver(1, "gameover", 1, "jpg");
//            GameOverStateController gameOverController = new GameOverStateController(uiGameOver);
            State.setCurrentStateController(new GameStateController());


        }
        if (exitController.isClicked()) {
            Display.destroy();
            System.exit(1);
        }
    }

    @Override
    public boolean checkInput() {
        return false;
    }
}
