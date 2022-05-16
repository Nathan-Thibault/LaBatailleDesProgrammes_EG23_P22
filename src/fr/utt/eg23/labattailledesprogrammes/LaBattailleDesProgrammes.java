package fr.utt.eg23.labattailledesprogrammes;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LaBattailleDesProgrammes {

	private JFrame frame;
	
	public final static Color COLOR_BACKGROUND = new Color(55, 60, 72);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaBattailleDesProgrammes window = new LaBattailleDesProgrammes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LaBattailleDesProgrammes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("La Bataille des Programmes");
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelImageTitre = new JLabel("");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		labelImageTitre.setIcon(getImageToSize("ecranTitre.jpg", (int) screenSize.getWidth(), (int) screenSize.getHeight()));
		frame.getContentPane().add(labelImageTitre, BorderLayout.CENTER);
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
