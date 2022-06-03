package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Shop extends JPanel {

    public Shop() {
        super();
        setLayout(new BorderLayout());
        setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        final TextButton returnButton = new TextButton("Retour", 30, () -> LaBatailleDesProgrammes.getInstance().switchPanel(new MainMenu()));
        northPanel.add(returnButton, BorderLayout.WEST);


        final JLabel title = new JLabel("Boutique");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(50f));
        northPanel.add(title, BorderLayout.CENTER);


        final JLabel moneyLeft = new JLabel("Reste: 12€");
        moneyLeft.setHorizontalAlignment(JLabel.CENTER);
        moneyLeft.setForeground(Color.WHITE);
        moneyLeft.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        moneyLeft.setBorder(new EmptyBorder(0, 0, 0, 15));
        northPanel.add(moneyLeft, BorderLayout.EAST);

        for (UTTBranch branch : UTTBranch.values()) {
            centerPanel.add(createBranchPanel(branch));
        }

        final JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createBranchPanel(UTTBranch branch) {
        final JPanel branchPanel = new JPanel();
        branchPanel.setLayout(new BoxLayout(branchPanel, BoxLayout.Y_AXIS));
        branchPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        final JLabel labelBranche = new JLabel(branch + " :");
        labelBranche.setForeground(Color.WHITE);
        labelBranche.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));

        final JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        labelPanel.setBackground(null);
        labelPanel.setBorder(new EmptyBorder(20, 20, 5, 0));
        labelPanel.add(labelBranche);
        branchPanel.add(labelPanel);

        final JPanel separation = new JPanel(new FlowLayout(FlowLayout.LEADING));
        separation.setBackground(null);
        separation.setBorder(new EmptyBorder(5, 20, 20, 0));
        separation.add(new JLabel(Utils.getImageToSize("barreBoutique.png", 1050, 18)));
        branchPanel.add(separation);

        final JPanel skinsPanel = new JPanel();
        skinsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        skinsPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        branchPanel.add(skinsPanel);

        skinsPanel.add(Box.createRigidArea(new Dimension(23, 0)));

        for (String skinFileName : branch.getSkinsFileNames()) {
            final JLabel skin = new JLabel();
            skin.setIcon(Utils.getImageToSize("shop/" + skinFileName, 130, 130));
            skin.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));
            skinsPanel.add(skin);
        }

        return branchPanel;
    }
}
