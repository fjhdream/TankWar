package com.fjhdream.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ResourceMgrTest {

    public static BufferedImage[] explodes = new BufferedImage[16];

    @Test
    void  explodesNotNull() {
        try {
            for (int i = 1; i <= 16; i++) {
                explodes[i-1] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + i + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 16; i++) {
            assertNotNull(explodes[i]);
        }
    }
}
