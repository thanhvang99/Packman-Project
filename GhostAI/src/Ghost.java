import edu.princeton.cs.algs4.Stack;

import static org.lwjgl.opengl.GL11.*;

public class Ghost {
    public enum DIRECTION{
        LEFT,RIGHT,UP,DOWN,STAND;
        private Animation left= new Animation(3,1,"ghost") ,
                right= new Animation(3,1,"ghost"),
                up= new Animation(3,1,"ghost"),
                down= new Animation(3,1,"ghost");

        public Animation getAnimation(){
            switch(this){
                case LEFT:
                    return left;
                case RIGHT:
                    return right;
                case UP:
                    return up;
                case DOWN:
                    return down;
                default:
                    return left;
            }
        }

    }
    private int row,column,width=30,height=30;
    private int x_pixel,y_pixel;
    private int speed;
    private DIRECTION direction;
    private Graph g;
    private Stack<Integer> path;
    private BreathFirstPath bfp;
    private boolean isUpdateShortestPath = true;
    public Ghost(int row,int column,DIRECTION direction,int speed,Graph g){
        this.g = g;
        this.row = row;
        this.column = column;
        this.speed = speed;
        this.direction = direction;
        x_pixel = width*column + Maze.startX_pixel;
        y_pixel = height*row + Maze.startY_pixel;

    }
    public void draw(){
        direction.getAnimation().bind();
        glBegin(GL_QUADS);
        glTexCoord2d(0,0);
        glVertex2i(x_pixel,y_pixel);
        glTexCoord2d(1,0);
        glVertex2i(x_pixel+width,y_pixel);
        glTexCoord2d(1,1);
        glVertex2i(x_pixel+width,y_pixel+height);
        glTexCoord2d(0,1);
        glVertex2i(x_pixel,y_pixel+height);
        glEnd();
    }
    public void update(){
        findShortestPath(0,g.getMaze().convertToIndex1D(9,11));
        move();
        updatePosition(x_pixel,y_pixel);

    }
    public void move(){
        switch(direction){
            case LEFT: x_pixel-=speed; break;
            case RIGHT: x_pixel+=speed; break;
            case UP: y_pixel -= speed; break;
            case DOWN: y_pixel += speed; break;
            default:
                // Nothing
        }

    }
    private void updatePosition(int x_pixel,int y_pixel){
        row = (int) Math.floor((y_pixel - Maze.startY_pixel)*1.0/35);
        column = (int) Math.floor((x_pixel - Maze.startX_pixel)*1.0/35);

    }
    private void findShortestPath(int source,int target){
        if(isUpdateShortestPath) {
            bfp = new BreathFirstPath(g, source);
            path = bfp.pathTo(target);
            isUpdateShortestPath = false;
        }
        if( !path.isEmpty()){
            int num = path.peek();
            int currentIndex = g.getMaze().convertToIndex1D(row,column);
            if(path.peek() == currentIndex){
                guessNextMove();
            }
        }
    }
    private void guessNextMove(){
        path.pop();
        if(!path.isEmpty()){
            int nextIndex = path.peek();
            int nextRow = g.getMaze().getRowFromIndex1D(nextIndex);
            int nextColumn = g.getMaze().getColumnFromIndex1D(nextIndex);
            if (nextRow > row) {
                direction = DIRECTION.DOWN;
            } else if (nextRow < row) {
                direction = DIRECTION.UP;
            } else if (nextColumn > column) {
                direction = DIRECTION.RIGHT;
            } else if (nextColumn < column) {
                direction = DIRECTION.LEFT;
            }
        }else{
            direction = DIRECTION.STAND;
        }

    }


}
