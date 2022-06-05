package fr.utt.eg23.labatailledesprogrammes.customcomponents;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {
    public DefaultLabel(String text) {
        super(text);
        setFont(LaBatailleDesProgrammes.GAME_FONT);
        setForeground(Color.WHITE);
    }

    public DefaultLabel(String text, float fontSize) {
        super(text);
        setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        setForeground(Color.WHITE);
    }
}
