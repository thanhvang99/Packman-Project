package Entity;

import Controller.StateController;

public class PacMan extends Entity{
    private static PacMan pac;
    public PacMan(int row, int column, String keyName, int numberOfFrame, int fps, String format) {
        super(row, column, keyName, numberOfFrame, fps, format);
    }
}


