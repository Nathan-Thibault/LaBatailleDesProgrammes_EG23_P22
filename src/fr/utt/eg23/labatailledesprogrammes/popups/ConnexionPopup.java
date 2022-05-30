package fr.utt.eg23.labatailledesprogrammes.popups;

import fr.utt.eg23.labatailledesprogrammes.LaBatailleDesProgrammes;
import fr.utt.eg23.labatailledesprogrammes.screens.MainMenu;
import fr.utt.eg23.labatailledesprogrammes.customcomponents.TextButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class ConnexionPopup extends JDialog {

	public ConnexionPopup() {
		setBounds(100, 100, 450, 200);
		setTitle("Connexion");
		getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPanel.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel labelID = new JLabel("Identifiant : ");
		labelID.setFont(LaBatailleDesProgrammes.GAME_FONT);
		labelID.setForeground(Color.WHITE);
		contentPanel.add(labelID);

		JTextField textFieldID = new JTextField();
		textFieldID.setText("xXx_darkEG23_xXx");
		textFieldID.setBorder(null);
		contentPanel.add(textFieldID);
		
		JLabel labelPassword = new JLabel("Mot de passe :");
		labelPassword.setFont(LaBatailleDesProgrammes.GAME_FONT);
		labelPassword.setForeground(Color.WHITE);
		contentPanel.add(labelPassword);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBorder(null);
		contentPanel.add(passwordField);
		
		JCheckBox checkBoxRemember = new JCheckBox("Se souvenir de moi");
		checkBoxRemember.setSelected(true);
		checkBoxRemember.setBorder(new EmptyBorder(10,0,0,0));
		checkBoxRemember.setBackground(null);
		checkBoxRemember.setForeground(Color.WHITE);
		contentPanel.add(checkBoxRemember);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(LaBatailleDesProgrammes.COLOR_BACKGROUND);
		buttonPane.setLayout(new BorderLayout());
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnLostPassword = new JButton("Mot de passe oublié ?");
		//underlined font
		Font font = new Font("Verdana Pro", Font.BOLD , 10);
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); 
		btnLostPassword.setFont(font.deriveFont(attributes));
		btnLostPassword.setForeground(Color.GRAY);
		btnLostPassword.setBackground(null);
		btnLostPassword.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPane.add(btnLostPassword, BorderLayout.WEST);
		btnLostPassword.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnLostPassword.setForeground(Color.CYAN);		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnLostPassword.setForeground(Color.GRAY);
		    }
		});
		
		JButton okButton = new TextButton("Valider", 15f, () -> {LaBatailleDesProgrammes.getInstance().switchPanel(new MainMenu());});
		okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton, BorderLayout.EAST);
		getRootPane().setDefaultButton(okButton);
	}

}
