package com.fjhdream.tank;


import com.fjhdream.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {


   public List<BaseBullet> bulletList = new ArrayList<>();
   public List<BaseTank> tankList = new ArrayList<>();
   public List<BaseExplode> explodes = new ArrayList<>();

   public GameFactory gameFactory = new DefaultFactory();
   BaseTank myTank = gameFactory.createTank(200,400, Dir.DOWN, Group.GOOD,this);

   public static  final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public  TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MykeyListener());
    }

    class MykeyListener extends KeyAdapter{


        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            myTank.setMoving(true);

            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);

            }
    }




    }

    //双缓冲防止闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_WIDTH);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }



    @Override
    public void paint(Graphics g) {

        Color c= g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹:"+bulletList.size(),10,60);
        g.drawString("敌人:"+tankList.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++) {
                bulletList.get(i).collidewith(tankList.get(j));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }


}
