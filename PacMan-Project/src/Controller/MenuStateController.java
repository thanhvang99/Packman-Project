package Controller;

import Entity.GameState;
import Entity.State;
import org.lwjgl.input.Mouse;

public class MenuStateController extends StateController {

    public MenuStateController(State state) {
        super(state);
        setCurrentController(this);
    }

    @Override
    public void updateState() {
        if (checkInput()){
            StateController gameStateController = new GameOverStateController(new GameState());
        }
    }

    @Override
    public boolean checkInput() {
//  Example
        if (state.isDisplaying()) {
            if (Mouse.isButtonDown(1))
                if (Mouse.getX() >= 20 && Mouse.getX() <= 60)
                    if (Mouse.getY() >= 20 && Mouse.getY() <= 60)
                        return true;
        }
        return false;
    }
}
