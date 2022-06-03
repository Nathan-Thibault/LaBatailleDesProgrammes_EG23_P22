package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.BlinkLabel;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.CustomProgressBar;
import fr.utt.eg23.labatailledesprogrammes.fighter.FighterType;
import fr.utt.eg23.labatailledesprogrammes.fighter.GameCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TroopConfiguration extends JPanel {

    private final JLabel pointsLabel;
    private final CustomProgressBar pointsBar;

    public TroopConfiguration(UTTBranch branch) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        JPanel row0 = new JPanel();
        row0.setLayout(new BoxLayout(row0, BoxLayout.X_AXIS));
        row0.setBackground(null);
        row0.add(new GameCard(branch, FighterType.MASTER_OF_WAR));
        for (int i = 0; i < 4; i++) {
            row0.add(Box.createRigidArea(new Dimension(3, 0)));//space between cards
            row0.add(new GameCard(branch, FighterType.ELITE_SOLDIER));
        }
        row0.add(Box.createRigidArea(new Dimension(10, 0)));//end of row0 spacing
        cardsPanel.add(row0);

        //add 3 rows of 5 soldier
        for (int n = 1; n < 4; n++) {
            JPanel rowN = new JPanel();
            rowN.setLayout(new BoxLayout(rowN, BoxLayout.X_AXIS));
            rowN.setBackground(null);
            for (int i = 0; i < 5; i++) {
                if (i > 0) rowN.add(Box.createRigidArea(new Dimension(3, 0)));//space between cards
                rowN.add(new GameCard(branch, FighterType.SOLDIER));
            }
            rowN.add(Box.createRigidArea(new Dimension(10, 0)));//end of rowN spacing
            cardsPanel.add(Box.createRigidArea(new Dimension(0, 3)));//space between rows
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

        JLabel timeText = new JLabel("Temps restant: ");
        timeText.setForeground(Color.WHITE);
        timeText.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));

        AtomicInteger secondsLeft = new AtomicInteger(70);
        String str = String.format("%02d:%02d", (secondsLeft.get() % 3600) / 60, (secondsLeft.get() % 60));//mm:ss
        BlinkLabel timeLeft = new BlinkLabel(str, 150);
        timeLeft.setBlinking(false);
        timeLeft.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        timeLeft.setForeground(Color.WHITE);
        Timer timer = new Timer(1000, e -> {
            secondsLeft.addAndGet(-1);
            String s = String.format("%02d:%02d", (secondsLeft.get() % 3600) / 60, (secondsLeft.get() % 60));//mm:ss
            if (secondsLeft.get() < 60 && !timeLeft.getForeground().equals(Color.RED)) {
                timeLeft.setForeground(Color.RED);
                timeLeft.setBlinking(true);
            }

            timeLeft.setText(s);
            timeLeft.revalidate();
            timeLeft.repaint();
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        timePanel.setBackground(null);
        timePanel.add(timeText);
        timePanel.add(timeLeft);

        JCheckBox readyCheckBox = new JCheckBox();
        readyCheckBox.setText("Êtes vous prêts ?");
        readyCheckBox.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        readyCheckBox.setForeground(Color.WHITE);
        readyCheckBox.setBackground(null);

        JLabel opponentStatus = new JLabel("Votre adversaire n'est pas prêt...");
        opponentStatus.setFont(LaBatailleDesProgrammes.GAME_FONT);
        opponentStatus.setForeground(Color.WHITE);

        JPanel readyPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        readyPanel.setBackground(null);
        readyPanel.add(readyCheckBox);
        readyPanel.add(opponentStatus);

        leftPanel.add(titlePanel);
        leftPanel.add(pointsBarPanel);
        leftPanel.add(timePanel);
        leftPanel.add(readyPanel);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addToPoints(int addend) {
        int newValue = pointsBar.getValue() + addend;
        pointsBar.setValue(newValue);
        pointsLabel.setText("Points restants : " + newValue);
        pointsLabel.revalidate();
        pointsLabel.repaint();
    }

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        TroopConfiguration tc = new TroopConfiguration(UTTBranch.ISI);
        frame.getContentPane().add(tc);
        frame.setSize(LaBatailleDesProgrammes.FRAME_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
