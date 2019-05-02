package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;

import javax.swing.*;

public class LoadingScreen extends JPanel {
	
	private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private JButton playButton = new JButton("Play");
	

	
	public LoadingScreen() {
		playButton.setEnabled(false);
		ImageIcon baseGameBoard = createImageIcon(baseImagePath + "blackjacklogo.png");
		panelBG.setLayout(new BorderLayout());
		panelBG.setIcon(baseGameBoard);
		panelBG.add(playButton, BorderLayout.SOUTH);
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


	public void setPlayButtonStatus(boolean status) {
		if (status) {
			playButton.setEnabled(true);
		} else {
			playButton.setEnabled(false);
		}
	}	
}
