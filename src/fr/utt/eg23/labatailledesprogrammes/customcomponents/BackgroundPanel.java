package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Allows to put an image as the background of a panel.
 */
public class BackgroundPanel extends JPanel {

    private Image img;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (img != null)
            g.drawImage(img, 0, 0, null);
    }

    public void setBackground(String imageFileName) {
        try {
            BufferedImage buffImg = ImageIO.read(Objects.requireNonNull(
                    LaBatailleDesProgrammes.class.getResource("/resources/images/backgrounds/" + imageFileName)));
            this.img = buffImg.getScaledInstance((int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth(),
                    (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            this.img = null;
        }
    }
}
