package fr.utt.eg23.labatailledesprogrammes.fighter;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.draganddrop.DragGestureHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.util.HashSet;

public class GameCard extends JPanel {
    public static final Color BACKGROUND_COLOR = new Color(194, 194, 200);
    public static final Dimension SIZE = new Dimension(180, 340);

    private final HashSet<PropertyModifier> properties = new HashSet<>(FighterProperty.values().length);
    private final JComboBox<String> strategyComboBox;
    private final JLabel strategy = new JLabel();

    public GameCard(UTTBranch branch, FighterType fType) {
        //make draggable
        DragGestureHandler dragGestureHandler = new DragGestureHandler(this);
        DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler);

        String iconFileName = "icon_" + branch.getFileNameAffix() + "_" + fType.getFileNameAffix() + ".png";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(SIZE);
        setMinimumSize(SIZE);
        setMaximumSize(SIZE);
        setBorder(new LineBorder(fType.getColor(), 3));

        JLabel title = new JLabel(fType.toString());
        float fontSize = (float) (SIZE.getHeight() * 0.04);
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JLabel icon = new JLabel();
        int iconSize = (int) (SIZE.getHeight() * 0.25);
        icon.setIcon(Utils.getImageToSize(iconFileName, iconSize, iconSize));
        icon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(icon);

        //Properties
        JSeparator line = new JSeparator();
        line.setForeground(Color.BLACK);
        line.setBackground(null);
        add(line);

        for (FighterProperty p : FighterProperty.values()) {
            PropertyModifier pm = new PropertyModifier(p, fType);
            properties.add(pm);
            add(pm);
        }

        //Strategy
        fontSize = (float) (SIZE.getHeight() * 0.03);

        JLabel strategyLabel = new JLabel("Stratégie");
        strategyLabel.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        strategyLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(strategyLabel);

        strategyComboBox = new JComboBox<>(new String[]{"Défensif", "Offensif", "Aléatoire"});
        strategyComboBox.setEditable(false);
        strategyComboBox.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(Font.BOLD, fontSize));
        strategyComboBox.setBackground(BACKGROUND_COLOR);
        strategyComboBox.setBorder(new EmptyBorder(0, 20, 0, 20));
        strategyComboBox.setSelectedIndex(2);
        add(strategyComboBox);

        strategy.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(Font.BOLD, fontSize * 1.2f));
        strategy.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void setModifiable(boolean modifiable) {
        for (PropertyModifier pm : properties) {
            pm.setModifiable(modifiable);
        }

        if (modifiable) {
            remove(strategy);
            add(strategyComboBox);
        } else {
            strategy.setText((String) strategyComboBox.getSelectedItem());

            remove(strategyComboBox);
            add(strategy);
        }

        revalidate();
        repaint();
    }
}
