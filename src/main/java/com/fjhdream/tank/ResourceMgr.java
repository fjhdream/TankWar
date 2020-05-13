package com.fjhdream.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    private static class ResourceMgrHolder {
        private static ResourceMgr Instance  = new ResourceMgr();
    }

    public ResourceMgr getInstance() {
        return ResourceMgrHolder.Instance;
    }
    private ResourceMgr(){}

    public static BufferedImage tankGoodL, tankGoodU, tankGoodD, tankGoodR ;
    public static BufferedImage bulletL, bulletU, bulletD, bulletR;

    public static BufferedImage tankBadU, tankBadD,tankBadL,tankBadR;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {

            System.out.println(ResourceMgr.class.getClassLoader().getResource("."));
            tankGoodU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            tankGoodR = ImageUtil.rotateImage(tankGoodU,90);
            tankGoodL = ImageUtil.rotateImage(tankGoodU,-90);
            tankGoodD = ImageUtil.rotateImage(tankGoodU, 180);
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            tankBadU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankBadD = ImageUtil.rotateImage(tankBadU,180);
            tankBadL = ImageUtil.rotateImage(tankBadU,270);
            tankBadR = ImageUtil.rotateImage(tankBadU,90);

            for (int i = 1; i <= 16; i++) {
                explodes[i-1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + i + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
