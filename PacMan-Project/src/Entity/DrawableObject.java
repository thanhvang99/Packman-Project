package Entity;

public abstract class DrawableObject extends Game {
    private int multipleNumber = 1;
    private int row,column;
    private int width,height;
    public DrawableObject(int row,int column,int width,int height){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public abstract void setAnimation(String keyName,int numberOfFrames,int fps,String format);
    public abstract Animation getAnimation();

    public int getMultipleNumber() {
        return multipleNumber;
    }

    public void setMultipleNumber(int multipleNumber) {
        this.multipleNumber = multipleNumber;
    }
}
