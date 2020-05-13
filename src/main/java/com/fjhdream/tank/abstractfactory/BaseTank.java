package com.fjhdream.tank.abstractfactory;

import com.fjhdream.tank.Dir;
import com.fjhdream.tank.Group;

import java.awt.*;

public abstract class BaseTank {

    public abstract void paint(Graphics g);

    public abstract Group getGroup();

    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public abstract void fire();

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);
}
