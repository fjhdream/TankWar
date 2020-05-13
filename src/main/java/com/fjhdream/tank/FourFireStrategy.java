package com.fjhdream.tank;

public class FourFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT;

        Dir[] dirs = Dir.values();
        for (Dir dir :
                dirs) {
            new Bullet(bX, bY, dir, tank.getGroup(), tank.tankFrame);
        }
    }
}
