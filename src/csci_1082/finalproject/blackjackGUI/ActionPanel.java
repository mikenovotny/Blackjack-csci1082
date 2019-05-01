package csci_1082.finalproject.blackjackGUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ActionPanel extends JPanel {
	
	// Hand action panel components
	private String baseImagePath = "/images/"; 
	private JButton hitButton = new JButton();
	private JButton standButton = new JButton();
	private JButton doubleButton = new JButton();
	private JButton splitButton = new JButton();
	
	// Bet panel components
	private JTextField betAmount = new JTextField();
	private JButton increaseBet = new JButton("+");
	private JButton decreaseBet = new JButton("-");
	private JButton betButton = new JButton("BET");
	private JPanel betSubPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
	private JPanel betSubPanel2 = new JPanel(new BorderLayout());
	
	// Menu Panel
	private JButton cashOut = new JButton("Cash Out");
	private JTextArea handHistory = new JTextArea();
	
	// Panels
	private JPanel handActionPanel = new JPanel(new GridLayout(1, 4, 10, 10));
	private JPanel betPanel = new JPanel(new GridLayout(2, 1, 5, 5));
	private JPanel menuPanel = new JPanel(new BorderLayout());
	
	// Constructor
	public ActionPanel() {
		buildHandActionPanel();
		buildBetPanel();
		buildMenuPanel();
		this.setBackground(Color.BLACK);
		this.add(menuPanel);
		this.add(betPanel);
		this.add(handActionPanel);
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
	
	private void formatButton(JButton button, ImageIcon baseIcon, ImageIcon pressedIcon, ImageIcon disabledIcon) {
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setIcon(baseIcon);
		button.setPressedIcon(pressedIcon);
		button.setDisabledIcon(disabledIcon);
	}

	
	private void buildHandActionPanel() {
		// Format and Add Split Button
		ImageIcon splitButtonIcon = createImageIcon(baseImagePath + "splitButtonIcon.jpg");
		ImageIcon splitButtonIconPressed = createImageIcon(baseImagePath + "splitButtonIconPressed.jpg");
		ImageIcon splitButtonIconDisabled = createImageIcon(baseImagePath + "splitButtonDisabled.jpg");
		formatButton(splitButton, splitButtonIcon, splitButtonIconPressed, splitButtonIconDisabled);
		//splitButton.setEnabled(false);
		handActionPanel.add(splitButton);
				
		// Format and Add Double Down button
		ImageIcon doubleDownIcon = createImageIcon(baseImagePath + "doubleDownIcon.jpg");
		ImageIcon doubleDownIconPressed = createImageIcon(baseImagePath + "doubleDownIconPressed.jpg");
		ImageIcon doubleDownIconDisabled = createImageIcon(baseImagePath + "doubleDownDisabled.jpg");
		formatButton(doubleButton, doubleDownIcon, doubleDownIconPressed, doubleDownIconDisabled);
		handActionPanel.add(doubleButton);
		
		// Format and add Stand button
		ImageIcon standButtonIcon = createImageIcon(baseImagePath + "standButtonIcon.jpg");
		ImageIcon standButtonIconPressed = createImageIcon(baseImagePath + "standButtonIconPressed.jpg");
		ImageIcon standButtonIconDisabled = createImageIcon(baseImagePath + "standButtonDisabled.jpg");
		formatButton(standButton, standButtonIcon, standButtonIconPressed, standButtonIconDisabled);
		handActionPanel.add(standButton);
		
		// Format and add Hit button
		ImageIcon hitButtonIcon = createImageIcon(baseImagePath + "hitButtonIcon.jpg");
		ImageIcon hitButtonIconPressed = createImageIcon(baseImagePath + "hitButtonIconPressed.jpg");
		ImageIcon hitButtonIconDisabled = createImageIcon(baseImagePath + "hitButtonDisabled.jpg");
		formatButton(hitButton, hitButtonIcon, hitButtonIconPressed, hitButtonIconDisabled);
		handActionPanel.add(hitButton);		
		
		handActionPanel.setBackground(Color.BLACK);
	}
	
	private void buildBetPanel() {
		buildBetSubPanel();
		betPanel.add(betSubPanel2);
		betPanel.add(betButton);
		betPanel.setBackground(Color.BLACK);
	}
	
	private void buildBetSubPanel() {
		betSubPanel1.add(increaseBet);
		betSubPanel1.add(decreaseBet);
		betSubPanel2.add(betSubPanel1, BorderLayout.SOUTH);
		betSubPanel2.add(betAmount, BorderLayout.CENTER);	
		betSubPanel2.setBackground(Color.BLACK);
	}
	
	
	private void buildMenuPanel() {
		menuPanel.add(cashOut, BorderLayout.SOUTH);
		menuPanel.add(handHistory, BorderLayout.CENTER);
		menuPanel.setBackground(Color.BLACK);
	}
}
