package fr.utt.eg23.labatailledesprogrammes.card;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DotCard extends OtherCardForm {
    public static final Dimension DEFAULT_SIZE = new Dimension(50, 50);

    private final FighterType fType;

    public DotCard(GameCard original) {
        super(original);

        fType = original.getFighterType();
        setPreferredSize(DEFAULT_SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        if (fType == FighterType.SOLDIER) {
            g2d.setColor(Color.WHITE);
            g2d.fill(new Ellipse2D.Double(0, 0, getSize().getWidth(), getSize().getHeight()));
        } else {
            g2d.setColor(fType.getColor());
            g2d.fill(new Ellipse2D.Double(0, 0, getSize().getWidth(), getSize().getHeight()));
            try {
                BufferedImage buffImg = ImageIO.read(Objects.requireNonNull(
                        LaBatailleDesProgrammes.class.getResource("/resources/images/star.png")));
                //resize
                int size = (int) (getSize().getWidth() * 0.7);
                int position = (int) ((getSize().getWidth() - size) / 2f);
                buffImg = Utils.resize(buffImg, size, size);

                g2d.drawImage(buffImg, null, position, position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        g2d.dispose();
    }
}
