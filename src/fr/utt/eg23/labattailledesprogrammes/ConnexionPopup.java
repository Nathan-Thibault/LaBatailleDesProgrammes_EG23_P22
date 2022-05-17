package fr.utt.eg23.labattailledesprogrammes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ConnexionPopup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JPasswordField passwordField;
	private final Font labelFont = new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 15);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnexionPopup dialog = new ConnexionPopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConnexionPopup() {
		setBounds(100, 100, 450, 200);
		setTitle("Connexion");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JLabel labelID = new JLabel("Identifiant : ");
		labelID.setFont(labelFont);
		labelID.setForeground(Color.WHITE);
		contentPanel.add(labelID);
		
		textFieldID = new JTextField();
		contentPanel.add(textFieldID);
		
		JLabel labelPassword = new JLabel("Mot de passe :");
		labelPassword.setFont(labelFont);
		labelPassword.setForeground(Color.WHITE);
		contentPanel.add(labelPassword);
		
		passwordField = new JPasswordField();
		contentPanel.add(passwordField);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(LaBattailleDesProgrammes.COLOR_BACKGROUND);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

}
