package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.DefaultLabel;
import fr.utt.eg23.labatailledesprogrammes.screens.MainMenu;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ConnexionPopup extends JDialog {

    public ConnexionPopup() {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 450;
        int height = 200;
        setBounds(center.x - (width / 2), center.y - (height / 2), width, height);
        setTitle("Connexion");
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(new DefaultLabel("Identifiant : "));

        JTextField textFieldID = new JTextField();
        textFieldID.setText("xXx_darkEG23_xXx");
        textFieldID.setBorder(null);
        contentPanel.add(textFieldID);

        contentPanel.add(new DefaultLabel("Mot de passe :"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(null);
        contentPanel.add(passwordField);

        JCheckBox checkBoxRemember = new JCheckBox("Se souvenir de moi");
        checkBoxRemember.setSelected(true);
        checkBoxRemember.setBorder(new EmptyBorder(10, 0, 0, 0));
        checkBoxRemember.setBackground(null);
        checkBoxRemember.setForeground(Color.WHITE);
        contentPanel.add(checkBoxRemember);

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
        buttonPane.setLayout(new BorderLayout());
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        class CustomButton extends JButton {
            CustomButton(String text) {
                //underlined font
                Font font = new Font("Verdana Pro", Font.BOLD, 10);
                Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                setFont(font.deriveFont(attributes));
                setText(text);
                setForeground(Color.GRAY);
                setBackground(null);
                setBorder(new EmptyBorder(10, 10, 10, 10));
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        setForeground(Color.CYAN);
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        setForeground(Color.GRAY);
                    }
                });
            }
        }

        JButton btnLostPassword = new CustomButton("Mot de passe oublié ?");
        buttonPane.add(btnLostPassword, BorderLayout.WEST);

        JButton btnRegistration = new CustomButton("S'inscrire");
        buttonPane.add(btnRegistration, BorderLayout.CENTER);

        JButton okButton = new TextButton("Valider", 15f, () -> {
            LaBatailleDesProgrammes.getInstance().switchPanel(new MainMenu());
            dispose();
        });
        okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton, BorderLayout.EAST);
        getRootPane().setDefaultButton(okButton);

        setVisible(true);
    }
}
