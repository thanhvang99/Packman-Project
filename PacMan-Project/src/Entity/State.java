package Entity;

import Controller.StateController;
import UI.UIState;

public abstract class State {
    private Animation background;

    private static StateController currentStateController;
    private static UIState currentUI;

    public static StateController getCurrentStateController() {
        return currentStateController;
    }

    public static UIState getCurrentUI() {
        return currentUI;
    }

    public static void setCurrentStateController(StateController currentStateController) {
        State.currentStateController = currentStateController;
    }

    public static void setCurrentUI(UIState currentUI) {
        State.currentUI = currentUI;
    }

    public static void notifyUI(){
        currentUI.render();
    }

    public static void notifyStateController(){
        currentStateController.updateState();
    }

    public boolean isOn(){
        return false;
    }
}
