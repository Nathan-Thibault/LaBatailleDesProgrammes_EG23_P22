package fr.utt.eg23.labatailledesprogrammes.card;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A {@link GameCard} without all its {@link fr.utt.eg23.labatailledesprogrammes.fighter.PropertyModifier}
 * and dropdown to choose strategy.
 */
public class MinimizedCard extends OtherCardForm {

    public static final Dimension SIZE = new Dimension((int) GameCard.SIZE.getWidth(),
            (int) GameCard.TITLE_FONT_SIZE + GameCard.ICON_SIZE + 10);

    public MinimizedCard(GameCard original) {
        super(original);

        String iconFileName = "icon_" + original.getBranch().getFileNameAffix() + "_" + original.getFighterType().getFileNameAffix() + ".png";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GameCard.BACKGROUND_COLOR);
        setPreferredSize(SIZE);
        setMinimumSize(SIZE);
        setMaximumSize(SIZE);
        setBorder(new LineBorder(original.getFighterType().getColor(), 3));

        JLabel title = new JLabel(original.getFighterType().toString());
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(GameCard.TITLE_FONT_SIZE));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JLabel icon = new JLabel();
        icon.setIcon(Utils.getImageToSize(iconFileName, GameCard.ICON_SIZE, GameCard.ICON_SIZE));
        icon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(icon);
    }
}
