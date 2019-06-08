package Controller;

import Entity.*;
import Helper.ButtonController;
import UI.UIGame;
import UI.UIMenu;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;

public class MenuStateController extends StateController {

    private UIMenu uiMenuState;

    ButtonController startController;
    ButtonController exitController;

    public MenuStateController(UIMenu uiState) {

        super(uiState);
        this.uiMenuState = uiState;
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
            ArrayList<Ghost> listOfGhosts = new ArrayList<Ghost>();

            Entity pac = new PacMan(3,3,"pac",11,22,"gif");
            Entity ghost = new Ghost(0,4,"ghost",3,3,"gif");
            Entity ghost1 = new Ghost(19,0,"ghost",3,3,"gif");
            pac.setSpeed(2);
            ghost.setSpeed(1);
            ghost1.setSpeed(1);


            listOfGhosts.add((Ghost)ghost);
            listOfGhosts.add((Ghost)ghost1);

            Graph graph = new Graph("maze.txt");



            State.setCurrentUI(new UIGame(pac,listOfGhosts,graph));


            new PacController(pac);
            new GraphController(graph,listOfGhosts,pac);
            new GhostController(listOfGhosts,graph);

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
