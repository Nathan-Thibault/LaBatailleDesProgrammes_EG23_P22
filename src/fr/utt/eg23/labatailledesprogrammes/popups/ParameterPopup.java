package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.DefaultLabel;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ParameterPopup extends JDialog {

    public ParameterPopup() {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 550;
        int height = 200;
        setBounds(center.x - (width / 2), center.y - (height / 2), width, height);
        setTitle("Paramètres");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        getContentPane().add(contentPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.add(leftPanel);

        JLabel audioLabel = new DefaultLabel("Volume audio:");
        audioLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        leftPanel.add(audioLabel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel resolutionLabel = new DefaultLabel("Résolution:");
        resolutionLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        leftPanel.add(resolutionLabel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel langageLabel = new DefaultLabel("Langue:");
        langageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        leftPanel.add(langageLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.add(rightPanel);
        rightPanel.setBorder(new EmptyBorder(0, 25, 0, 0));

        JSlider slider = new JSlider();
        slider.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        rightPanel.add(slider);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        String[] optionsToChooseR = {"1080p", "720p", "360p", "144p"};
        JComboBox<String> comboBoxResolution = new JComboBox<>(optionsToChooseR);
        rightPanel.add(comboBoxResolution);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        String[] optionsToChooseL = {"Français", "English", "Deutsch", "Español"};
        JComboBox<String> comboBoxLangage = new JComboBox<>(optionsToChooseL);
        rightPanel.add(comboBoxLangage);

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        buttonPane.setLayout(new BorderLayout());
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new TextButton("Valider", 15f, this::dispose);
        okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton, BorderLayout.EAST);
        getRootPane().setDefaultButton(okButton);

        setVisible(true);
    }
}
