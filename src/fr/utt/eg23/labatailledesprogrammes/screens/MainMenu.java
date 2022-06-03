package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {
        super();
        this.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        setLayout(new BorderLayout());
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4, 1));
        panelButtons.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        JLabel labelImageMenu = new JLabel();
        labelImageMenu.setIcon(Utils.getImageToSize("EcranMenu.png", 804, 695));

        TextButton btnJouer = new TextButton("Jouer", 50, () -> {
            //TODO popup matchmaking
            TroopConfiguration tc = new TroopConfiguration(UTTBranch.ISI);
            LaBatailleDesProgrammes.getInstance().setTroopConfiguration(tc);
            LaBatailleDesProgrammes.getInstance().switchPanel(tc);
        });
        btnJouer.setBorder(new EmptyBorder(10, 25, 10, 10));
        btnJouer.setHorizontalAlignment(SwingConstants.LEFT);

        TextButton btnTutoriel = new TextButton("Tutoriel", 50, () -> {
            //TODO fenetre Tuto
        });
        btnTutoriel.setBorder(new EmptyBorder(10, 25, 10, 10));
        btnTutoriel.setHorizontalAlignment(SwingConstants.LEFT);

        TextButton btnBoutique = new TextButton("Boutique", 50, () -> {
            LaBatailleDesProgrammes.getInstance().switchPanel(new Shop());
        });
        btnBoutique.setBorder(new EmptyBorder(10, 25, 10, 10));
        btnBoutique.setHorizontalAlignment(SwingConstants.LEFT);

        TextButton btnInfos = new TextButton("Infos", 50, () -> {
            //TODO fenetre Infos
        });
        btnInfos.setBorder(new EmptyBorder(10, 25, 10, 10));
        btnInfos.setHorizontalAlignment(SwingConstants.LEFT);

        TextButton btnQuitter = new TextButton("Quitter", 30, () -> System.exit(0));
        btnQuitter.setBorder(new EmptyBorder(10, 50, 10, 25));
        btnQuitter.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton btnSettings = new JButton();
        btnSettings.setIcon(Utils.getImageToSize("settings.png", 100, 60));
        btnSettings.setBackground(null);
        btnSettings.setBorder(new EmptyBorder(10, 50, 10, 0));

        this.add(labelImageMenu, BorderLayout.CENTER);
        panelButtons.add(btnJouer);
        panelButtons.add(btnTutoriel);
        panelButtons.add(btnBoutique);
        panelButtons.add(btnInfos);
        rightPanel.add(btnQuitter, BorderLayout.NORTH);
        rightPanel.add(btnSettings, BorderLayout.SOUTH);
        this.add(panelButtons, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }
}
