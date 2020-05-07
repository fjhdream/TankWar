package com.fjhdream.tank;


public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            frame.tankList.add(new Tank(200+i*100,200, Dir.LEFT, Group.BAD, frame));
        }
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
