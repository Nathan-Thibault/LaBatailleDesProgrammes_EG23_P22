package fr.utt.eg23.labattailledesprogrammes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextButton extends JButton {

    public static final Color COLOR_DEFAULT = Color.WHITE;
    public static final Color COLOR_MOUSE_OVER = Color.CYAN;

    private Color foregroundColor;
    private Color mouseOverColor;

    public TextButton(String text, float fontSize, Runnable onClick) {
        super(text);

        foregroundColor = COLOR_DEFAULT;
        mouseOverColor = COLOR_MOUSE_OVER;

        setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        super.setForeground(foregroundColor);
        setBackground(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        //change text color when mouse over
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setColorMouseOver();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setColorDefault();
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClick.run();
            }
        });
    }

    private void setColorDefault(){
        super.setForeground(foregroundColor);
    }

    private void setColorMouseOver(){
        super.setForeground(mouseOverColor);
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        foregroundColor = color;
    }

    public void setMouseOverColor(Color color) {
        mouseOverColor = color;
    }
}
