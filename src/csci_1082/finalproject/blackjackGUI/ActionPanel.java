package csci_1082.finalproject.blackjackGUI;

import java.awt.*;
import javax.swing.*;


public class ActionPanel extends JPanel {
	
	// Hand action panel components
	private JButton hitButton = new JButton("HIT");
	private JButton standButton = new JButton("STAND");
	private JButton doubleButton = new JButton("DOUBLE");
	private JButton splitButton = new JButton("SPLIT");
	
	// Bet panel components
	private JTextField betAmount = new JTextField("0");
	private JButton increaseBet = new JButton("+");
	private JButton decreaseBet = new JButton("-");
	private JButton betButton = new JButton("BET");
	private JPanel betSubPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
	private JPanel betSubPanel2 = new JPanel(new BorderLayout());
	
	// Menu Panel
	private JButton cashOut = new JButton("Cash Out");
	private JTextArea gameHistory = new JTextArea(5,40);
	
	
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
	}
	
	
	
	public JButton getHitButton() {
		return hitButton;
	}



	public void setHitButton(JButton hitButton) {
		this.hitButton = hitButton;
	}



	public JButton getStandButton() {
		return standButton;
	}



	public void setStandButton(JButton standButton) {
		this.standButton = standButton;
	}



	public JButton getDoubleButton() {
		return doubleButton;
	}



	public void setDoubleButton(JButton doubleButton) {
		this.doubleButton = doubleButton;
	}



	public JButton getSplitButton() {
		return splitButton;
	}



	public void setSplitButton(JButton splitButton) {
		this.splitButton = splitButton;
	}



	public JButton getIncreaseBet() {
		return increaseBet;
	}



	public void setIncreaseBet(JButton increaseBet) {
		this.increaseBet = increaseBet;
	}



	public JButton getDecreaseBet() {
		return decreaseBet;
	}



	public void setDecreaseBet(JButton decreaseBet) {
		this.decreaseBet = decreaseBet;
	}



	public JButton getBetButton() {
		return betButton;
	}


	public JTextArea getGameHistory() {
		return gameHistory;
	}


	
	public JTextField getBetAmount() {
		return betAmount;
	}



	public void setBetAmount(double betAmount) {
		this.betAmount.setText(Double.toString(betAmount));
	}
	
	public void clearBetAmount() {
		this.betAmount.setText("0");
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
		
		/*
	 	ImageIcon splitButtonIcon = createImageIcon(baseImagePath + "splitButtonIcon.jpg");
		ImageIcon splitButtonIconPressed = createImageIcon(baseImagePath + "splitButtonIconPressed.jpg");
		ImageIcon splitButtonIconDisabled = createImageIcon(baseImagePath + "splitButtonDisabled.jpg");
		formatButton(splitButton, splitButtonIcon, splitButtonIconPressed, splitButtonIconDisabled);
		*/
		//splitButton.setEnabled(false);
		handActionPanel.add(splitButton);
				
		// Format and Add Double Down button
		/*
		ImageIcon doubleDownIcon = createImageIcon(baseImagePath + "doubleDownIcon.jpg");
		ImageIcon doubleDownIconPressed = createImageIcon(baseImagePath + "doubleDownIconPressed.jpg");
		ImageIcon doubleDownIconDisabled = createImageIcon(baseImagePath + "doubleDownDisabled.jpg");
		formatButton(doubleButton, doubleDownIcon, doubleDownIconPressed, doubleDownIconDisabled);
		*/
		handActionPanel.add(doubleButton);
		
		// Format and add Stand button
		/*
		ImageIcon standButtonIcon = createImageIcon(baseImagePath + "standButtonIcon.jpg");
		ImageIcon standButtonIconPressed = createImageIcon(baseImagePath + "standButtonIconPressed.jpg");
		ImageIcon standButtonIconDisabled = createImageIcon(baseImagePath + "standButtonDisabled.jpg");
		formatButton(standButton, standButtonIcon, standButtonIconPressed, standButtonIconDisabled);
		*/
		handActionPanel.add(standButton);
		
		// Format and add Hit button
		/*
		ImageIcon hitButtonIcon = createImageIcon(baseImagePath + "hitButtonIcon.jpg");
		ImageIcon hitButtonIconPressed = createImageIcon(baseImagePath + "hitButtonIconPressed.jpg");
		ImageIcon hitButtonIconDisabled = createImageIcon(baseImagePath + "hitButtonDisabled.jpg");
		formatButton(hitButton, hitButtonIcon, hitButtonIconPressed, hitButtonIconDisabled);
		*/
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
		menuPanel.add(new JScrollPane(gameHistory), BorderLayout.CENTER);
		menuPanel.setBackground(Color.BLACK);
	}
}
