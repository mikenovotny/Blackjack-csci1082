package csci_1082.finalproject.blackjackGUI;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameBoard extends JPanel {
	
	//private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private BufferedImage baseGameBoard;

	
	public GameBoard() {
		baseGameBoard = createImage(baseImagePath + "gameBoard.jpg");
		//panelBG.setIcon(baseGameBoard);
		this.setLayout(new GridBagLayout());
		//this.add(panelBG, BorderLayout.CENTER);
	}
	
	
	private BufferedImage createImage(String path) {
		URL imgURL = getClass().getResource(path);
		BufferedImage image = null;
		if (imgURL != null) {
			try {
				image = ImageIO.read(imgURL);
			} catch (IOException e) {
				System.err.println("Couldn't find File: " + path);
			}
		}
		return image;
	}
	
	@Override
	public void paintComponent(Graphics background)
	{
		super.paintComponent(background);
		background.drawImage(baseGameBoard, 0, 0, this);
	}
	
}
