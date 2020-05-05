package com.fjhdream.tank;

import java.awt.*;



public class Bullet {
    private static final int SPEED = 20;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

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

        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y, null);
                break;
            default:
                break;
        }

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

    public void collidewith(Tank tank) {
        Rectangle rectangle1 = new Rectangle(this.x,this.y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectangle1.intersects(rectangle2)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.live = false;
    }

}
