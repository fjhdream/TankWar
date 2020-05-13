package com.fjhdream.tank.abstractfactory;

import com.fjhdream.tank.Dir;
import com.fjhdream.tank.Group;
import com.fjhdream.tank.TankFrame;

public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new RectTank(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);

    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, Group group, TankFrame tankFrame) {
        return new RectBullet(x, y, dir, group, tankFrame);
    }
}
