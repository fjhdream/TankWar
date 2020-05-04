package com.fjhdream.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }
    }
}
