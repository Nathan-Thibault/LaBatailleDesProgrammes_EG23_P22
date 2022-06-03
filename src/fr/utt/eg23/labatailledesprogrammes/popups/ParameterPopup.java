package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;
import fr.utt.eg23.labatailledesprogrammes.screens.MainMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class ParameterPopup extends JDialog {

    public ParameterPopup() {
        setBounds(100, 100, 550, 200);
        setTitle("Paramètres");
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        getContentPane().add(contentPanel);

        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.add(panelGauche);

        JLabel labelAudio = new JLabel("Volume audio");
        labelAudio.setFont(LaBatailleDesProgrammes.GAME_FONT);
        labelAudio.setForeground(Color.WHITE);
        panelGauche.add(labelAudio);

        panelGauche.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel labelResolution = new JLabel("Résolution");
        labelResolution.setFont(LaBatailleDesProgrammes.GAME_FONT);
        labelResolution.setForeground(Color.WHITE);
        panelGauche.add(labelResolution);

        panelGauche.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel labelLangue = new JLabel("Langage");
        labelLangue.setForeground(Color.WHITE);
        labelLangue.setFont(LaBatailleDesProgrammes.GAME_FONT);
        panelGauche.add(labelLangue);

        JPanel panelDroite = new JPanel();
        panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
        panelDroite.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.add(panelDroite);
        panelDroite.setBorder(new EmptyBorder(0,25, 0, 0));

        JSlider slider = new JSlider();
        slider.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        slider.setBounds(0, 0, 80, 20);
        panelDroite.add(slider);

        //panelDroite.add(Box.createRigidArea(new Dimension(0, 15)));

        String[] optionsToChooseR = {"1080p", "720p", "360p", "144p"};
        JComboBox comboBoxResolution = new JComboBox<>(optionsToChooseR);
        comboBoxResolution.setPreferredSize(new Dimension(80, 20));
        panelDroite.add(comboBoxResolution);

        String[] optionsToChooseL = {"Français", "English", "Deutsch", "Espanol"};
        JComboBox comboBoxLangage = new JComboBox<>(optionsToChooseL);
        comboBoxLangage.setPreferredSize(new Dimension(80, 20));
        panelDroite.add(comboBoxLangage);




        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        buttonPane.setLayout(new BorderLayout());
        getContentPane().add(buttonPane, BorderLayout.SOUTH);


        JButton okButton = new TextButton("Valider", 15f, () -> this.dispose());
        okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton, BorderLayout.EAST);
        getRootPane().setDefaultButton(okButton);
    }
}
