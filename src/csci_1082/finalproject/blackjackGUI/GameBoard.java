package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.*;

public class GameBoard extends JPanel {
	
	private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 

	
	public GameBoard() {
		ImageIcon baseGameBoard = createImageIcon(baseImagePath + "gameBoard.jpg");
		panelBG.setIcon(baseGameBoard);
		this.setLayout(new BorderLayout());
		this.add(panelBG, BorderLayout.CENTER);
	}
	
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find File: " + path);
			return null;
		}
	}
}
