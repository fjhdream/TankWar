package com.fjhdream.tank;

public class DefaultFireStartegy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT;

        new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.tankFrame);
    }
}
