package fr.utt.eg23.labatailledesprogrammes.screens;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.BlinkLabel;
import fr.utt.eg23.labatailledesprogrammes.popups.ConnexionPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TitleScreen extends JPanel {

    private final BlinkLabel continueText;

    public TitleScreen() {
        JLabel labelImageTitre = new JLabel("");
        //resize title image to make room for continue text
        int height = (int) LaBatailleDesProgrammes.FRAME_SIZE.getHeight() - 25;
        labelImageTitre.setIcon(Utils.getImageToSize("ecranTitre.jpg", (int) ((float) 1280 * (float) height / (float) 720), height));

        continueText = new BlinkLabel("    Appuyez sur espace pour continuer...", 500);
        continueText.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(25f));
        continueText.setForeground(Color.WHITE);

        JPanel continuePanel = new JPanel();
        continuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        continuePanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        continuePanel.add(continueText);

        setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        setFocusable(true);
        setSize(LaBatailleDesProgrammes.FRAME_SIZE);
        setLayout(new BorderLayout());
        add(labelImageTitre, BorderLayout.CENTER);
        add(continuePanel, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    continueText.setVisible(false);
                    //connexion popup
                    new ConnexionPopup();
                }
            }
        });
    }
}
