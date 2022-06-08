package fr.utt.eg23.labatailledesprogrammes;

import fr.utt.eg23.labatailledesprogrammes.card.GameCard;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.OpponentFighter;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;
import fr.utt.eg23.labatailledesprogrammes.screens.TitleScreen;
import fr.utt.eg23.labatailledesprogrammes.screens.TroopConfiguration;
import fr.utt.eg23.labatailledesprogrammes.screens.TroopPositioning;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LaBatailleDesProgrammes {

    private JFrame frame;

    private static LaBatailleDesProgrammes instance;

    public static final Color COLOR_BACKGROUND = new Color(0x373C48);
    public static final Color BLUE = new Color(0x636DD0);
    public static final Color ORANGE = new Color(0xC48C5D);
    public static final Color GREEN = new Color(0x6BCA63);
    public static final Color RED = new Color(0xC65165);
    public static final Color PINK = new Color(0xC688CF);

    public static final Font GAME_FONT = new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 15);
    public static final Dimension FRAME_SIZE = new Dimension(1280, 720);

    private TroopConfiguration troopConfiguration = null;
    private final Map<String, Set<GameCard>> playerTroops = new HashMap<>(5);
    private final Map<String, Map<FighterType, Integer>> opponentTroops = new HashMap<>(5);

    private UTTBranch branch = null;

    public static LaBatailleDesProgrammes getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                instance = new LaBatailleDesProgrammes();
                instance.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LaBatailleDesProgrammes() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("La Bataille des Programmes");
        frame.setSize(FRAME_SIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TitleScreen());
    }

    public void switchPanel(JPanel newPanel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newPanel);
        frame.repaint();
        frame.revalidate();
    }

    public TroopConfiguration getTroopConfiguration() {
        return troopConfiguration;
    }

    public void setTroopConfiguration(TroopConfiguration troopConfiguration) {
        this.troopConfiguration = troopConfiguration;
    }

    public UTTBranch getBranch() {
        return branch;
    }

    public void setBranch(UTTBranch branch) {
        this.branch = branch;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Map<String, Set<GameCard>> getPlayerTroops() {
        return playerTroops;
    }

    public Map<String, Map<FighterType, Integer>> getOpponentTroops() {
        return opponentTroops;
    }
}
