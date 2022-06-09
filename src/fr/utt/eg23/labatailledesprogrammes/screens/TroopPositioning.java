package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.card.*;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.*;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Screen to position troops on battle areas.
 * Cards are drag from the inventory bar on the bottom and drop to an area in the corresponding bubble.
 *
 * @see CardDropPanel
 */
public class TroopPositioning extends BackgroundPanel {

    private static final int areaWidth = 145;
    private static final int areaHeight = 80;

    public TroopPositioning(Set<GameCard> cardSet) {
        setLayout(null);
        setBackground("troop_positioning_bg.png");

        JPanel orangeArea = new CardDropPanel(20, CardForm.DOT);
        orangeArea.setBackground(LaBatailleDesProgrammes.ORANGE);
        orangeArea.setBounds(188, 35, areaWidth, areaHeight);
        add(orangeArea);

        JPanel greenArea = new CardDropPanel(20, CardForm.DOT);
        greenArea.setBackground(LaBatailleDesProgrammes.GREEN);
        greenArea.setBounds(573, 111, areaWidth, areaHeight);
        add(greenArea);

        JPanel redArea = new CardDropPanel(20, CardForm.DOT);
        redArea.setBackground(LaBatailleDesProgrammes.RED);
        redArea.setBounds(858, 170, areaWidth, areaHeight);
        add(redArea);

        JPanel pinkArea = new CardDropPanel(20, CardForm.DOT);
        pinkArea.setBackground(LaBatailleDesProgrammes.PINK);
        pinkArea.setBounds(973, 398, areaWidth, areaHeight);
        add(pinkArea);

        JPanel blueArea = new CardDropPanel(20, CardForm.DOT);
        blueArea.setBackground(LaBatailleDesProgrammes.BLUE);
        blueArea.setBounds(387, 430, areaWidth, areaHeight);
        add(blueArea);

        JPanel timePanel = new TimerDisplay(120, null);

        ReadyPanel readyPanel = new ReadyPanel(() -> {
            Map<String, Set<GameCard>> playerTroops = LaBatailleDesProgrammes.getInstance().getPlayerTroops();
            playerTroops.put("red", new HashSet<>());
            playerTroops.put("blue", new HashSet<>());
            playerTroops.put("orange", new HashSet<>());
            playerTroops.put("green", new HashSet<>());
            playerTroops.put("pink", new HashSet<>());

            //save troop positioning in main class hashmap
            for (String cName : playerTroops.keySet()) {
                JPanel areaPanel = switch (cName) {
                    case "red" -> redArea;
                    case "blue" -> blueArea;
                    case "orange" -> orangeArea;
                    case "green" -> greenArea;
                    case "pink" -> pinkArea;
                    default -> throw new IllegalArgumentException("cName unrecognized");
                };

                for (Component c : areaPanel.getComponents()) {
                    playerTroops.get(cName).add(((OtherCardForm) c).getOriginal());
                }
            }

            //mock opponent troop positioning
            Map<String, Map<FighterType, Integer>> opponentTroops = LaBatailleDesProgrammes.getInstance().getOpponentTroops();
            opponentTroops.put("red", Map.of(FighterType.MASTER_OF_WAR, 1, FighterType.SOLDIER, 4));
            opponentTroops.put("green", Map.of(FighterType.ELITE_SOLDIER, 1, FighterType.SOLDIER, 2));
            opponentTroops.put("pink", Map.of(FighterType.ELITE_SOLDIER, 1, FighterType.SOLDIER, 2));
            opponentTroops.put("orange", Map.of(FighterType.SOLDIER, 2));
            opponentTroops.put("blue", Map.of(FighterType.SOLDIER, 2));

            LaBatailleDesProgrammes.getInstance().switchPanel(new BattleVisualisation());
        });
        readyPanel.setOpponentReady(true);

        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.Y_AXIS));
        infosPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        infosPanel.add(timePanel);
        infosPanel.add(readyPanel);
        infosPanel.setBounds(5, 200, 320, 250);
        add(infosPanel);

        JLabel title = new DefaultLabel("Phase de déploiement des troupes", 30f);
        title.setBounds(450, 5, 600, 40);
        add(title);

        JPanel cardsPanel = new CardDropPanel(20, CardForm.MINIMIZED);
        cardsPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        cardsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 3));
        for (GameCard gc : cardSet) {
            gc.setModifiable(false);
            cardsPanel.add(new MinimizedCard(gc));
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        int height = (int) MinimizedCard.SIZE.getHeight() + 20;
        scrollPane.setBounds(0, (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight() - height - 40,
                (int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth() - 17, height);
        add(scrollPane);

        JLabel hint = new JLabel("  (Glisser/déposer les cartes dans les bulles colorées.)");
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint.setForeground(Color.GRAY);
        hint.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(10f));
        hint.setBounds((int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth() / 2 - 200,
                (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight() - height - 53, 400, 12);
        add(hint);
    }
}
