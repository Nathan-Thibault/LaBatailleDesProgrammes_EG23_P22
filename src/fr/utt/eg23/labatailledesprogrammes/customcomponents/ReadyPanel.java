package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with a {@link TimerDisplay} and a ready checkbox.
 * Used in {@link fr.utt.eg23.labatailledesprogrammes.screens.TroopConfiguration} and
 * {@link fr.utt.eg23.labatailledesprogrammes.screens.TroopPositioning}.
 */
public class ReadyPanel extends JPanel {

    private final JLabel opponentStatus;

    public ReadyPanel(Runnable onChecked) {
        JCheckBox readyCheckBox = new JCheckBox();
        readyCheckBox.setText("Êtes vous prêts ?");
        readyCheckBox.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(20f));
        readyCheckBox.setForeground(Color.WHITE);
        readyCheckBox.setBackground(null);
        readyCheckBox.addActionListener(e -> {
            if (readyCheckBox.isSelected()) onChecked.run();
        });

        //TODO : custom checkbox graphics ?

        opponentStatus = new DefaultLabel("Votre adversaire n'est pas prêt...");

        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        setBackground(null);
        add(readyCheckBox);
        add(opponentStatus);
    }

    public void setOpponentReady(boolean ready) {
        if (ready)
            opponentStatus.setText("Votre adversaire est prêt!");
        else
            opponentStatus.setText("Votre adversaire n'est pas prêt...");
    }
}
