package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import csci_1082.finalproject.blackjack.Player;
import csci_1082.finalproject.blackjack.PlayerType;

public class GameBoard extends JPanel {
	
	private JPanel winPanel = new JPanel();
	private JLabel winLabel = new JLabel();
	private JPanel gamePanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel dealerPanel = new JPanel(new FlowLayout());
	private JPanel seat0Panel = new JPanel(new BorderLayout());
	private JPanel seat1Panel = new JPanel(new BorderLayout());
	private JPanel seat2Panel = new JPanel(new BorderLayout());
	private JPanel seat3Panel = new JPanel(new BorderLayout());
	private JPanel seat4Panel = new JPanel(new BorderLayout());
	private JPanel seat5Panel = new JPanel(new BorderLayout());
	private JPanel seat6Panel = new JPanel(new BorderLayout());
	private JPanel seat7Panel = new JPanel(new BorderLayout());
	private JLayeredPane seat0Cards = new JLayeredPane();
	private JLayeredPane seat1Cards = new JLayeredPane();
	private JLayeredPane seat2Cards = new JLayeredPane();
	private JLayeredPane seat3Cards = new JLayeredPane();
	private JLayeredPane seat4Cards = new JLayeredPane();
	private JLayeredPane seat5Cards = new JLayeredPane();
	private JLayeredPane seat6Cards = new JLayeredPane();
	private JLayeredPane seat7Cards = new JLayeredPane();
	private ArrayList<JLabel> seatPlayerLabels = new ArrayList<JLabel>();
	private int seat0depth = 0;
	private int seat1depth = 0;
	private int seat2depth = 0;
	private int seat3depth = 0;
	private int seat4depth = 0;
	private int seat5depth = 0;
	private int seat6depth = 0;
	private int seat7depth = 0;
	private Point seat0point = new Point(5, 5);
	private Point seat1point = new Point(5, 5);
	private Point seat2point = new Point(5, 5);
	private Point seat3point = new Point(5, 5);
	private Point seat4point = new Point(5, 5);
	private Point seat5point = new Point(5, 5);
	private Point seat6point = new Point(5, 5);
	private Point seat7point = new Point(5, 5);
	
	public GameBoard(ArrayList<Player> playerlist) {
		createSeatLabels(playerlist);
		buildWinPanel();
		buildgamePanel();
		this.setMinimumSize(new Dimension(1200,800));
		this.setPreferredSize(new Dimension(1200,800));
		this.setLayout(new BorderLayout());
		this.add(gamePanel, BorderLayout.CENTER);
	}
	
	
	private void createSeatLabels(ArrayList<Player> playerList) {
		for (int seat = 0; seat < 9; seat++) {
			JLabel seatLabel = new JLabel();
			seatLabel.setMaximumSize(new Dimension(150, 50));
			seatLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
			seatLabel.setForeground(Color.BLACK);
			seatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			String seatLabelText = "<html>Seat " + seat;
			for (Player player : playerList) {
				if (player.getSeat() == seat) {
					if (player.getType() == PlayerType.DEALER) {
						seatLabelText = "Dealer";
					} else {
						seatLabelText += "<br>" + player.getPlayerName();
					}
				}
			}
			seatLabel.setText(seatLabelText);
			seatPlayerLabels.add(seatLabel);
		}
	}


	private void buildgamePanel() {
		configurePanelSizes();
		buildleftPanel();
		buildRightPanel();
		buildCenterPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
		gamePanel.add(leftPanel);
		gamePanel.add(centerPanel);
		gamePanel.add(rightPanel);
		gamePanel.setBackground(new Color(0,128,0,255));
		gamePanel.setVisible(true);
		
	}


	private void buildCenterPanel() {
		buildbottomPanel();
		buildDealerpanel();
		buildWinPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(dealerPanel);
		centerPanel.add(winPanel);
		centerPanel.add(bottomPanel);
	}


	private void buildDealerpanel() {
		seat0Panel.add(seatPlayerLabels.get(0), BorderLayout.NORTH);
		seat0Panel.add(seat0Cards, BorderLayout.CENTER);
		seat0Panel.setBackground(new Color(0,128,0,255));
		dealerPanel.add(seat0Panel);
		dealerPanel.setBackground(new Color(0,128,0,255));
		dealerPanel.setVisible(true);
		
	}


	private void configurePanelSizes() {
		dealerPanel.setPreferredSize(new Dimension(720, 300));
		bottomPanel.setPreferredSize(new Dimension(720, 300));
		winPanel.setPreferredSize(new Dimension(720, 200));
		seat0Panel.setPreferredSize(new Dimension(240, 300));
		seat1Panel.setPreferredSize(new Dimension(240, 300));
		seat2Panel.setPreferredSize(new Dimension(240, 300));
		seat3Panel.setPreferredSize(new Dimension(240, 300));
		seat4Panel.setPreferredSize(new Dimension(240, 300));
		seat5Panel.setPreferredSize(new Dimension(240, 300));
		seat6Panel.setPreferredSize(new Dimension(240, 300));
		seat7Panel.setPreferredSize(new Dimension(240, 300));
		seat0Cards.setPreferredSize(new Dimension(200,250));
		seat1Cards.setPreferredSize(new Dimension(200,250));
		seat2Cards.setPreferredSize(new Dimension(200,250));
		seat3Cards.setPreferredSize(new Dimension(200,250));
		seat4Cards.setPreferredSize(new Dimension(200,250));
		seat5Cards.setPreferredSize(new Dimension(200,250));
		seat6Cards.setPreferredSize(new Dimension(200,250));
		seat7Cards.setPreferredSize(new Dimension(200,250));
	}


	private void buildbottomPanel() {
		buildSeat5Panel();
		buildSeat4Panel();
		buildSeat3Panel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(seat5Panel);
		bottomPanel.add(seat4Panel);
		bottomPanel.add(seat3Panel);
		bottomPanel.setBackground(new Color(0,128,0,255));
		bottomPanel.setOpaque(false);
		bottomPanel.setVisible(true);
	}


	private void buildSeat3Panel() {
		seat3Panel.add(seatPlayerLabels.get(3), BorderLayout.NORTH);
		seat3Panel.add(seat3Cards, BorderLayout.CENTER);
		seat3Panel.setBackground(new Color(0,128,0,255));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat4Panel() {
		seat4Panel.add(seatPlayerLabels.get(4), BorderLayout.NORTH);
		seat4Panel.add(seat4Cards, BorderLayout.CENTER);
		seat4Panel.setBackground(new Color(0,128,0,255));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat5Panel() {
		seat5Panel.add(seatPlayerLabels.get(5), BorderLayout.NORTH);
		seat5Panel.add(seat5Cards, BorderLayout.CENTER);
		seat5Panel.setBackground(new Color(0,128,0,255));
		seat5Panel.setVisible(true);
		
	}


	private void buildRightPanel() {
		buildSeat1Panel();
		buildSeat2Panel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(Box.createRigidArea(new Dimension(240,100)));
		rightPanel.add(seat1Panel);
		rightPanel.add(seat2Panel);
		rightPanel.add(Box.createRigidArea(new Dimension(240,100)));
		rightPanel.setBackground(new Color(0,128,0,255));
		rightPanel.setVisible(true);
		
	}


	private void buildSeat2Panel() {
		seat2Panel.add(seatPlayerLabels.get(2), BorderLayout.NORTH);
		seat2Panel.add(seat2Cards, BorderLayout.CENTER);
		seat2Panel.setBackground(new Color(0,128,0,255));
		seat2Panel.setVisible(true);
		
	}


	private void buildSeat1Panel() {
		seat1Panel.add(seatPlayerLabels.get(1), BorderLayout.NORTH);
		seat1Panel.add(seat1Cards, BorderLayout.CENTER);
		seat1Panel.setBackground(new Color(0,128,0,255));
		seat1Panel.setVisible(true);
		
	}


	private void buildleftPanel() {
		buildSeat7Panel();
		buildSeat6Panel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createRigidArea(new Dimension(240,100)));
		leftPanel.add(seat7Panel);
		leftPanel.add(seat6Panel);
		leftPanel.add(Box.createRigidArea(new Dimension(240,100)));
		leftPanel.setBackground(new Color(0,128,0,255));
		leftPanel.setVisible(true);
		
	}


	private void buildSeat6Panel() {
		seat6Panel.add(seatPlayerLabels.get(6), BorderLayout.NORTH);
		seat6Panel.add(seat6Cards, BorderLayout.CENTER);
		seat6Panel.setBackground(new Color(0,128,0,255));
		seat6Panel.setVisible(true);
		
	}


	private void buildSeat7Panel() {
		seat7Panel.add(seatPlayerLabels.get(7), BorderLayout.NORTH);
		seat7Panel.add(seat7Cards, BorderLayout.CENTER);
		seat7Panel.setBackground(new Color(0,128,0,255));
		seat7Panel.setVisible(true);
		
	}


	private void buildWinPanel() {
		winLabel.setVisible(false);
		winLabel.setSize(150, 150);
		winPanel.add(winLabel);	
		winPanel.setBackground(new Color(0,128,0,255));
		//winPanel.setOpaque(false);
	}

	public void updateCardPanel(int seat, JLabel card) {
		setCardAttributes(seat, card);
		switch (seat) {
		case 0:
			seat0Cards.add(card, new Integer(seat0depth++));
			break;
		case 1:
			seat1Cards.add(card, new Integer(seat1depth++));
			break;
		case 2:
			seat2Cards.add(card, new Integer(seat2depth++));
			break;
		case 3:
			seat3Cards.add(card, new Integer(seat3depth++));
			break;
		case 4:
			seat4Cards.add(card, new Integer(seat4depth++));
			break;
		case 5:
			seat5Cards.add(card, new Integer(seat5depth++));
			break;
		case 6:
			seat6Cards.add(card, new Integer(seat6depth++));
			break;
		case 7:
			seat7Cards.add(card, new Integer(seat7depth++));
			break;
		}
		
	}
	
	private void setCardAttributes(int seat, JLabel card) {
		switch (seat) {
		case 0:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat0point.x, seat0point.y, 75, 105);
			seat0point.x += 20;
			seat0point.y += 20;
			break;
		case 1:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat1point.x, seat1point.y, 75, 105);
			seat1point.x += 20;
			seat1point.y += 20;
			break;
		case 2:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat2point.x, seat2point.y, 75, 105);
			seat2point.x += 20;
			seat2point.y += 20;
			break;
		case 3:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat3point.x, seat3point.y, 75, 105);
			seat3point.x += 20;
			seat3point.y += 20;
			break;
		case 4:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat4point.x, seat4point.y, 75, 105);
			seat4point.x += 20;
			seat4point.y += 20;
			break;
		case 5:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat5point.x, seat5point.y, 75, 105);
			seat5point.x += 20;
			seat5point.y += 20;
			break;
		case 6:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat6point.x, seat6point.y, 75, 105);
			seat6point.x += 20;
			seat6point.y += 20;
			break;
		case 7:
			card.setVerticalAlignment(JLabel.TOP);
			card.setHorizontalAlignment(JLabel.CENTER);
			card.setOpaque(true);
			card.setBounds(seat7point.x, seat7point.y, 75, 105);
			seat7point.x += 20;
			seat7point.y += 20;
			break;
		}
		
	}


	public void resetSeatJLIndicies() {
		seat0depth = 0;
		seat1depth = 0;
		seat2depth = 0;
		seat3depth = 0;
		seat4depth = 0;
		seat5depth = 0;
		seat6depth = 0;
		seat7depth = 0;
		seat0point.x = 5;
		seat0point.y = 5;
		seat1point.x = 5;
		seat1point.y = 5;
		seat2point.x = 5;
		seat2point.y = 5;
		seat3point.x = 5;
		seat3point.y = 5;
		seat4point.x = 5;
		seat4point.y = 5;
		seat5point.x = 5;
		seat5point.y = 5;
		seat6point.x = 5;
		seat6point.y = 5;
		seat7point.x = 5;
		seat7point.y = 5;
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
