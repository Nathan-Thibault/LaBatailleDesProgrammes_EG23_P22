package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.BackgroundPanel;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.DefaultLabel;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.InvisiblePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class BattleVisualisation extends BackgroundPanel {

    private final JPanel opponentTroopPanel;
    private final JPanel playerTroopPanel;

    public BattleVisualisation() {
        setLayout(null);
        setBackground("red_bg.png");

        JLabel title = new DefaultLabel("Combats en cours...", 30f);
        title.setBounds(200, 10, 400, 35);
        add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new LineBorder(Color.BLACK, 5));
        mainPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        Insets insets = LaBatailleDesProgrammes.getInstance().getFrame().getInsets();
        int panelWidth = 400;
        int width = (int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth() - insets.right - insets.left;
        int height = (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight() - insets.top - insets.bottom;
        mainPanel.setBounds(width - panelWidth, 0, panelWidth, height);
        add(mainPanel);

        UTTBranch branch = Objects.requireNonNull(LaBatailleDesProgrammes.getInstance().getBranch());
        JPanel playerBranch = new JPanel();
        playerBranch.setBackground(null);
        playerBranch.add(new DefaultLabel(branch.toString()));

        JPanel opponentBranch = new JPanel();
        opponentBranch.setBackground(null);
        opponentBranch.add(new DefaultLabel(UTTBranch.RT.toString()));

        opponentTroopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        opponentTroopPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        opponentTroopPanel.setBackground(null);

        playerTroopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        playerTroopPanel.setBorder(new EmptyBorder(20, 20, 30, 20));
        playerTroopPanel.setBackground(null);

        JLabel line = new JLabel(Utils.getImageToSize("barreBoutique.png", panelWidth - 40, 10));
        line.setBorder(new EmptyBorder(0, 20, 0, 20));
        JPanel linePanel = new JPanel();
        linePanel.setBackground(null);
        linePanel.add(line);

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);
        north.add(opponentBranch, BorderLayout.NORTH);
        north.add(opponentTroopPanel, BorderLayout.CENTER);
        north.add(linePanel, BorderLayout.SOUTH);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(null);
        south.add(playerBranch, BorderLayout.SOUTH);
        south.add(playerTroopPanel, BorderLayout.CENTER);

        mainPanel.add(north);
        mainPanel.add(south);

        JLabel hint = new JLabel("  (Cliquer sur les zones pour afficher les combats.)");
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint.setForeground(Color.GRAY);
        hint.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(10f));
        hint.setBounds((int) LaBatailleDesProgrammes.FRAME_SIZE.getWidth() / 2 - 200,
                (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight() - 53, 400, 12);
        add(hint);

        JPanel bluePanel = new InvisiblePanel("blue", this);
        bluePanel.setBounds(275, 355, 60, 60);
        add(bluePanel);

        JPanel greenPanel = new InvisiblePanel("green", this);
        greenPanel.setBounds(240, 303, 45, 38);
        add(greenPanel);

        JPanel redPanel = new InvisiblePanel("red", this);
        redPanel.setBounds(375, 300, 90, 60);
        add(redPanel);

        JPanel pinkPanel = new InvisiblePanel("pink", this);
        pinkPanel.setBounds(420, 375, 105, 130);
        add(pinkPanel);

        JPanel orangePanel = new InvisiblePanel("orange", this);
        orangePanel.setBounds(70, 80, 110, 100);
        add(orangePanel);
    }

    public JPanel getOpponentTroopPanel() {
        return opponentTroopPanel;
    }

    public JPanel getPlayerTroopPanel() {
        return playerTroopPanel;
    }
}
