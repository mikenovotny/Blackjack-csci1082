package csci_1082.finalproject.blackjackGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class BetPanel extends JPanel {
		
	private BufferedImage betPanelBackground = null;
	JLabel betDisplay = new JLabel("0");
	

	public BetPanel() {
		try {
			betPanelBackground = ImageIO.read(getClass().getResource("/images/pokerchip.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(175, 175));
		betDisplay.setPreferredSize(new Dimension(75,75));
		betDisplay.setFont(new Font("Arial Black", Font.PLAIN, 25));
		betDisplay.setBackground(new Color(0, 0, 0, 0));
		betDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		betDisplay.setVerticalAlignment(SwingConstants.CENTER);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createRigidArea(new Dimension(175, 63)));
		this.add(betDisplay);
		this.add(Box.createRigidArea(new Dimension(175, 63)));
		this.setBackground(Color.BLACK);
		this.setVisible(true);		
	}
	
	
	
	public JLabel getBetDisplay() {
		return betDisplay;
	}



	public void setBetDisplay(JLabel betDisplay) {
		this.betDisplay = betDisplay;
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.drawImage(betPanelBackground, 0, 0, null);
	}
}



