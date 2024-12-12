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
            game.fill(255, 165, 0);
            size = 50;
            name = "Persimmon";
        }else if (type < 1.2) {
            game.fill(50, 0, 0);
            size = 75;
            name = "Strawberry";
        }else if (type < 1.4) {
            game.fill(0, 100, 0);
            size = 85;
            name = "Pear";
        }else if (type < 1.6) {
            game.fill(255, 255, 0);
            size = 100;
            name = "Pineapple";
        }else if (type < 1.8) {
            game.fill(0, 255, 100);
            size = 115;
            name = "Melon";
        }else if (type < 2.0) {
            game.fill(0, 255, 150);
            size = 125;
            name = "Watermelon";
        }
    }

    public boolean testmoveY() {
        int max = (600 - 30 - size / 2 - 7); //SCREENHEIGHT-BUFFER - radius - stroke weight
        if (!hit) {
            if (dropped) {
                //hit ground
                if (y < max) {
                    speed += GRAVITY;
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
        double max = 600 - 30 - size / (double) 2;
        double min = 30 + size / (double) 2;

        if (!dropped) {
            if (game.mouseX <= max && game.mouseX >= min) {
                x = game.mouseX;
            }
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
            speed += GRAVITY;
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

        return centerDist<=(2*this.size);

    }
}
