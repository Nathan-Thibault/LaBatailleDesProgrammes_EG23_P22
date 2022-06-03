package fr.utt.eg23.labatailledesprogrammes.fighter;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.CustomProgressBar;
import fr.utt.eg23.labatailledesprogrammes.screens.TroopConfiguration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PropertyModifier extends JPanel {

    public PropertyModifier(FighterProperty property, FighterType fighterType) {
        int min = property.getMin(fighterType);
        int max = property.getMax();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GameCard.BACKGROUND_COLOR);

        JLabel title = new JLabel(property.toString());
        float fontSize = (float) (GameCard.SIZE.getHeight() * 0.03);
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(GameCard.BACKGROUND_COLOR);
        actionPanel.setBorder(new EmptyBorder(0, 2, 0, 0));
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        add(actionPanel);

        CustomProgressBar pb = new CustomProgressBar();
        pb.setMinimum(0);
        pb.setMaximum(max);
        pb.setValue(min);
        pb.setProgressColor(property.getColor());
        pb.setBorder(new LineBorder(Color.BLACK));
        pb.setForeground(Color.BLACK);
        actionPanel.add(pb);

        TextButton buttonLess = new TextButton("-", 10f, () -> {
            if (pb.getValue() > min) {
                pb.setValue(pb.getValue() - 1);
                TroopConfiguration tc = LaBatailleDesProgrammes.getInstance().getTroopConfiguration();
                if (tc != null)
                    tc.addToPoints(+1);
            }
        });
        buttonLess.setBorder(new EmptyBorder(5, 5, 5, 3));
        buttonLess.setForeground(Color.BLACK);
        actionPanel.add(buttonLess);

        TextButton buttonMore = new TextButton("+", 10f, () -> {
            if (pb.getValue() < max) {
                pb.setValue(pb.getValue() + 1);
                TroopConfiguration tc = LaBatailleDesProgrammes.getInstance().getTroopConfiguration();
                if (tc != null)
                    tc.addToPoints(-1);
            }
        });
        buttonMore.setBorder(new EmptyBorder(5, 3, 5, 5));
        buttonMore.setForeground(Color.BLACK);
        actionPanel.add(buttonMore);
    }
}
