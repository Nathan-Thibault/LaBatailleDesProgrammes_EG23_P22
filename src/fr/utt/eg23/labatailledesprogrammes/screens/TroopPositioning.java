package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.card.CardForm;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.*;
import fr.utt.eg23.labatailledesprogrammes.card.GameCard;
import fr.utt.eg23.labatailledesprogrammes.card.MinimizedCard;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

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

        ReadyPanel readyPanel = new ReadyPanel(null);
        readyPanel.setOpponentReady(true);

        JPanel infosPanel = new JPanel();
        infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.Y_AXIS));
        infosPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        infosPanel.add(timePanel);
        infosPanel.add(readyPanel);
        infosPanel.setBounds(5, 200, 320, 250);
        add(infosPanel);

        JLabel title = new DefaultLabel("Phase de déploiement des troupes", 30f);
        title.setBounds(500, 5, 500, 40);
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
    }
}
