package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
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
	private JPanel seat0Panel = new JPanel(new BorderLayout());
	private JPanel seat1Panel = new JPanel(new BorderLayout());
	private JPanel seat2Panel = new JPanel(new BorderLayout());
	private JPanel seat3Panel = new JPanel(new BorderLayout());
	private JPanel seat4Panel = new JPanel(new BorderLayout());
	private JPanel seat5Panel = new JPanel(new BorderLayout());
	private JPanel seat6Panel = new JPanel(new BorderLayout());
	private JPanel seat7Panel = new JPanel(new BorderLayout());
	private JPanel seat0Cards = new JPanel(new FlowLayout());
	private JPanel seat1Cards = new JPanel(new FlowLayout());
	private JPanel seat2Cards = new JPanel(new FlowLayout());
	private JPanel seat3Cards = new JPanel(new FlowLayout());
	private JPanel seat4Cards = new JPanel(new FlowLayout());
	private JPanel seat5Cards = new JPanel(new FlowLayout());
	private JPanel seat6Cards = new JPanel(new FlowLayout());
	private JPanel seat7Cards = new JPanel(new FlowLayout());
	private JLabel seat0Bet = new JLabel();
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
	private BufferedImage gameBoardBackground = null;

	
	public GameBoard() {
		buildWinPanel();
		buildBottomPanel();
		//try {
	//		gameBoardBackground = ImageIO.read(getClass().getResource("/images/gameBoard.jpg"));
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		//panelBG.setIcon(baseGameBoard);
		this.setPreferredSize(new Dimension(1200,700));
		this.setLayout(new BorderLayout());
		this.add(winPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.CENTER);
		this.validate();
	}
	
	
	private void buildBottomPanel() {
		buildleftPanel();
		buildRightPanel();
		buildCenterPanel();
		bottomPanel.add(leftPanel, BorderLayout.WEST);
		bottomPanel.add(rightPanel, BorderLayout.EAST);
		bottomPanel.add(centerPanel, BorderLayout.CENTER);
		bottomPanel.setBackground(new Color(0,0,0,10));
		bottomPanel.setOpaque(false);
		bottomPanel.setVisible(true);
		
	}


	private void buildCenterPanel() {
		buildSeat5Panel();
		buildSeat4Panel();
		buildSeat3Panel();
		centerPanel.add(seat5Panel);
		centerPanel.add(seat4Panel);
		centerPanel.add(seat3Panel);
		centerPanel.setBackground(new Color(0,0,0,10));
		centerPanel.setOpaque(false);
		centerPanel.setVisible(true);
	}


	private void buildSeat3Panel() {
		seat3Panel.add(seat3Bet, BorderLayout.NORTH);
		seat3Panel.add(seat3Cards, BorderLayout.CENTER);
		seat3Panel.setOpaque(false);
		seat3Panel.setBackground(new Color(0,0,0,10));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat4Panel() {
		seat4Panel.add(seat4Bet, BorderLayout.NORTH);
		seat4Panel.add(seat4Cards, BorderLayout.CENTER);
		seat4Panel.setOpaque(false);
		seat4Panel.setBackground(new Color(0,0,0,10));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat5Panel() {
		seat5Panel.add(seat5Bet, BorderLayout.NORTH);
		seat5Panel.add(seat5Cards, BorderLayout.CENTER);
		seat5Panel.setOpaque(false);
		seat5Panel.setBackground(new Color(0,0,0,10));
		seat5Panel.setVisible(true);
		
	}


	private void buildRightPanel() {
		buildSeat1Panel();
		buildSeat2Panel();
		rightPanel.add(seat1Panel);
		rightPanel.add(seat2Panel);
		rightPanel.setOpaque(false);
		rightPanel.setBackground(new Color(0,0,0,10));
		rightPanel.setVisible(true);
		
	}


	private void buildSeat2Panel() {
		seat2Panel.add(seat2Bet, BorderLayout.WEST);
		seat2Panel.add(seat2Cards, BorderLayout.CENTER);
		seat2Panel.setOpaque(false);
		seat2Panel.setBackground(new Color(0,0,0,10));
		seat2Panel.setVisible(true);
		
	}


	private void buildSeat1Panel() {
		seat1Panel.add(seat1Bet, BorderLayout.WEST);
		seat1Panel.add(seat1Cards, BorderLayout.CENTER);
		seat1Panel.setOpaque(false);
		seat1Panel.setBackground(new Color(0,0,0,10));
		seat1Panel.setVisible(true);
		
	}


	private void buildleftPanel() {
		buildSeat7Panel();
		buildSeat6Panel();
		leftPanel.add(seat7Panel);
		leftPanel.add(seat6Panel);
		leftPanel.setOpaque(false);
		leftPanel.setBackground(new Color(0,0,0,10));
		leftPanel.setVisible(true);
		
	}


	private void buildSeat6Panel() {
		seat6Panel.add(seat6Bet, BorderLayout.EAST);
		seat6Panel.add(seat6Cards, BorderLayout.CENTER);
		seat6Panel.setOpaque(false);
		seat6Panel.setBackground(new Color(0,0,0,10));
		seat6Panel.setVisible(true);
		
	}


	private void buildSeat7Panel() {
		seat7Panel.add(seat7Bet, BorderLayout.EAST);
		seat7Panel.add(seat7Cards, BorderLayout.CENTER);
		seat7Panel.setOpaque(false);
		seat7Panel.setBackground(new Color(0,0,0,10));
		seat7Panel.setVisible(true);
		
	}


	private void buildWinPanel() {
		winLabel.setVisible(false);
		winLabel.setSize(150, 150);
		winPanel.add(winLabel);	
		winPanel.setBackground(new Color(0,0,0,10));
		winPanel.setOpaque(false);
	}

	public void updateCardPanel(int seat, JLabel card) {
		switch (seat) {
		case 0:
			seat0Cards.add(card);
			seat0Cards.revalidate();
			//seat0Cards.repaint();
			break;
		case 1:
			seat1Cards.add(card);
			seat1Panel.revalidate();
			//seat1Cards.repaint();
			break;
		case 2:
			seat2Cards.add(card);
			seat2Panel.revalidate();
			//seat2Cards.repaint();
			break;
		case 3:
			seat3Cards.add(card);
			seat3Cards.revalidate();
			//seat3Cards.repaint();
			break;
		case 4:
			seat4Cards.add(card);
			seat4Cards.revalidate();
			//seat4Cards.repaint();
			break;
		case 5:
			seat5Cards.add(card);
			seat5Cards.revalidate();
			//seat5Cards.repaint();
			break;
		case 6:
			seat6Cards.add(card);
			seat6Cards.revalidate();
			seat6Cards.repaint();
			break;
		case 7:
			seat7Cards.add(card);
			seat7Cards.revalidate();
			seat7Cards.repaint();
			break;
		}
		
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
	
	//@Override
	//protected void paintComponent(Graphics g) {
	////	super.paintComponent(g);
	//		g.drawImage(gameBoardBackground, 0, 0, null);
	//	
//	}
	
}
