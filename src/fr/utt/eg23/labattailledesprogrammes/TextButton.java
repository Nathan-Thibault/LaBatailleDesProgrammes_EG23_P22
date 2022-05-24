package fr.utt.eg23.labattailledesprogrammes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextButton extends JButton {

    public static final Color COLOR_DEFAULT = Color.WHITE;
    public static final Color COLOR_MOUSE_OVER = Color.CYAN;

    public TextButton(String text, float fontSize, Runnable onClick) {
        super(text);
        setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        setForeground(COLOR_DEFAULT);
        setBackground(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        //change text color when mouse over
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(COLOR_MOUSE_OVER);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(COLOR_DEFAULT);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClick.run();
            }
        });
    }
}
