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
    private TextButton btnJouer;
    private TextButton btnTutoriel;
    private TextButton btnBoutique;
    private TextButton btnInfos;
    private TextButton btnQuitter;
    private JButton btnSettings;

    public MainMenu(){
        super();
        this.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        setLayout(new BorderLayout());
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(4,1));
        panelButtons.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        panelDroite = new JPanel();
        panelDroite.setLayout(new BorderLayout());
        panelDroite.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);



        labelImageMenu = new JLabel("");

        btnJouer = new TextButton("Jouer", 50, () -> {
            //TODO popup matchmaking
        });
        btnJouer.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnJouer.setHorizontalAlignment(SwingConstants.LEFT);



        btnTutoriel = new TextButton("Tutoriel", 50, () -> {
            //TODO fenetre Tuto
        });
        this.btnTutoriel.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnTutoriel.setHorizontalAlignment(SwingConstants.LEFT);

        btnBoutique = new TextButton("Boutique", 50, () -> {
            LaBatailleDesProgrammes.getInstance().switchPanel(new Shop());
        });
        this.btnBoutique.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnBoutique.setHorizontalAlignment(SwingConstants.LEFT);

        btnInfos = new TextButton("Infos", 50, () -> {
            //TODO fenetre Infos
        });
        this.btnInfos.setBorder(new EmptyBorder(10, 25, 10, 10));
        this.btnInfos.setHorizontalAlignment(SwingConstants.LEFT);

        btnQuitter = new TextButton("Quitter", 30, () -> {
            System.exit(0);
        });
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
            BufferedImage buffImg = ImageIO.read(LaBatailleDesProgrammes.class.getResource("/images/" + imageName));
            Image img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
