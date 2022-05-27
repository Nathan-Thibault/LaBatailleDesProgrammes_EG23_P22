package fr.utt.eg23.labattailledesprogrammes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameCard extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(194, 194, 200);
    private static final Dimension SIZE = new Dimension(150, 200);

    public GameCard(UTTBranch branch, FighterType fType) {
        String iconFileName = "icon_" + branch.getFileNameAffix() + "_" + fType.getFileNameAffix() + ".png";

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(SIZE);
        setMinimumSize(SIZE);
        setMaximumSize(SIZE);
        setBorder(new LineBorder(fType.getColor(), 5));

        JLabel title = new JLabel(fType.toString());
        float fontSize = (float) (SIZE.width * 0.13);
        title.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(fontSize));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(title);

        JLabel icon = new JLabel();
        int iconSize = (int) (SIZE.getWidth() * 0.65);
        icon.setIcon(Utils.getImageToSize(iconFileName, iconSize, iconSize));
        icon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(icon);

        JSeparator line = new JSeparator();
        line.setForeground(Color.BLACK);
        line.setBackground(null);
        add(line);

        JPanel c = new JPanel();
        c.setBackground(BACKGROUND_COLOR);
        c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
        add(c);

        CustomProgressBar pb = new CustomProgressBar();
        pb.setMinimum(0);
        pb.setMaximum(10);
        pb.setValue(3);
        pb.setProgressColor(Color.ORANGE);
        pb.setForeground(Color.BLACK);
        c.add(pb);

        TextButton buttonLess = new TextButton("-", 10f, () -> pb.setValue(pb.getValue() - 1));
        buttonLess.setBorder(new EmptyBorder(5,5,5,3));
        buttonLess.setForeground(Color.BLACK);
        c.add(buttonLess);
        TextButton buttonMore = new TextButton("+", 10f, () -> pb.setValue(pb.getValue() + 1));
        buttonMore.setBorder(new EmptyBorder(5,3,5,5));
        buttonMore.setForeground(Color.BLACK);
        c.add(buttonMore);

    }

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(new GameCard(UTTBranch.ISI, FighterType.SOLDIER));
        frame.getContentPane().add(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}