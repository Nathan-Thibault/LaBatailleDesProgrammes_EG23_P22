package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class OpponentFighter extends JPanel {
    public static final Dimension DEFAULT_SIZE = new Dimension(60, 75);

    private final FighterType fType;

    public OpponentFighter(FighterType fType) {
        this.fType = fType;
        setPreferredSize(DEFAULT_SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        //life bar
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0, 0, getWidth() - 1, 10);
        Random r = new Random();
        int max = getWidth() - 2;
        int life = r.nextInt(max);
        if (life <= max / 4)
            g2d.setColor(Color.RED);
        else if (life <= 3 * max / 4)
            g2d.setColor(Color.ORANGE);
        else
            g2d.setColor(Color.GREEN);
        g2d.fillRect(1, 1, life, 9);

        //shape
        if (fType == FighterType.SOLDIER) {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 15, (int) getSize().getWidth(), (int) getSize().getHeight() - 15);
        } else {
            g2d.setColor(fType.getColor());
            g2d.fillRect(0, 15, (int) getSize().getWidth(), (int) getSize().getHeight() - 15);
            try {
                BufferedImage buffImg = ImageIO.read(Objects.requireNonNull(
                        LaBatailleDesProgrammes.class.getResource("/resources/images/star.png")));
                //resize
                int size = (int) (getSize().getWidth() * 0.7);
                int position = (int) ((getSize().getWidth() - size) / 2f);
                buffImg = Utils.resize(buffImg, size, size);

                g2d.drawImage(buffImg, null, position, position + 15);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        g2d.dispose();
    }
}
