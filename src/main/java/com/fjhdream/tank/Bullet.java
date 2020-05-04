package com.fjhdream.tank;

import java.awt.*;



public class Bullet {
    private static final int SPEED = 20;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private int x, y;
    private Dir dir;

    private TankFrame tankFrame;

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {

        if (!live) {
            tankFrame.bulletList.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH, HEIGHT);
        g.setColor(c);

        move();


    }

    private void move() {

        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y+= SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }

        if ( x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            this.live = false;
        }
    }

}
