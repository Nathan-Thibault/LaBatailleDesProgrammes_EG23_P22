package fr.utt.eg23.labatailledesprogrammes.card;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.draganddrop.DragGestureHandler;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterProperty;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;
import fr.utt.eg23.labatailledesprogrammes.fighter.PropertyModifier;

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
    public static final float TITLE_FONT_SIZE = (float) (SIZE.getHeight() * 0.04);
    public static final int ICON_SIZE = (int) (SIZE.getHeight() * 0.25);

    private final UTTBranch branch;
    private final FighterType fType;

    private final HashSet<PropertyModifier> properties = new HashSet<>(FighterProperty.values().length);
    private final JComboBox<String> strategyComboBox;
    private final JLabel strategy = new JLabel();

    private MinimizedCard minimizedThis = null;

    public GameCard(UTTBranch branch, FighterType fType) {
        this.branch = branch;
        this.fType = fType;

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
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(TITLE_FONT_SIZE));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JLabel icon = new JLabel();
        icon.setIcon(Utils.getImageToSize(iconFileName, ICON_SIZE, ICON_SIZE));
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
        float fontSize = (float) (SIZE.getHeight() * 0.03);

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

    public UTTBranch getBranch() {
        return branch;
    }

    public FighterType getFighterType() {
        return fType;
    }

    public MinimizedCard getMinimized() {
        return this.minimizedThis;
    }

    public void setMinimized(MinimizedCard minimizedCard) {
        this.minimizedThis = minimizedCard;
    }
}
