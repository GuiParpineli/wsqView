package org.example;

import org.jnbis.api.model.Bitmap;
import org.jnbis.internal.WsqDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WSQtoPNGConverter {

    public void convert(String wsqFilePath, String pngFilePath) {
        try {
            byte[] wsqData = Files.readAllBytes(new File(wsqFilePath).toPath());

            Bitmap bitmap = new WsqDecoder().decode(wsqData);

            BufferedImage bufferedImage = new BufferedImage(bitmap.getWidth(), bitmap.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bitmap.getHeight(); y++) {
                for (int x = 0; x < bitmap.getWidth(); x++) {
                    int value = bitmap.getPixels()[y * bitmap.getWidth() + x] & 0xff;
                    Color color = new Color(value, value, value);
                    bufferedImage.setRGB(x, y, color.getRGB());
                }
            }
            ImageIO.write(bufferedImage, "PNG", new File(pngFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}