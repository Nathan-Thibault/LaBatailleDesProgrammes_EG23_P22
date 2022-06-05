package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.UTTBranch;
import fr.utt.eg23.labatailledesprogrammes.Utils;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.CustomProgressBar;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.DefaultLabel;
import fr.utt.eg23.labatailledesprogrammes.screens.TroopConfiguration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;


public class MatchmakingPopup extends JDialog {
    public MatchmakingPopup() {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 550;
        int height = 300;
        setBounds(center.x - (width / 2), center.y - (height / 2), width, height);
        setTitle("Matchmaking");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JComboBox<UTTBranch> branchComboBox = new JComboBox<>(UTTBranch.values());
        JComboBox<ImageIcon> skinComboBox = new JComboBox<>();

        branchComboBox.addActionListener(e -> {
            skinComboBox.removeAllItems();
            String[] fileNames = ((UTTBranch) Objects.requireNonNull(branchComboBox.getSelectedItem())).getSkinsFileNames();
            for (String fileName : fileNames) {
                skinComboBox.addItem(Utils.getImageToSize("shop/" + fileName, 50, 50));
            }
        });
        branchComboBox.setSelectedIndex(0);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(null);
        leftPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
        leftPanel.add(new DefaultLabel("Choix de la branche"), BorderLayout.NORTH);
        leftPanel.add(branchComboBox, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(null);
        rightPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        rightPanel.add(new DefaultLabel("Choix du skin"), BorderLayout.NORTH);
        rightPanel.add(skinComboBox);

        JPanel selectionPanel = new JPanel(new BorderLayout());
        selectionPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        selectionPanel.add(leftPanel, BorderLayout.WEST);
        selectionPanel.add(rightPanel, BorderLayout.CENTER);
        getContentPane().add(selectionPanel);

        JSeparator line = new JSeparator();
        line.setBackground(null);
        line.setBorder(new LineBorder(Color.BLACK));
        getContentPane().add(line);

        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        progressPanel.setBorder(new EmptyBorder(5, 20, 10, 20));
        getContentPane().add(progressPanel);

        JLabel progressLabel = new DefaultLabel("");
        progressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        progressPanel.add(progressLabel, BorderLayout.NORTH);

        CustomProgressBar progressBar = new CustomProgressBar();
        progressBar.setProgressColor(Color.GREEN);
        progressBar.setBarsVisible(false);
        progressBar.setStringVisible(false);

        Timer t = new Timer(50, et -> {
            int v = progressBar.getValue() + 1;
            int max = progressBar.getMaximum();

            if (v >= max) {
                ((Timer) et.getSource()).stop();
                progressLabel.setText("Partie trouvée ! Démarrage !");
                TroopConfiguration tc = new TroopConfiguration((UTTBranch) branchComboBox.getSelectedItem());
                LaBatailleDesProgrammes.getInstance().setTroopConfiguration(tc);
                LaBatailleDesProgrammes.getInstance().switchPanel(tc);
                dispose();
            } else {
                progressBar.setValue(v);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        getContentPane().add(buttonPanel);

        JButton btn = new JButton("Lancer la recherche");
        btn.setFont(LaBatailleDesProgrammes.GAME_FONT);
        btn.setBackground(Color.GRAY);
        btn.setForeground(Color.BLACK);
        btn.setBorder(new EmptyBorder(5, 5, 5, 5));
        btn.addActionListener(eb -> {
            if (btn.getText().equals("Lancer la recherche")) {
                btn.setText("Annuler");
                branchComboBox.setEnabled(false);
                skinComboBox.setEnabled(false);
                progressLabel.setText("Recherche en cours...");
                progressPanel.add(progressBar);
                progressBar.setValue(0);
                t.setCoalesce(true);
                t.setRepeats(true);
                t.start();
            } else {
                t.stop();
                btn.setText("Lancer la recherche");
                branchComboBox.setEnabled(true);
                skinComboBox.setEnabled(true);
                progressLabel.setText("Recherche annulée");
                progressPanel.remove(progressBar);
            }
            progressPanel.revalidate();
            progressPanel.repaint();
        });
        buttonPanel.add(btn);

        setVisible(true);
    }
}
