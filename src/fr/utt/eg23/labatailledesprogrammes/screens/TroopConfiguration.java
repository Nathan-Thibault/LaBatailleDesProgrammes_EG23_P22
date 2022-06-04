package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.card.CardForm;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.*;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;
import fr.utt.eg23.labatailledesprogrammes.card.GameCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class TroopConfiguration extends JPanel {

    private final JLabel pointsLabel;
    private final CustomProgressBar pointsBar;

    public TroopConfiguration(UTTBranch branch) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        JPanel row0 = new CardDropPanel(5, CardForm.FULL);
        row0.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 0));
        row0.setBackground(null);
        row0.add(new GameCard(branch, FighterType.MASTER_OF_WAR));
        for (int i = 0; i < 4; i++) {
            row0.add(new GameCard(branch, FighterType.ELITE_SOLDIER));
        }
        cardsPanel.add(row0);

        //add 3 rows of 5 soldier
        for (int n = 1; n < 4; n++) {
            JPanel rowN = new CardDropPanel(5, CardForm.FULL);
            rowN.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 0));
            rowN.setBackground(null);
            rowN.setBorder(new EmptyBorder(3, 0, 0, 0));
            for (int i = 0; i < 5; i++) {
                rowN.add(new GameCard(branch, FighterType.SOLDIER));
            }
            cardsPanel.add(rowN);
        }

        cardsPanel.setPreferredSize(new Dimension(
                (int) (5 * (GameCard.SIZE.getWidth() + 3) + 7),
                (int) (4 * (GameCard.SIZE.getHeight() + 3) - 3)));

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        int scrollPaneWidth = (int) (5 * (GameCard.SIZE.getWidth() + 3) + 17);
        scrollPane.setPreferredSize(new Dimension(
                scrollPaneWidth,
                (int) (1.5 * GameCard.SIZE.getHeight())));

        JPanel leftPanel = new JPanel();
        int width = (int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth() - scrollPaneWidth - 15;
        leftPanel.setPreferredSize(new Dimension(width, 0));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        leftPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        JLabel title1 = new JLabel("Paramétrages des ");
        title1.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        title1.setForeground(Color.WHITE);

        JLabel title2 = new JLabel("troupes");
        title2.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        title2.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        titlePanel.setBackground(null);
        titlePanel.add(title1);
        titlePanel.add(title2);

        pointsLabel = new JLabel("Points restants : 400");
        pointsLabel.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setBackground(null);

        pointsBar = new CustomProgressBar();
        pointsBar.setMinimum(0);
        pointsBar.setMaximum(400);
        pointsBar.setValue(400);
        pointsBar.setStringVisible(false);
        pointsBar.setBarsVisible(false);
        pointsBar.setProgressColor(Color.GREEN);
        pointsBar.setBorder(new LineBorder(Color.WHITE));
        pointsBar.setBackground(null);
        pointsBar.setPreferredSize(new Dimension(300, 40));

        JPanel pointsBarPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        pointsBarPanel.setBackground(null);
        pointsBarPanel.add(pointsLabel);
        pointsBarPanel.add(pointsBar);

        JPanel timePanel = new TimerDisplay(120);

        JPanel readyPanel = new ReadyPanel();

        leftPanel.add(titlePanel);
        leftPanel.add(pointsBarPanel);
        leftPanel.add(timePanel);
        leftPanel.add(readyPanel);

        JSeparator line = new JSeparator();
        line.setForeground(Color.BLACK);
        line.setBackground(null);

        JLabel reservistLabel = new JLabel("Réservistes: ");
        reservistLabel.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        reservistLabel.setForeground(Color.WHITE);

        JLabel reservistCount = new JLabel("0/5");
        reservistCount.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        reservistCount.setForeground(Color.RED);

        JPanel reservistText = new JPanel();
        reservistText.setBackground(null);
        reservistText.add(reservistLabel);
        reservistText.add(reservistCount);

        JPanel reservistDropPanel = new CardDropPanel(5, CardForm.MINIMIZED);
        reservistDropPanel.setBackground(null);
        reservistDropPanel.setBorder(new LineBorder(Color.BLACK, 5));
        reservistDropPanel.setPreferredSize(new Dimension(0, 80));
        reservistDropPanel.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                updateContainer(e);
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                updateContainer(e);
            }

            private void updateContainer(ContainerEvent e) {
                int n = e.getContainer().getComponentCount();

                reservistCount.setText(n + "/5");
                if (n == 5) {
                    reservistCount.setForeground(Color.GREEN);
                } else {
                    reservistCount.setForeground(Color.RED);
                }

                reservistCount.revalidate();
                reservistCount.repaint();
            }
        });

        JPanel reservistPanel = new JPanel();
        reservistPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        reservistPanel.setLayout(new BoxLayout(reservistPanel, BoxLayout.Y_AXIS));
        reservistPanel.setBorder(new EmptyBorder(3, 10, 2, 10));
        reservistPanel.add(line);
        reservistPanel.add(reservistText);
        reservistPanel.add(reservistDropPanel);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(reservistPanel, BorderLayout.SOUTH);
    }

    public void addToPoints(int addend) {
        int newValue = pointsBar.getValue() + addend;
        pointsBar.setValue(newValue);
        pointsLabel.setText("Points restants : " + newValue);
        pointsLabel.revalidate();
        pointsLabel.repaint();
    }
}
