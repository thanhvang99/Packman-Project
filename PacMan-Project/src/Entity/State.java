package Entity;

import Controller.StateController;
import UI.UIState;

public class State {

    private boolean isDisplaying;

    //    public static State currentState;
    public static StateController currentStateController;
    public static UIState currentUI;

//    private static List<UIState> listOfUIState;
//    private static List<StateController> listOfStateController;

    public boolean isDisplaying() {
        return isDisplaying;
    }

    public void setDisplaying(boolean displaying) {
        isDisplaying = displaying;
    }
}
