package csci_1082.finalproject.blackjackGUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Background extends JPanel {
	
	private BufferedImage gameBoardBackground = null;

	public Background() {
		try {
			gameBoardBackground = ImageIO.read(getClass().getResource("/images/gameBoard.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.drawImage(gameBoardBackground, 0, 0, null);
	}
}
