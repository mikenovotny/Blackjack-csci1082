package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;

import javax.swing.*;

public class LoadingScreen extends JPanel {

	private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private JButton playButton = new JButton();
	

	
	public LoadingScreen() {
		ImageIcon baseGameBoard = createImageIcon(baseImagePath + "blackjacklogo.png");
		panelBG.setIcon(baseGameBoard);
		this.setLayout(new BorderLayout());
		this.add(panelBG, BorderLayout.CENTER);
		validate();
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
