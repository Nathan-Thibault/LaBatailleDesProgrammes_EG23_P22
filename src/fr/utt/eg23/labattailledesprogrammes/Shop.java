package fr.utt.eg23.labattailledesprogrammes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Shop extends JPanel {

    public Shop(){
        super();
        setLayout(new BorderLayout());
        setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        //Le panel avec les 3 textes en haut.
        final JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BorderLayout());
        panelHaut.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        //Le panel principal avec les différentes branches.
        final JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);



        final TextButton btnQuitter = new TextButton("Retour", 30, () -> {
            LaBatailleDesProgrammes.getInstance().switchPanel(new MainMenu());
        });
        panelHaut.add(btnQuitter, BorderLayout.WEST);


        final JLabel titreBoutique = new JLabel("Boutique");
        titreBoutique.setHorizontalAlignment(JLabel.CENTER);
        titreBoutique.setForeground(Color.WHITE);
        titreBoutique.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(50f));
        panelHaut.add(titreBoutique, BorderLayout.CENTER);


        final JLabel argentRestant = new JLabel("Reste: 12€");
        argentRestant.setHorizontalAlignment(JLabel.CENTER);
        argentRestant.setForeground(Color.WHITE);
        argentRestant.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        argentRestant.setBorder(new EmptyBorder(0, 0, 0, 15));
        panelHaut.add(argentRestant, BorderLayout.EAST);

        creerBranche("Informatique et Systèmes d'Informations", "smartphone.png", "tablette.png", panelPrincipal);
        creerBranche("Automatique et Informatique Industrielle", "processeur.png", "robot.png", panelPrincipal);
        creerBranche("Réseaux et Télécommunications", "cloud.png", "server.png", panelPrincipal);
        creerBranche("Génie Mécanique", "presse.png", "engrenage.png", panelPrincipal);
        creerBranche("Matériaux, Technologie et Économie", "bitcoin.png", "materiaux.png", panelPrincipal);
        creerBranche("Génie Industriel", "calendrier.png", "optimisation.png", panelPrincipal);

        this.add(panelHaut, BorderLayout.NORTH);
        //this.add(panelPrincipal, BorderLayout.CENTER);

        final JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(null);
    }

    private void creerBranche(String nom, String icone1, String icone2, JPanel panelPrincipal){
        final JPanel panelBranche = new JPanel();
        panelBranche.setLayout(new BoxLayout(panelBranche, BoxLayout.Y_AXIS));
//        panelBranche.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBranche.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);



        final JLabel labelBranche = new JLabel(nom + " :");
        labelBranche.setForeground(Color.WHITE);
        labelBranche.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelBranche.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        labelBranche.setBorder(new EmptyBorder(20, 0, 0, 0));
        panelBranche.add(labelBranche);

        final JLabel barreBoutique = new JLabel();
        barreBoutique.setAlignmentX(Component.LEFT_ALIGNMENT);
        barreBoutique.setIcon(Utils.getImageToSize("barreBoutique.png", 1050, 18));
        barreBoutique.setBorder(new EmptyBorder(10, 0, 20, 0));
        panelBranche.add(barreBoutique);

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        panelBranche.add(panel);

        panel.add(Box.createRigidArea(new Dimension(23, 0)));

        final JLabel skin1 = new JLabel();
        skin1.setIcon(Utils.getImageToSize(icone1, 130, 130));
        skin1.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));
        panel.add(skin1);

        final JLabel skin2 = new JLabel();
        skin2.setIcon(Utils.getImageToSize(icone2, 130, 130));
        skin2.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));
        panel.add(skin2);

        panelPrincipal.add(panelBranche);
    }

    public static void main(String[] a){
        final JFrame frame = new JFrame("La Bataille des Programmes");
        frame.setSize(LaBatailleDesProgrammes.FRAME_SIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Shop());
        frame.setVisible(true);
    }
}
