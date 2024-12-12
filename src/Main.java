import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BUFFER = 30;

    private ArrayList<Fruit> fruits = new ArrayList<>();

    private int frames = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int score = 0;
    private boolean gameOver = false;
    private boolean drop = false;
    private double currObj = Math.random();
    private Fruit next;

    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void setup() {
        Fruit newFruit = new Fruit(currObj, mouseX, BUFFER + 30);
        currObj = Math.random();
        next = new Fruit(currObj, 500, BUFFER + 30);
        fruits.add(newFruit);

    }

    public void draw() {

        background(100);

        //Box
        strokeWeight(7);
        line(BUFFER, BUFFER, BUFFER, SCREEN_HEIGHT - BUFFER);
        line(SCREEN_WIDTH - BUFFER, BUFFER, SCREEN_WIDTH - BUFFER, SCREEN_HEIGHT - BUFFER);
        line(BUFFER, SCREEN_HEIGHT - BUFFER, SCREEN_WIDTH - BUFFER, SCREEN_HEIGHT - BUFFER);


        //Timer
        frames++;
        seconds = frames / 60;
        if (seconds == 60) {
            frames = 0;
            seconds = 0;
            minutes++;
        }

        fill(0);
        text("Score:" + score, 30, 30);
        text(minutes + ":" + ((seconds) / 10) + "" + seconds % 10, 270, 30);

        //Spawn Fruit

        if (fruits.get(fruits.size() - 1).isDropped()) {
            Fruit newFruit = new Fruit(currObj, mouseX, BUFFER + 30);
            currObj = Math.random();
            next = new Fruit(currObj, 500, BUFFER + 30);
            fruits.add(newFruit);
        }
        next.drawFruit(this);
        //Fruit Mechanics
        for (Fruit fruit : fruits) {

            //Move fruit
            fruit.drawFruit(this);
            fruit.moveX(this);
            if (fruit.testmoveY()) {
                fruit.bounce();
            }

            //Drop current fruit
            if (drop) {
                fruits.get(fruits.size() - 1).drop();
                drop = false;
            }
        }

        //Seeing if scored a point
        if (fruits.size() > 1) {
            for (int i = 0; i < fruits.size(); i++) {
                for (int j = 0; j < fruits.size(); j++) {
                    if(i==j) break;
                    Fruit fruitA = fruits.get(i);
                    Fruit fruitB = fruits.get(j);
                    if (fruitA.isDropped() && fruitB.isDropped()) {
                        if (fruitA.isTouching(fruitB) && (fruitA.getName().equals(fruitB.getName()))) {
                            System.out.println(fruitA.getName());
                            score += fruitA.getScore();
                            fruitB.changeType();
                            fruits.remove(fruitA);
                        }
                    }
                }
            }
        }
    }

    public void mousePressed() {
        drop = true;
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
