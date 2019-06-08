package Entity;

import Controller.ObjectController;
import UI.UIGame;
import org.lwjgl.util.Rectangle;

import java.util.ArrayList;

public abstract class GameObject {
    private boolean isDied;
    private static int Score = 0;
    private static ArrayList<ObjectController> objectControllers = new ArrayList<ObjectController>();
    private static ArrayList<UIGame> UIObjects = new ArrayList<UIGame>();
    private int row,column,width,height,x_pixel,y_pixel,multipleNumber=1;
    private int previousX_pixel,previousY_pixel;
    private int previousRow,previousColumn;
    public boolean collisedWithWall = false;
    public enum TYPE{
        GHOST,PAC,WALL,DOT,NORMAL,CHERRY;

    }

    private TYPE type;
    public GameObject(){}
    public GameObject(int row,int column,int width,int height,int x_pixel,int y_pixel,TYPE type){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
        this.x_pixel = x_pixel;
        this.y_pixel = y_pixel;
        this.type = type;
    }
    public static void addController(ObjectController controller){
        objectControllers.add(controller);
    }
    public static void addUI(UIGame ui){
        UIObjects.add(ui);
    }
    public static void notifyController(){
        for(ObjectController controller : objectControllers){
            controller.update();
        }

    }
    public static void notifyUI(){
        for( UIGame entity : UIObjects ){
            entity.render();
        }

    }
    public static ArrayList<ObjectController> getObjectControllers(){return objectControllers;}

    public static void setObjectControllers(ArrayList<ObjectController> objectControllers) {
        GameObject.objectControllers = objectControllers;
    }

    public static void setUIObjects(ArrayList<UIGame> UIObjects) {
        GameObject.UIObjects = UIObjects;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX_pixel(int x_pixel) {
        this.x_pixel = x_pixel;
    }

    public void setY_pixel(int y_pixel) {
        this.y_pixel = y_pixel;
    }

    public void setMultipleNumber(int multipleNumber) {
        this.multipleNumber = multipleNumber;
    }

    public static ArrayList<UIGame> getUIObjects() {
        return UIObjects;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX_pixel() {
        return x_pixel;
    }

    public int getY_pixel() {
        return y_pixel;
    }

    public int getMultipleNumber() {
        return multipleNumber;
    }
    public abstract Rectangle getRect();
    public TYPE getType() {
        return type;
    }
    public void setType(TYPE type) {
        this.type = type;
    }

    public void setPreviousY_pixel(int previousY_pixel) {
        this.previousY_pixel = previousY_pixel;
    }

    public void setPreviousX_pixel(int previousX_pixel) {
        this.previousX_pixel = previousX_pixel;
    }

    public int getPreviousY_pixel() {
        return previousY_pixel;
    }

    public int getPreviousX_pixel() {
        return previousX_pixel;
    }

    public void setPreviousRow(int previousRow) {
        this.previousRow = previousRow;
    }

    public void setPreviousColumn(int previousColumn) {
        this.previousColumn = previousColumn;
    }

    public int getPreviousRow() {
        return previousRow;
    }

    public int getPreviousColumn() {
        return previousColumn;
    }
    public static void setScore(int num){Score = num;}
    public static int getScore(){return Score;}

    public void setDied(boolean died) {
        isDied = died;
    }

    public boolean isDied() {
        return isDied;
    }
}
