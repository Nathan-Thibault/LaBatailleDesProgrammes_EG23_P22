package fr.utt.eg23.labattailledesprogrammes;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends JPanel {

    private JLabel labelImageMenu;
    private JPanel panelButtons;
    private JPanel panelDroite;
    private JButton btnJouer;
    private JButton btnTutoriel;
    private JButton btnBoutique;
    private JButton btnInfos;
    private JButton btnQuitter;
    private JButton btnSettings;

    public MainMenu(){
        super();
        this.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);

        setLayout(new BorderLayout());
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4,1));
        panelButtons.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);

        panelDroite = new JPanel();
        panelDroite.setLayout(new BorderLayout());
        panelDroite.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);



        labelImageMenu = new JLabel("");

        btnJouer = new JButton("Jouer");
        this.btnJouer.setFont(new Font("Verdana Pro", Font.BOLD|Font.ITALIC, 50));
        this.btnJouer.setForeground(Color.WHITE);
        this.btnJouer.setBackground(null);
        this.btnJouer.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnJouer.setHorizontalAlignment(SwingConstants.LEFT);

        btnTutoriel = new JButton("Tutoriel");
        this.btnTutoriel.setFont(new Font("Verdana Pro", Font.BOLD|Font.ITALIC, 50));
        this.btnTutoriel.setForeground(Color.WHITE);
        this.btnTutoriel.setBackground(null);
        this.btnTutoriel.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnTutoriel.setHorizontalAlignment(SwingConstants.LEFT);

        btnBoutique = new JButton("Boutique");
        this.btnBoutique.setFont(new Font("Verdana Pro", Font.BOLD|Font.ITALIC, 50));
        this.btnBoutique.setForeground(Color.WHITE);
        this.btnBoutique.setBackground(null);
        this.btnBoutique.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnBoutique.setHorizontalAlignment(SwingConstants.LEFT);

        btnInfos = new JButton("Infos");
        this.btnInfos.setFont(new Font("Verdana Pro", Font.BOLD|Font.ITALIC, 50));
        this.btnInfos.setForeground(Color.WHITE);
        this.btnInfos.setBackground(null);
        this.btnInfos.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnInfos.setHorizontalAlignment(SwingConstants.LEFT);

        btnQuitter = new JButton("Quitter");
        this.btnQuitter.setFont(new Font("Verdana Pro", Font.BOLD|Font.ITALIC, 30));
        this.btnQuitter.setForeground(Color.WHITE);
        this.btnQuitter.setBackground(null);
        this.btnQuitter.setBorder(new EmptyBorder(10, 50, 10, 25));
        this.btnQuitter.setHorizontalAlignment(SwingConstants.RIGHT);

        btnSettings = new JButton();
        this.btnSettings.setIcon(getImageToSize("settings.png", 100, 60));
        this.btnSettings.setBackground(null);
        this.btnSettings.setBorder(new EmptyBorder(10, 50, 10, 0));


        labelImageMenu.setIcon(getImageToSize("EcranMenu.png", 804, 695));

        this.add(labelImageMenu, BorderLayout.CENTER);
        panelButtons.add(btnJouer);
        panelButtons.add(btnTutoriel);
        panelButtons.add(btnBoutique);
        panelButtons.add(btnInfos);
        panelDroite.add(btnQuitter, BorderLayout.NORTH);
        panelDroite.add(btnSettings, BorderLayout.SOUTH);
        this.add(panelButtons, BorderLayout.WEST);
        this.add(panelDroite, BorderLayout.EAST);
    }

    public ImageIcon getImageToSize(String imageName, int width, int height) {
        try {
            BufferedImage buffImg = ImageIO.read(LaBattailleDesProgrammes.class.getResource("/images/" + imageName));
            Image img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
