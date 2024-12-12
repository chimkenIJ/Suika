import processing.core.PApplet;

public class Fruit {
    private double type;
    public float x, y;
    private boolean dropped;
    private int size;
    private String name;
    private static final double YSPEED = 1, GRAVITY = 0.5;
    private double damping = 0.33;
    private double speed;
    private boolean hit;

    public Fruit(double type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.dropped = false;
        this.hit = false;
        this.speed = YSPEED;
    }

    private void chooseType(PApplet game) {
        if (type < 0.2) {
            game.fill(255, 0, 0);
            size = 10;
            name = "Cherry";
        } else if (type < 0.4) {
            game.fill(100, 0, 0);
            size = 15;
            name = "Apple";
        } else if (type < 0.6) {
            game.fill(255, 0, 255);
            size = 25;
            name = "Grape";
        } else if (type < 0.8) {
            game.fill(0, 255, 0);
            size = 35;
            name = "Dekopon";
        } else if (type < 1) {
            game.fill(255, 255, 0);
            size = 50;
            name = "Persimmon";
        }
    }
    public boolean testmoveY() {
        int max = (600 - 30 - size / 2 - 7); //SCREENHEIGHT-BUFFER - radius - stroke weight
        if (!hit) {
            if (dropped) {
                //hit ground
                if (y < max) {
                    speed+=GRAVITY;
                    y += speed;
                    if (y + speed > max) {
                        y = max;
                        hit = true;
                    }
                }
            }
        } else {
            return true;
        }
        return y >= max;
    }

    public void moveX(PApplet game) {
//        if(!dropped && x<(600-30) && x>30) {
//            x = game.mouseX;
//        }
        if (!dropped) {
            x = game.mouseX;
        }

    }

    public void bounce() {
        int max = (600 - 30 - size / 2 - 7);

        speed += GRAVITY;


        y += speed;
        if (y >= max) {
            y = max;
            speed = -speed * damping;
        }
        if (speed == 0 && y < max) {
            speed+=GRAVITY;
        }
        if (Math.abs(speed) < 0.1) {
            speed = 0;
        }

    }

    public void drawFruit(PApplet game) {
        chooseType(game);
        game.strokeWeight(1);
        game.ellipse(x, y, size, size);
    }

    public void drop() {
        dropped = true;
    }

    public boolean isDropped() {
        return dropped;
    }

    public String getName() {
        if (name != null) return name;
        else return "";
    }

    public int getScore() {
        return size;
    }

    public void changeType() {
        type += 0.2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isTouching(Fruit other) {
        double distX = this.getX() - other.getX();
        double distY = this.getY() - other.getY();
        double centerDist = Math.sqrt(distX * distX + distY * distY);

        return Math.abs(centerDist - (this.getSize() + other.getSize())) < 1.0;

    }
}