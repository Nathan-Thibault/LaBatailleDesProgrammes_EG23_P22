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

        JPanel bluePanel = new InvisiblePanel("blue", this);
        bluePanel.setBounds(620, 355, 60, 60);
        add(bluePanel);

        JPanel greenPanel = new InvisiblePanel("green", this);
        greenPanel.setBounds(585, 303, 45, 38);
        add(greenPanel);

        JPanel redPanel = new InvisiblePanel("red", this);
        redPanel.setBounds(720, 300, 90, 60);
        add(redPanel);

        JPanel pinkPanel = new InvisiblePanel("pink", this);
        pinkPanel.setBounds(765, 375, 105, 130);
        add(pinkPanel);

        JPanel orangePanel = new InvisiblePanel("orange", this);
        orangePanel.setBounds(415, 80, 110, 100);
        add(orangePanel);

        JLabel title = new DefaultLabel("Combats en cours...", 30f);
        title.setBounds(10, 10, 400, 35);
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
        playerBranch.setPreferredSize(new Dimension(0, 17));
        playerBranch.add(new DefaultLabel(branch.toString()));

        JPanel opponentBranch = new JPanel();
        opponentBranch.setBackground(null);
        opponentBranch.setPreferredSize(new Dimension(0, 17));
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
        linePanel.setPreferredSize(new Dimension(0, 10));
        linePanel.add(line);

        mainPanel.add(opponentBranch);
        mainPanel.add(opponentTroopPanel);
        mainPanel.add(linePanel);
        mainPanel.add(playerTroopPanel);
        mainPanel.add(playerBranch);
    }

    public JPanel getOpponentTroopPanel() {
        return opponentTroopPanel;
    }

    public JPanel getPlayerTroopPanel() {
        return playerTroopPanel;
    }
}
