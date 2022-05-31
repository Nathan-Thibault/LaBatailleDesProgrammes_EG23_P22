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

        final JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BorderLayout());
        panelHaut.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);

        final JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1));
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


        //ISI:


        final JLabel labelIsi = new JLabel("Informatique et Systèmes d'Informations :");
        labelIsi.setHorizontalAlignment(JLabel.LEFT);
        labelIsi.setForeground(Color.WHITE);
        labelIsi.setFont(LaBatailleDesProgrammes.GAME_FONT.deriveFont(30f));
        labelIsi.setBorder(new EmptyBorder(0, 30, 0, 0));
        panelPrincipal.add(labelIsi);

        final JLabel barreBoutiqueISI = new JLabel();
        barreBoutiqueISI.setIcon(Utils.getImageToSize("barreBoutique.png", 1050, 18));
        barreBoutiqueISI.setBorder(new EmptyBorder(0, 30, 20, 0));
        barreBoutiqueISI.setHorizontalAlignment(JLabel.LEFT);
        panelPrincipal.add(barreBoutiqueISI);

        final JPanel panelISI = new JPanel();
        panelISI.setLayout(new FlowLayout(FlowLayout.LEADING));
        panelISI.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        panelPrincipal.add(panelISI);

        final JLabel emptyLabel = new JLabel();
        emptyLabel.setBorder(new EmptyBorder(0, 23, 0, 0));
        panelISI.add(emptyLabel);

        final JLabel skinIsi1 = new JLabel();
        skinIsi1.setIcon(Utils.getImageToSize("smartphone.png", 130, 130));
        skinIsi1.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));
        panelISI.add(skinIsi1);

        final JLabel skinIsi2 = new JLabel();
        skinIsi2.setIcon(Utils.getImageToSize("tablette.png", 130, 130));
        skinIsi2.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));
        panelISI.add(skinIsi2);


        this.add(panelHaut, BorderLayout.NORTH);
        this.add(panelPrincipal, BorderLayout.CENTER);
    }
}
