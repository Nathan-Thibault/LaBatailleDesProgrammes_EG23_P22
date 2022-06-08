package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfosPopUp extends JDialog {
    public InfosPopUp(){
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 550;
        int height = 200;
        setBounds(center.x - (width / 2), center.y - (height / 2), width, height);
        setTitle("Informations");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setVisible(true);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        getContentPane().add(contentPanel);

        JTextArea infosLbl = new JTextArea("Ce jeu a été réalisé dans le cadre\nde l'unité d'enseignement EG23 enseignée" +
                " la\nbranche Informatique et Systèmes d'Informations au\nsemestre de printemps 2022 à l'université " +
                "de\ntechnologie de Troyes par Étienne\nLanternier et Nathan Thibault.", 5, 1);
        infosLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosLbl.setForeground(Color.WHITE);
        infosLbl.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        infosLbl.setFont(LaBatailleDesProgrammes.GAME_FONT);
        contentPanel.add(infosLbl);

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        buttonPane.setLayout(new BorderLayout());
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new TextButton("quitter", 15f, this::dispose);
        okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton, BorderLayout.EAST);
        getRootPane().setDefaultButton(okButton);

    }

}
