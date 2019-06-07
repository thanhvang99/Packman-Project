package Entity;

import Controller.ButtonController;

import java.util.ArrayList;

public class Button {

    private int xPosition;
    private int yPosotion;
    private int height;
    private int width;

    private static ArrayList<ButtonController> listOfButton= new ArrayList<ButtonController>(); ;



    public Button (int x, int y, int width, int height){
        this.xPosition = x;
        this.yPosotion = y;
        this.width = width;
        this.height = height;
    }

    public void addButtonController(ButtonController controller){
        listOfButton.add(controller);
    }

    public static void notifyButtonController(){
        for (ButtonController controller : listOfButton){
            controller.update();
        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosotion() {
        return yPosotion;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
