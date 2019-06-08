package Controller;

import Entity.*;
import UI.UIGame;
import UI.UIGameOver;

import java.util.ArrayList;

public class GameStateController extends StateController {
    private Entity pacman;

    public GameStateController() {
        State.setCurrentStateController(this);
        UIGameOver uiGameOver = new UIGameOver(1, "gameover", 1, "jpg");
//            GameOverStateController gameOverController = new GameOverStateController(uiGameOver);
        ArrayList<Ghost> listOfGhosts = new ArrayList<Ghost>();

        this.pacman = new PacMan(3, 3, "pac", 11, 22, "gif");
        Entity ghost = new Ghost(0, 4, "ghost", 3, 3, "gif");
        Entity ghost1 = new Ghost(19, 3, "ghost", 3, 3, "gif");
        Entity ghost2 = new Ghost(21, 16, "ghost", 3, 3, "gif");
        Entity ghost3 = new Ghost(8, 8, "ghost", 3, 3, "gif");
        pacman.setSpeed(2);
        ghost.setSpeed(1);
        ghost1.setSpeed(1);
        ghost2.setSpeed(2);


        listOfGhosts.add((Ghost) ghost);
        listOfGhosts.add((Ghost) ghost1);
        listOfGhosts.add((Ghost) ghost2);
        listOfGhosts.add((Ghost) ghost3);

        Graph graph = new Graph("maze.txt");


        State.setCurrentUI(new UIGame(pacman, listOfGhosts, graph));

        new PacController(pacman);
        new GraphController(graph, listOfGhosts, pacman);
        new GhostController(listOfGhosts);
    }

    @Override
    public void updateState() {
        if (!pacman.isDied()) {
            GameObject.notifyUI();
            GameObject.notifyController();
        } else {
            GameObject.removeAll();
            State.setCurrentStateController(new GameOverStateController(new UIGameOver(1, "gameover", 1, "jpg")));
        }
    }




    @Override
    public boolean checkInput() {
        return false;
    }

}
