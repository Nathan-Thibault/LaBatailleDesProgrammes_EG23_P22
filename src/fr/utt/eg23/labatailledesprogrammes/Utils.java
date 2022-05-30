package fr.utt.eg23.labatailledesprogrammes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public final class Utils {
    public static ImageIcon getImageToSize(String imageName, int width, int height) {
        try {
            BufferedImage buffImg = ImageIO.read(
                    Objects.requireNonNull(LaBatailleDesProgrammes.class.getResource("/resources/images/" + imageName)));
            Image img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
