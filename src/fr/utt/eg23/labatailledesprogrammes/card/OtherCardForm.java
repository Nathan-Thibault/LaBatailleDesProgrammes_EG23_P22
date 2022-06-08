package fr.utt.eg23.labatailledesprogrammes.card;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class OtherCardForm extends JPanel {
    private JFrame frame;

    private final GameCard original;

    protected OtherCardForm(GameCard original) {
        this.original = original;

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (frame != null) return;

                frame = new JFrame();
                frame.setSize(GameCard.SIZE);
                frame.setAlwaysOnTop(true);
                frame.setResizable(false);
                frame.setUndecorated(true);
                frame.setLocationRelativeTo(original.getOtherForm());
                frame.getContentPane().add(original);
                original.getOtherForm().removeMouseListener(this);
                original.addMouseListener(this);
                frame.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                original.getOtherForm().disposeFrame();
                original.removeMouseListener(this);
                original.getOtherForm().addMouseListener(this);
            }
        });

        original.setOtherForm(this);
    }

    public void disposeFrame() {
        frame.dispose();
        frame = null;
    }

    public GameCard getOriginal() {
        return original;
    }
}
