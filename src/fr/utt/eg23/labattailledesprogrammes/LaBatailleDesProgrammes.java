package fr.utt.eg23.labattailledesprogrammes;

import javax.swing.*;
import java.awt.*;

public class LaBatailleDesProgrammes {

    private JFrame frame;

    private static LaBatailleDesProgrammes instance;

    public static final Color COLOR_BACKGROUND = new Color(55, 60, 72);
	public static final Font GAME_FONT = new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 15);
    public static final Dimension FRAME_SIZE = new Dimension(1280,720);

    public static LaBatailleDesProgrammes getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                instance = new LaBatailleDesProgrammes();
                instance.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LaBatailleDesProgrammes() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("La Bataille des Programmes");
        frame.setSize(FRAME_SIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TitleScreen());
    }

    public void switchPanel(JPanel newPanel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newPanel);
        frame.repaint();
        frame.revalidate();
    }
}
