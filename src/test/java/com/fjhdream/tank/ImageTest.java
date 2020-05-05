package com.fjhdream.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    void test(){

        try {
            BufferedImage image = ImageIO.read(new File("/Users/carota/IdeaProjects/MyTank/src/main/resources/images/bulletD.gif"));
            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(
                    Objects.requireNonNull(ImageTest.class.getClassLoader()
                            .getResourceAsStream("images/bulletD.gif")));
            assertNotNull(image2);
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
