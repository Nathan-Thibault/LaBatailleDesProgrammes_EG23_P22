package fr.utt.eg23.labattailledesprogrammes;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		JLabel labelImageTitre = new JLabel("");
		
		BlinkLabel continueText = new BlinkLabel("    Appuyez sur espace pour continuer...", 500);
		continueText.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 25));
		continueText.setForeground(Color.WHITE);
		
		JPanel continuePanel = new JPanel();
		continuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		continuePanel.setBackground(COLOR_BACKGROUND);
		continuePanel.add(continueText);
		
		frame = new JFrame("La Bataille des Programmes");
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					continueText.setVisible(false);
					//connexion popup
					ConnexionPopup dialog = new ConnexionPopup();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setAlwaysOnTop(true);
				}
			}
		});
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(labelImageTitre, BorderLayout.CENTER);
        frame.getContentPane().add(continuePanel, BorderLayout.SOUTH);
        
        //resize title image to make room for continue text
        int height = frame.getHeight() - 25;
        labelImageTitre.setIcon(getImageToSize("ecranTitre.jpg", (int) ((float) 1280 * (float) height / (float) 720), height));
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
	
	//label with timer and modified paint() method to make text blinking
	@SuppressWarnings("serial")
	protected static class BlinkLabel extends JLabel {

        private boolean on = true;
        private int tCount = 0;

        public BlinkLabel(String text, int timeInMillis) {
            super(text);
        	
            Timer timer = new Timer(timeInMillis, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	if(on && tCount == 0) {//make text appears only 2 timer loops out of 3
                		tCount++;
                	}else {
                		tCount = 0;
                		on = !on;
                		repaint();
                	}
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            if (!on) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(0f));
            } else {
                g2d.setComposite(AlphaComposite.SrcOver.derive(1f));
            }
            super.paint(g2d); 
            g2d.dispose();
        }

    }
}
