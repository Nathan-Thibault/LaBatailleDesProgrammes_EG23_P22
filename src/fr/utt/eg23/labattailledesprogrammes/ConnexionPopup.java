package fr.utt.eg23.labattailledesprogrammes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JPasswordField passwordField;
	private final Font labelFont = new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 15);

	/**
	 * Create the dialog.
	 */
	public ConnexionPopup() {
		setBounds(100, 100, 450, 200);
		setTitle("Connexion");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPanel.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JLabel labelID = new JLabel("Identifiant : ");
		labelID.setFont(labelFont);
		labelID.setForeground(Color.WHITE);
		contentPanel.add(labelID);
		
		textFieldID = new JTextField();
		textFieldID.setText("xXx_darkEG23_xXx");
		textFieldID.setBorder(null);
		contentPanel.add(textFieldID);
		
		JLabel labelPassword = new JLabel("Mot de passe :");
		labelPassword.setFont(labelFont);
		labelPassword.setForeground(Color.WHITE);
		contentPanel.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		contentPanel.add(passwordField);
		
		JCheckBox checkBoxRemember = new JCheckBox("Se souvenir de moi");
		checkBoxRemember.setSelected(true);
		checkBoxRemember.setBorder(new EmptyBorder(10,0,0,0));
		checkBoxRemember.setBackground(null);
		checkBoxRemember.setForeground(Color.WHITE);
		contentPanel.add(checkBoxRemember);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);
		buttonPane.setLayout(new BorderLayout());
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnLostPassword = new JButton("Mot de passe oubli\u00E9 ?");
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
		
		JButton okButton = new JButton("Valider");
		okButton.setFont(labelFont);
		okButton.setForeground(Color.WHITE);
		okButton.setBackground(null);
		okButton.setBorder(new EmptyBorder(10, 10, 10, 20));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton, BorderLayout.EAST);
		//change text color when mouse over
		getRootPane().setDefaultButton(okButton);
		okButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        okButton.setForeground(Color.CYAN);		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        okButton.setForeground(Color.WHITE);
		    }
		});

	}

}
