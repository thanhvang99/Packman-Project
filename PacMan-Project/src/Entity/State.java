package Entity;

import Controller.StateController;
import UI.UIState;

public abstract class State {
    private Animation background;

    //    public static State currentState;
    private static StateController currentStateController;
    private static UIState currentUI;

//    private static List<UIState> listOfUIState;
//    private static List<StateController> listOfStateController;



    public static  StateController getCurrentStateController() {
        return currentStateController;
    }

    public static UIState getCurrentUI() {
        return currentUI;
    }

    public void setCurrentStateController(StateController currentStateController) {
        State.currentStateController = currentStateController;
    }

    public static void setCurrentUI(UIState currentUI) {
        State.currentUI = currentUI;
    }

    public static void notifyUI(){
        currentUI.render();
    }

    public boolean isOn(){
        return false;
    }
}
