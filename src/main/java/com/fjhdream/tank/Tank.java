package com.fjhdream.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    private Dir dir;
    private boolean aliving;

    private Group group = Group.BAD;

    private static final int SPEED = 5;
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = true;

    private final TankFrame tankFrame;
    private Random random = new Random();

    public Tank(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.aliving = true;
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        System.out.println();
        if (!aliving) {
            tankFrame.tankList.remove(this);
            return;
        }

        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
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
        if (random.nextInt(10) > 5) {
            this.fire();
        }
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT;
        tankFrame.bulletList.add(new Bullet(bX, bY, this.dir, this.group, tankFrame));
    }

    public void die() {
        this.aliving = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
