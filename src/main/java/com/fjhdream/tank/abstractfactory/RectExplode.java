package com.fjhdream.tank.abstractfactory;

import com.fjhdream.tank.Audio;
import com.fjhdream.tank.ResourceMgr;
import com.fjhdream.tank.TankFrame;

import java.awt.*;

public class RectExplode  extends BaseExplode{
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private TankFrame tankFrame;
    private int x, y;

    //private boolean living = true;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }



    @Override
    public void paint(Graphics g) {

        Color c= g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;

        //g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if(step >= ResourceMgr.explodes.length)
            tankFrame.explodes.remove(this);
    }
}
