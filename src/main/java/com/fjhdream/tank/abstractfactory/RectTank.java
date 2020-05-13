package com.fjhdream.tank.abstractfactory;

import com.fjhdream.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    private int x;
    private int y;
    private Dir dir;
    private boolean aliving;

    private Group group = Group.BAD;

    private Rectangle tankRect = new Rectangle();


    private FireStrategy fireStrategy = new DefaultFireStartegy();
    private static final int SPEED = 5;
    public static final int WIDTH = ResourceMgr.tankGoodU.getWidth();
    public static final int HEIGHT = ResourceMgr.tankBadU.getHeight();

    private boolean moving = true;

    public final TankFrame tankFrame;
    private Random random = new Random();

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.aliving = true;
        this.group = group;

        tankRect.x = x;
        tankRect.y = y;
        tankRect.width = RectTank.WIDTH;
        tankRect.height = RectTank.HEIGHT;

        if (this.group == Group.GOOD) {
            String fs = (String) PropertyMgr.get("goodfs");
            try {
                this.fireStrategy = (FireStrategy) Class.forName(fs).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
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
        if (!aliving) {
            tankFrame.tankList.remove(this);
            return;
        }
        g.setColor(group == Group.BAD ? Color.WHITE : Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
        move();

        boundsCheck();

        tankRect.x = this.x;
        tankRect.y = this.y;
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
        if ( this.group == Group.BAD && random.nextInt(10) > 5) {
            this.fire();
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y=30;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH -2) x = TankFrame.GAME_WIDTH - RectTank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2) y =TankFrame.GAME_HEIGHT - RectTank.HEIGHT  -2;
    }

    public void fire() {
        //fireStrategy.fire(this);
        int bX = this.getX() + RectTank.WIDTH/2 - Bullet.WIDTH;
        int bY = this.getY() + RectTank.HEIGHT/2 - Bullet.HEIGHT;

        tankFrame.gameFactory.createBullet(bX, bY, this.getDir(), this.getGroup(), this.tankFrame);
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
