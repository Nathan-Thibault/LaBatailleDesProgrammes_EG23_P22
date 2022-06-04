package fr.utt.eg23.labatailledesprogrammes.card;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinimizedCard extends JPanel {

    public static final Dimension SIZE = new Dimension((int) GameCard.SIZE.getWidth(),
            (int) GameCard.TITLE_FONT_SIZE + GameCard.ICON_SIZE + 10);

    private JFrame frame;

    public MinimizedCard(GameCard original) {
        String iconFileName = "icon_" + original.getBranch().getFileNameAffix() + "_" + original.getFighterType().getFileNameAffix() + ".png";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GameCard.BACKGROUND_COLOR);
        setPreferredSize(SIZE);
        setMinimumSize(SIZE);
        setMaximumSize(SIZE);
        setBorder(new LineBorder(original.getFighterType().getColor(), 3));

        JLabel title = new JLabel(original.getFighterType().toString());
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(GameCard.TITLE_FONT_SIZE));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JLabel icon = new JLabel();
        icon.setIcon(Utils.getImageToSize(iconFileName, GameCard.ICON_SIZE, GameCard.ICON_SIZE));
        icon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(icon);

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
                frame.setLocationRelativeTo(original.getMinimized());
                frame.getContentPane().add(original);
                original.getMinimized().removeMouseListener(this);
                original.addMouseListener(this);
                frame.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                original.getMinimized().disposeFrame();
                original.removeMouseListener(this);
                original.getMinimized().addMouseListener(this);
            }
        });

        original.setMinimized(this);
    }

    public void disposeFrame(){
        frame.dispose();
        frame = null;
    }
}
