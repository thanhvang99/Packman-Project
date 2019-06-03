import static org.lwjgl.opengl.GL11.*;

public class PacMan {
    public enum DIRECTION{
        LEFT,
        RIGHT,
        UP,
        DOWN;
        private Animation left = new Animation(11,20,"pac_left"),
                          right = new Animation(11,20,"pac_right"),
                          up = new Animation(11,20,"pac_up"),
                          down = new Animation(11,20,"pac_down");
        @Override
        public String toString(){
            switch(this){
                case LEFT:
                    return "left";
                case RIGHT:
                    return "right";
                case UP:
                    return "up";
                case DOWN:
                    return "down";
                default:
                    return "Unvalid direction";
            }
        }
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
                    return null;
            }

        }
    }
    private DIRECTION direction;
    private int speed;

    private int x, y;

    public PacMan(int x,int y,DIRECTION direction){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;

    }
    public void setDirection(DIRECTION direction){
        this.direction = direction;
    }
    public void draw(){
        direction.getAnimation().bind();
        glBegin(GL_QUADS);
        glTexCoord2d(0,0);
        glVertex2i(x,y);
        glTexCoord2d(1,0);
        glVertex2i(x+50,y);
        glTexCoord2d(1,1);
        glVertex2i(x+50,y+50);
        glTexCoord2d(0,1);
        glVertex2i(x,y+50);
        glEnd();
    }
    public void moveX(int dx){x+=dx;}
    public void moveY(int dy){y+=dy;}
    public void move(){
        switch(direction.toString()){
            case "left":
                moveX(-speed);
                break;
            case "right":
                moveX(speed);
                break;
            case "up":
                moveY(-speed);
                break;
            case "down":
                moveY(speed);
                break;
            default:
                // nothing
        }
    }
    public void update(){
        move();
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }



}
