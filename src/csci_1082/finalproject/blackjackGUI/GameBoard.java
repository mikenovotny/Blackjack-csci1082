package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameBoard extends JPanel {
	
	private JPanel winPanel = new JPanel();
	private JLabel winLabel = new JLabel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel leftPanel = new JPanel(new GridLayout(3,1));
	private JPanel rightPanel = new JPanel(new GridLayout(3,1));
	private JPanel centerPanel = new JPanel(new GridLayout(1,3));
	private JPanel seat1Panel = new JPanel(new BorderLayout());
	private JPanel seat2Panel = new JPanel(new BorderLayout());
	private JPanel seat3Panel = new JPanel(new BorderLayout());
	private JPanel seat4Panel = new JPanel(new BorderLayout());
	private JPanel seat5Panel = new JPanel(new BorderLayout());
	private JPanel seat6Panel = new JPanel(new BorderLayout());
	private JPanel seat7Panel = new JPanel(new BorderLayout());
	private JPanel seat1Cards = new JPanel();
	private JPanel seat2Cards = new JPanel();
	private JPanel seat3Cards = new JPanel();
	private JPanel seat4Cards = new JPanel();
	private JPanel seat5Cards = new JPanel();
	private JPanel seat6Cards = new JPanel();
	private JPanel seat7Cards = new JPanel();
	private JLabel seat1Bet = new JLabel();
	private JLabel seat2Bet = new JLabel();
	private JLabel seat3Bet = new JLabel();
	private JLabel seat4Bet = new JLabel();
	private JLabel seat5Bet = new JLabel();
	private JLabel seat6Bet = new JLabel();
	private JLabel seat7Bet = new JLabel();
	
	
	//private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private JLabel panelBG = new JLabel();
	private ImageIcon baseGameBoard;

	
	public GameBoard() {
		buildWinPanel();
		buildBottomPanel();
		baseGameBoard = createImageIcon(baseImagePath + "gameBoard.jpg");
		panelBG.setIcon(baseGameBoard);
		this.setLayout(new BorderLayout());
		this.add(panelBG, BorderLayout.CENTER);
	}
	
	
	private void buildBottomPanel() {
		buildleftPanel();
		buildRightPanel();
		buildCenterPanel();
		bottomPanel.add(leftPanel, BorderLayout.WEST);
		bottomPanel.add(rightPanel, BorderLayout.EAST);
		bottomPanel.add(centerPanel, BorderLayout.CENTER);
		bottomPanel.setOpaque(false);
		
	}


	private void buildCenterPanel() {
		buildSeat5Panel();
		buildSeat4Panel();
		buildSeat3Panel();
		centerPanel.add(seat5Panel);
		centerPanel.add(seat4Panel);
		centerPanel.add(seat3Panel);
		centerPanel.setOpaque(false);
		
	}


	private void buildSeat3Panel() {
		seat3Panel.add(seat3Bet, BorderLayout.NORTH);
		seat3Panel.add(seat3Cards, BorderLayout.CENTER);
		seat3Panel.setOpaque(false);
		
	}


	private void buildSeat4Panel() {
		seat4Panel.add(seat4Bet, BorderLayout.NORTH);
		seat4Panel.add(seat4Cards, BorderLayout.CENTER);
		seat4Panel.setOpaque(false);
		
	}


	private void buildSeat5Panel() {
		seat5Panel.add(seat5Bet, BorderLayout.NORTH);
		seat6Panel.add(seat5Cards, BorderLayout.CENTER);
		seat6Panel.setOpaque(false);
		
	}


	private void buildRightPanel() {
		buildSeat1Panel();
		buildSeat2Panel();
		rightPanel.add(seat1Panel);
		rightPanel.add(seat2Panel);
		rightPanel.setOpaque(false);
		
	}


	private void buildSeat2Panel() {
		seat2Panel.add(seat2Bet, BorderLayout.WEST);
		seat2Panel.add(seat2Cards, BorderLayout.CENTER);
		seat2Panel.setOpaque(false);
		
	}


	private void buildSeat1Panel() {
		seat1Panel.add(seat1Bet, BorderLayout.WEST);
		seat1Panel.add(seat1Cards, BorderLayout.CENTER);
		seat1Panel.setOpaque(false);
		
	}


	private void buildleftPanel() {
		buildSeat7Panel();
		buildSeat6Panel();
		leftPanel.add(seat7Panel);
		leftPanel.add(seat6Panel);
		leftPanel.setOpaque(false);
		
	}


	private void buildSeat6Panel() {
		seat6Panel.add(seat6Bet, BorderLayout.EAST);
		seat6Panel.add(seat6Cards, BorderLayout.CENTER);
		seat6Panel.setOpaque(false);
		
	}


	private void buildSeat7Panel() {
		seat7Panel.add(seat7Bet, BorderLayout.EAST);
		seat7Panel.add(seat7Cards, BorderLayout.CENTER);
		seat7Panel.setOpaque(false);
		
	}


	private void buildWinPanel() {
		winLabel.setVisible(false);
		winLabel.setSize(150, 150);
		winPanel.add(winLabel);	
		winPanel.setOpaque(false);
	}


	private ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
				return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find File: " + path);
		}
		return null;
	}
	
}
