package fr.utt.eg23.labatailledesprogrammes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public final class Utils {
    /**
     * Gets an {@link ImageIcon} to the given size for the given image file name.
     *
     * @param imageName name of the file of the image
     * @param width     width of the icon wanted
     * @param height    height of the icon wanted
     * @return the ImageIcon for the given image file name to the given size
     */
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

    /**
     * Gets a {@link BufferedImage} to the given size from the given {@link BufferedImage}.
     *
     * @param img       image to resize
     * @param newWidth  wanted width
     * @param newHeight wanted height
     * @return BufferedImage resized to the given width and height
     */
    public static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage bufImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufImg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return bufImg;
    }
}
