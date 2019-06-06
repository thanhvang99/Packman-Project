package Entity;

import Controller.StateController;
import UI.UIState;

import java.util.List;

public class State {

    private boolean isDisplaying;
    private static List<UIState> listOfUIState;
    private static List<StateController> listOfStateController;

    public void add(UIState uiState){
        listOfUIState.add(uiState);
    }

    public void add(StateController stateController){
        listOfStateController.add(stateController);
    }
}
