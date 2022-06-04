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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public class GameCard extends JPanel implements Serializable {
    public static final Color BACKGROUND_COLOR = new Color(194, 194, 200);
    public static final Dimension SIZE = new Dimension(180, 340);
    public static final float TITLE_FONT_SIZE = (float) (SIZE.getHeight() * 0.04);
    public static final int ICON_SIZE = (int) (SIZE.getHeight() * 0.25);
    public static final Dimension MINIMIZED_SIZE = new Dimension((int) SIZE.getWidth(), (int) TITLE_FONT_SIZE + ICON_SIZE + 10);

    private final UTTBranch branch;
    private final FighterType fType;
    private final GameCard original;

    private final HashSet<PropertyModifier> properties = new HashSet<>(FighterProperty.values().length);
    private final JComboBox<String> strategyComboBox;
    private final JLabel strategy = new JLabel();
    private final JLabel strategyLabel;
    private final JSeparator line;

    private JFrame f;
    private final MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (getOriginalGameCard().f != null) return;

            f = new JFrame();
            f.setSize(SIZE);
            f.setAlwaysOnTop(true);
            f.setResizable(false);
            f.setUndecorated(true);
            f.setLocationRelativeTo(getOriginalGameCard());
            GameCard copy = new GameCard(getOriginalGameCard());
            f.getContentPane().add(copy);
            getOriginalGameCard().removeMouseListener(this);
            copy.addMouseListener(this);
            f.setVisible(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            getOriginalGameCard().f.dispose();
            getOriginalGameCard().f = null;
            getOriginalGameCard().addMouseListener(this);
        }
    };

    private boolean minimized = false;

    public GameCard(UTTBranch branch, FighterType fType) {
        this.original = null;
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
        line = new JSeparator();
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

        strategyLabel = new JLabel("Stratégie");
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

    public GameCard(GameCard toCopy) {
        this.original = toCopy;
        this.branch = toCopy.branch;
        this.fType = toCopy.fType;
        this.minimized = false;

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
        line = new JSeparator();
        line.setForeground(Color.BLACK);
        line.setBackground(null);
        add(line);

        for (PropertyModifier pm : toCopy.properties) {
            properties.add(pm);
            add(pm);
        }

        //Strategy
        float fontSize = (float) (SIZE.getHeight() * 0.03);

        strategyLabel = new JLabel("Stratégie");
        strategyLabel.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        strategyLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(strategyLabel);

        strategy.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(Font.BOLD, fontSize * 1.2f));
        strategy.setAlignmentX(Component.CENTER_ALIGNMENT);
        strategy.setText((String) toCopy.strategyComboBox.getSelectedItem());
        add(strategy);

        strategyComboBox = null;
    }

    public void setModifiable(boolean modifiable) {
        if (minimized) return;

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

    public void setMinimized(boolean minimized) {
        if (this.minimized == minimized) return;

        if (minimized) {
            setModifiable(false);
            setPreferredSize(MINIMIZED_SIZE);
            setMinimumSize(MINIMIZED_SIZE);
            setMaximumSize(MINIMIZED_SIZE);

            for (PropertyModifier pm : properties) {
                remove(pm);
            }
            remove(strategyLabel);
            remove(strategy);
            remove(line);

            addMouseListener(mouseListener);
        } else {
            setPreferredSize(SIZE);
            setMinimumSize(SIZE);
            setMaximumSize(SIZE);

            add(line);
            for (PropertyModifier pm : properties) {
                add(pm);
            }
            add(strategyLabel);
            add(strategy);

            removeMouseListener(mouseListener);
        }

        revalidate();
        repaint();

        this.minimized = minimized;
    }

    private GameCard getOriginalGameCard() {
        return Objects.requireNonNullElse(original, this);
    }
}
