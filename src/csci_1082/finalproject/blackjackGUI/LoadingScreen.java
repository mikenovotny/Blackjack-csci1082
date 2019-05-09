package csci_1082.finalproject.blackjackGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import csci_1082.finalproject.blackjack.PlayerType;

import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
public class LoadingScreen extends JPanel implements MouseListener {

	private BufferedImage backgroundImage = null;
	
	// Play Button Panel
	private JPanel playButtonPanel = new JPanel();
	private JButton playButton = new JButton("Play");

	// Seat Panel
	private JPanel seatPanel = new JPanel();
	private int seatChoices = 128;
	private PlayerType[] players = new PlayerType[7];

	// Seat 1
	private JPanel seat1Panel = new JPanel();
	private JLabel seat1Title = new JLabel("Seat 1", SwingConstants.CENTER);
	private JLabel seat1Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat1Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat1Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 2
	private JPanel seat2Panel = new JPanel();
	private JLabel seat2Title = new JLabel("Seat 2", SwingConstants.CENTER);
	private JLabel seat2Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat2Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat2Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 3
	private JPanel seat3Panel = new JPanel();
	private JLabel seat3Title = new JLabel("Seat 3", SwingConstants.CENTER);
	private JLabel seat3Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat3Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat3Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 4
	private JPanel seat4Panel = new JPanel();
	private JLabel seat4Title = new JLabel("Seat 4", SwingConstants.CENTER);
	private JLabel seat4Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat4Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat4Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 5
	private JPanel seat5Panel = new JPanel();
	private JLabel seat5Title = new JLabel("Seat 5", SwingConstants.CENTER);
	private JLabel seat5Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat5Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat5Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 6
	private JPanel seat6Panel = new JPanel();
	private JLabel seat6Title = new JLabel("Seat 6", SwingConstants.CENTER);
	private JLabel seat6Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat6Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat6Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	// Seat 7
	private JPanel seat7Panel = new JPanel();
	private JLabel seat7Title = new JLabel("Seat 7", SwingConstants.CENTER);
	private JLabel seat7Human = new JLabel("Human Player", SwingConstants.CENTER);
	private JLabel seat7Computer = new JLabel("Computer Player", SwingConstants.CENTER);
	private JLabel seat7Empty = new JLabel("Empty Seat", SwingConstants.CENTER);

	public LoadingScreen() {
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/images/blackjacklogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//buildArrayLists();
		buildPlayButtonPanel();
		buildSeatPanels();
		buildLoadingScreen();
	}

	private void buildSeatPanels() {
		buildSeat1Panel();
		buildSeat2Panel();
		buildSeat3Panel();
		buildSeat4Panel();
		buildSeat5Panel();
		buildSeat6Panel();
		buildSeat7Panel();
		buildSeatPanel();
	}
	
	private void buildPlayButtonPanel() {
		playButtonPanel.setPreferredSize(new Dimension(1200, 75));
		playButton.setPreferredSize(new Dimension(200,75));
		playButton.setFont(new Font("Arial Black", Font.PLAIN, 28));
		playButton.setEnabled(false);
		playButtonPanel.add(playButton);
		playButtonPanel.setBackground(new Color(0, 0, 0, 0));
		playButtonPanel.setVisible(true);
	}
	

	private void buildSeat1Panel() {
		seat1Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat1Panel.setPreferredSize(new Dimension(150,150));
		
		
		seat1Title.setPreferredSize(new Dimension(150,30));
		seat1Title.setOpaque(true);
		seat1Title.setForeground(Color.BLACK);
		seat1Title.setBackground(new Color(50, 109, 79, 150));
		seat1Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat1Human.setPreferredSize(new Dimension(150,30));
		seat1Human.setName("seat1Human");
		seat1Human.setOpaque(true);
		seat1Human.setForeground(Color.DARK_GRAY);
		seat1Human.setBackground(Color.LIGHT_GRAY);
		seat1Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat1Human.addMouseListener(this);
		
		seat1Computer.setPreferredSize(new Dimension(150,30));
		seat1Computer.setName("seat1Computer");
		seat1Computer.setOpaque(true);
		seat1Computer.setForeground(Color.DARK_GRAY);
		seat1Computer.setBackground(Color.LIGHT_GRAY);
		seat1Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat1Computer.addMouseListener(this);
		
		
		seat1Empty.setPreferredSize(new Dimension(150,30));
		seat1Empty.setName("seat1Empty");
		seat1Empty.setOpaque(true);
		seat1Empty.setForeground(Color.DARK_GRAY);
		seat1Empty.setBackground(Color.LIGHT_GRAY);
		seat1Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat1Empty.addMouseListener(this);
		
		seat1Panel.add(seat1Title);
		seat1Panel.add(seat1Human);
		seat1Panel.add(seat1Computer);
		seat1Panel.add(seat1Empty);	
		seat1Panel.setBackground(new Color(0, 0, 0, 0));
		seat1Panel.setVisible(true);
	}

	private void buildSeat2Panel() {
		seat2Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat2Panel.setPreferredSize(new Dimension(150,150));
		seat2Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat2Title.setPreferredSize(new Dimension(150,30));
		seat2Title.setOpaque(true);
		seat2Title.setForeground(Color.BLACK);
		seat2Title.setBackground(new Color(50, 109, 79, 150));
		seat2Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat2Human.setPreferredSize(new Dimension(150,30));
		seat2Human.setName("seat2Human");
		seat2Human.setOpaque(true);
		seat2Human.setForeground(Color.DARK_GRAY);
		seat2Human.setBackground(Color.LIGHT_GRAY);
		seat2Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat2Human.addMouseListener(this);
		
		seat2Computer.setPreferredSize(new Dimension(150,30));
		seat2Computer.setName("seat2Computer");
		seat2Computer.setOpaque(true);
		seat2Computer.setForeground(Color.DARK_GRAY);
		seat2Computer.setBackground(Color.LIGHT_GRAY);
		seat2Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat2Computer.addMouseListener(this);
		
		
		seat2Empty.setPreferredSize(new Dimension(150,30));
		seat2Empty.setName("seat2Empty");
		seat2Empty.setOpaque(true);
		seat2Empty.setForeground(Color.DARK_GRAY);
		seat2Empty.setBackground(Color.LIGHT_GRAY);
		seat2Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat2Empty.addMouseListener(this);
		
		seat2Panel.add(seat2Title);
		seat2Panel.add(seat2Human);
		seat2Panel.add(seat2Computer);
		seat2Panel.add(seat2Empty);	
		seat2Panel.setVisible(true);
	}

	private void buildSeat3Panel() {
		seat3Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat3Panel.setPreferredSize(new Dimension(150,150));
		seat3Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat3Title.setPreferredSize(new Dimension(150,30));
		seat3Title.setOpaque(true);
		seat3Title.setForeground(Color.BLACK);
		seat3Title.setBackground(new Color(50, 109, 79, 150));
		seat3Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat3Human.setPreferredSize(new Dimension(150,30));
		seat3Human.setName("seat3Human");
		seat3Human.setOpaque(true);
		seat3Human.setForeground(Color.DARK_GRAY);
		seat3Human.setBackground(Color.LIGHT_GRAY);
		seat3Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat3Human.addMouseListener(this);
		
		seat3Computer.setPreferredSize(new Dimension(150,30));
		seat3Computer.setName("seat3Computer");
		seat3Computer.setOpaque(true);
		seat3Computer.setForeground(Color.DARK_GRAY);
		seat3Computer.setBackground(Color.LIGHT_GRAY);
		seat3Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat3Computer.addMouseListener(this);
		
		
		seat3Empty.setPreferredSize(new Dimension(150,30));
		seat3Empty.setName("seat3Empty");
		seat3Empty.setOpaque(true);
		seat3Empty.setForeground(Color.DARK_GRAY);
		seat3Empty.setBackground(Color.LIGHT_GRAY);
		seat3Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat3Empty.addMouseListener(this);
		
		seat3Panel.add(seat3Title);
		seat3Panel.add(seat3Human);
		seat3Panel.add(seat3Computer);
		seat3Panel.add(seat3Empty);	
		seat3Panel.setVisible(true);
	}

	private void buildSeat4Panel() {
		seat4Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat4Panel.setPreferredSize(new Dimension(150,150));
		seat4Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat4Title.setPreferredSize(new Dimension(150,30));
		seat4Title.setOpaque(true);
		seat4Title.setForeground(Color.BLACK);
		seat4Title.setBackground(new Color(50, 109, 79, 150));
		seat4Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat4Human.setPreferredSize(new Dimension(150,30));
		seat4Human.setName("seat4Human");
		seat4Human.setOpaque(true);
		seat4Human.setForeground(Color.DARK_GRAY);
		seat4Human.setBackground(Color.LIGHT_GRAY);
		seat4Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat4Human.addMouseListener(this);
		
		seat4Computer.setPreferredSize(new Dimension(150,30));
		seat4Computer.setName("seat4Computer");
		seat4Computer.setOpaque(true);
		seat4Computer.setForeground(Color.DARK_GRAY);
		seat4Computer.setBackground(Color.LIGHT_GRAY);
		seat4Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat4Computer.addMouseListener(this);
		
		
		seat4Empty.setPreferredSize(new Dimension(150,30));
		seat4Empty.setName("seat4Empty");
		seat4Empty.setOpaque(true);
		seat4Empty.setForeground(Color.DARK_GRAY);
		seat4Empty.setBackground(Color.LIGHT_GRAY);
		seat4Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat4Empty.addMouseListener(this);
		
		seat4Panel.add(seat4Title);
		seat4Panel.add(seat4Human);
		seat4Panel.add(seat4Computer);
		seat4Panel.add(seat4Empty);	
		seat4Panel.setVisible(true);
	}

	private void buildSeat5Panel() {
		seat5Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat5Panel.setPreferredSize(new Dimension(150,150));
		seat5Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat5Title.setPreferredSize(new Dimension(150,30));
		seat5Title.setOpaque(true);
		seat5Title.setForeground(Color.BLACK);
		seat5Title.setBackground(new Color(50, 109, 79, 150));
		seat5Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat5Human.setPreferredSize(new Dimension(150,30));
		seat5Human.setName("seat5Human");
		seat5Human.setOpaque(true);
		seat5Human.setForeground(Color.DARK_GRAY);
		seat5Human.setBackground(Color.LIGHT_GRAY);
		seat5Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat5Human.addMouseListener(this);
		
		seat5Computer.setPreferredSize(new Dimension(150,30));
		seat5Computer.setName("seat5Computer");
		seat5Computer.setOpaque(true);
		seat5Computer.setForeground(Color.DARK_GRAY);
		seat5Computer.setBackground(Color.LIGHT_GRAY);
		seat5Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat5Computer.addMouseListener(this);
		
		
		seat5Empty.setPreferredSize(new Dimension(150,30));
		seat5Empty.setName("seat5Empty");
		seat5Empty.setOpaque(true);
		seat5Empty.setForeground(Color.DARK_GRAY);
		seat5Empty.setBackground(Color.LIGHT_GRAY);
		seat5Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat5Empty.addMouseListener(this);
		
		seat5Panel.add(seat5Title);
		seat5Panel.add(seat5Human);
		seat5Panel.add(seat5Computer);
		seat5Panel.add(seat5Empty);	
		seat5Panel.setVisible(true);
	}

	private void buildSeat6Panel() {
		seat6Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat6Panel.setPreferredSize(new Dimension(150,150));
		seat6Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat6Title.setPreferredSize(new Dimension(150,30));
		seat6Title.setOpaque(true);
		seat6Title.setForeground(Color.BLACK);
		seat6Title.setBackground(new Color(50, 109, 79, 150));
		seat6Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat6Human.setPreferredSize(new Dimension(150,30));
		seat6Human.setName("seat6Human");
		seat6Human.setOpaque(true);
		seat6Human.setForeground(Color.DARK_GRAY);
		seat6Human.setBackground(Color.LIGHT_GRAY);
		seat6Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat6Human.addMouseListener(this);
		
		seat6Computer.setPreferredSize(new Dimension(150,30));
		seat6Computer.setName("seat6Computer");
		seat6Computer.setOpaque(true);
		seat6Computer.setForeground(Color.DARK_GRAY);
		seat6Computer.setBackground(Color.LIGHT_GRAY);
		seat6Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat6Computer.addMouseListener(this);
		
		
		seat6Empty.setPreferredSize(new Dimension(150,30));
		seat6Empty.setName("seat6Empty");
		seat6Empty.setOpaque(true);
		seat6Empty.setForeground(Color.DARK_GRAY);
		seat6Empty.setBackground(Color.LIGHT_GRAY);
		seat6Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat6Empty.addMouseListener(this);
		
		seat6Panel.add(seat6Title);
		seat6Panel.add(seat6Human);
		seat6Panel.add(seat6Computer);
		seat6Panel.add(seat6Empty);	
		seat6Panel.setVisible(true);
	}

	private void buildSeat7Panel() {
		seat7Panel.setLayout(new GridLayout(4, 1, 5, 5));
		seat7Panel.setPreferredSize(new Dimension(150,150));
		seat7Panel.setBackground(new Color(0, 0, 0, 0));
		
		seat7Title.setPreferredSize(new Dimension(150,30));
		seat7Title.setOpaque(true);
		seat7Title.setForeground(Color.BLACK);
		seat7Title.setBackground(new Color(50, 109, 79, 150));
		seat7Title.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
		seat7Human.setPreferredSize(new Dimension(150,30));
		seat7Human.setName("seat7Human");
		seat7Human.setOpaque(true);
		seat7Human.setForeground(Color.DARK_GRAY);
		seat7Human.setBackground(Color.LIGHT_GRAY);
		seat7Human.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat7Human.addMouseListener(this);
		
		seat7Computer.setPreferredSize(new Dimension(150,30));
		seat7Computer.setName("seat7Computer");
		seat7Computer.setOpaque(true);
		seat7Computer.setForeground(Color.DARK_GRAY);
		seat7Computer.setBackground(Color.LIGHT_GRAY);
		seat7Computer.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat7Computer.addMouseListener(this);
		
		
		seat7Empty.setPreferredSize(new Dimension(150,30));
		seat7Empty.setName("seat7Empty");
		seat7Empty.setOpaque(true);
		seat7Empty.setForeground(Color.DARK_GRAY);
		seat7Empty.setBackground(Color.LIGHT_GRAY);
		seat7Empty.setFont(new Font("Arial Black", Font.PLAIN, 14));
		seat7Empty.addMouseListener(this);
		
		seat7Panel.add(seat7Title);
		seat7Panel.add(seat7Human);
		seat7Panel.add(seat7Computer);
		seat7Panel.add(seat7Empty);	
		seat7Panel.setVisible(true);

	}


	private void buildSeatPanel() {
		seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.X_AXIS));
		seatPanel.setPreferredSize(new Dimension(1200, 150));
		seatPanel.add(Box.createHorizontalStrut(18));
		seatPanel.add(seat1Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat2Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat3Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat4Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat5Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat6Panel);
		seatPanel.add(Box.createHorizontalStrut(19));
		seatPanel.add(seat7Panel);
		seatPanel.add(Box.createHorizontalStrut(18));
		seatPanel.setBackground(new Color(0, 0, 0, 0));
		seatPanel.setVisible(true);
	}

	private void buildLoadingScreen() {
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createRigidArea(new Dimension(1200, 552)));
		this.add(playButtonPanel);
		this.add(Box.createVerticalStrut(15));
		this.add(separator);
		this.add(Box.createVerticalStrut(5));
		this.add(seatPanel);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setVisible(true);
		
	}

	private void checkIfSeatsFilled() {

		if (seatChoices == 255) {
			playButton.setEnabled(true);
		} else {
			playButton.setEnabled(false);
		}
	}

	

	public void noHumanPlayersWarn() {
		JOptionPane.showMessageDialog(null, "Error: You must have at least one Human Player!", "Error Message",
				JOptionPane.ERROR_MESSAGE);
	}


	public PlayerType[] getPlayers() {
		return players;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, null);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		JLabel labelMouseEntered = (JLabel) event.getSource();
		if (!(labelMouseEntered.getBackground() == Color.GREEN)) {			
			labelMouseEntered.setBackground(new Color(200, 255, 225, 255));
		}
	}

	@Override
	public void mouseExited(MouseEvent event) {
		JLabel labelMouseExited = (JLabel) event.getSource();
		if (!(labelMouseExited.getBackground() == Color.GREEN)) {
			labelMouseExited.setBackground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		JLabel labelMousePressed = (JLabel) event.getSource();
		switch (labelMousePressed.getName()) {
		case "seat1Human":
			seat1Human.setBackground(Color.GREEN);
			seat1Human.setForeground(Color.WHITE);
			seat1Computer.setForeground(Color.DARK_GRAY);
			seat1Computer.setBackground(Color.LIGHT_GRAY);
			seat1Empty.setForeground(Color.DARK_GRAY);
			seat1Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 0);
			players[0] = PlayerType.HUMAN;
			break;
		case "seat2Human":
			seat2Human.setBackground(Color.GREEN);
			seat2Human.setForeground(Color.WHITE);
			seat2Computer.setForeground(Color.DARK_GRAY);
			seat2Computer.setBackground(Color.LIGHT_GRAY);
			seat2Empty.setForeground(Color.DARK_GRAY);
			seat2Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 1);
			players[1] = PlayerType.HUMAN;
			break;
		case "seat3Human":
			seat3Human.setBackground(Color.GREEN);
			seat3Human.setForeground(Color.WHITE);
			seat3Computer.setForeground(Color.DARK_GRAY);
			seat3Computer.setBackground(Color.LIGHT_GRAY);
			seat3Empty.setForeground(Color.DARK_GRAY);
			seat3Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 2);
			players[2] = PlayerType.HUMAN;
			break;
		case "seat4Human":
			seat4Human.setBackground(Color.GREEN);
			seat4Human.setForeground(Color.WHITE);
			seat4Computer.setForeground(Color.DARK_GRAY);
			seat4Computer.setBackground(Color.LIGHT_GRAY);
			seat4Empty.setForeground(Color.DARK_GRAY);
			seat4Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 3);
			players[3] = PlayerType.HUMAN;
			break;
		case "seat5Human":
			seat5Human.setBackground(Color.GREEN);
			seat5Human.setForeground(Color.WHITE);
			seat5Computer.setForeground(Color.DARK_GRAY);
			seat5Computer.setBackground(Color.LIGHT_GRAY);
			seat5Empty.setForeground(Color.DARK_GRAY);
			seat5Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 4);
			players[4] = PlayerType.HUMAN;
			break;
		case "seat6Human":
			seat6Human.setBackground(Color.GREEN);
			seat6Human.setForeground(Color.WHITE);
			seat6Computer.setForeground(Color.DARK_GRAY);
			seat6Computer.setBackground(Color.LIGHT_GRAY);
			seat6Empty.setForeground(Color.DARK_GRAY);
			seat6Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 5);
			players[5] = PlayerType.HUMAN;
			break;
		case "seat7Human":
			seat7Human.setBackground(Color.GREEN);
			seat7Human.setForeground(Color.WHITE);
			seat7Computer.setForeground(Color.DARK_GRAY);
			seat7Computer.setBackground(Color.LIGHT_GRAY);
			seat7Empty.setForeground(Color.DARK_GRAY);
			seat7Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 6);
			players[6] = PlayerType.HUMAN;
			break;
		case "seat1Computer":
			seat1Human.setForeground(Color.DARK_GRAY);
			seat1Human.setBackground(Color.LIGHT_GRAY);
			seat1Computer.setBackground(Color.GREEN);
			seat1Computer.setForeground(Color.WHITE);
			seat1Empty.setForeground(Color.DARK_GRAY);
			seat1Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 0);
			players[0] = PlayerType.COMPUTER;
			break;
		case "seat2Computer":
			seat2Human.setForeground(Color.DARK_GRAY);
			seat2Human.setBackground(Color.LIGHT_GRAY);
			seat2Computer.setBackground(Color.GREEN);
			seat2Computer.setForeground(Color.WHITE);
			seat2Empty.setForeground(Color.DARK_GRAY);
			seat2Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 1);
			players[1] = PlayerType.COMPUTER;
			break;
		case "seat3Computer":
			seat3Human.setForeground(Color.DARK_GRAY);
			seat3Human.setBackground(Color.LIGHT_GRAY);
			seat3Computer.setBackground(Color.GREEN);
			seat3Computer.setForeground(Color.WHITE);
			seat3Empty.setForeground(Color.DARK_GRAY);
			seat3Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 2);
			players[2] = PlayerType.COMPUTER;
			break;
		case "seat4Computer":
			seat4Human.setForeground(Color.DARK_GRAY);
			seat4Human.setBackground(Color.LIGHT_GRAY);
			seat4Computer.setBackground(Color.GREEN);
			seat4Computer.setForeground(Color.WHITE);
			seat4Empty.setForeground(Color.DARK_GRAY);
			seat4Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 3);
			players[3] = PlayerType.COMPUTER;
			break;
		case "seat5Computer":
			seat5Human.setForeground(Color.DARK_GRAY);
			seat5Human.setBackground(Color.LIGHT_GRAY);
			seat5Computer.setBackground(Color.GREEN);
			seat5Computer.setForeground(Color.WHITE);
			seat5Empty.setForeground(Color.DARK_GRAY);
			seat5Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 4);
			players[4] = PlayerType.COMPUTER;
			break;
		case "seat6Computer":
			seat6Human.setForeground(Color.DARK_GRAY);
			seat6Human.setBackground(Color.LIGHT_GRAY);
			seat6Computer.setBackground(Color.GREEN);
			seat6Computer.setForeground(Color.WHITE);
			seat6Empty.setForeground(Color.DARK_GRAY);
			seat6Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 5);
			players[5] = PlayerType.COMPUTER;
			break;
		case "seat7Computer":
			seat7Human.setForeground(Color.DARK_GRAY);
			seat7Human.setBackground(Color.LIGHT_GRAY);
			seat7Computer.setBackground(Color.GREEN);
			seat7Computer.setForeground(Color.WHITE);
			seat7Empty.setForeground(Color.DARK_GRAY);
			seat7Empty.setBackground(Color.LIGHT_GRAY);
			seatChoices |= (1 << 6);
			players[6] = PlayerType.COMPUTER;
			break;
		case "seat1Empty":
			seat1Human.setForeground(Color.DARK_GRAY);
			seat1Human.setBackground(Color.LIGHT_GRAY);
			seat1Computer.setForeground(Color.DARK_GRAY);
			seat1Computer.setBackground(Color.LIGHT_GRAY);
			seat1Empty.setBackground(Color.GREEN);
			seat1Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 0);
			players[0] = null;
			break;
		case "seat2Empty":
			seat2Human.setForeground(Color.DARK_GRAY);
			seat2Human.setBackground(Color.LIGHT_GRAY);
			seat2Computer.setForeground(Color.DARK_GRAY);
			seat2Computer.setBackground(Color.LIGHT_GRAY);
			seat2Empty.setBackground(Color.GREEN);
			seat2Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 1);
			players[1] = null;
			break;
		case "seat3Empty":
			seat3Human.setForeground(Color.DARK_GRAY);
			seat3Human.setBackground(Color.LIGHT_GRAY);
			seat3Computer.setForeground(Color.DARK_GRAY);
			seat3Computer.setBackground(Color.LIGHT_GRAY);
			seat3Empty.setBackground(Color.GREEN);
			seat3Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 2);
			players[2] = null;
			break;
		case "seat4Empty":
			seat4Human.setForeground(Color.DARK_GRAY);
			seat4Human.setBackground(Color.LIGHT_GRAY);
			seat4Computer.setForeground(Color.DARK_GRAY);
			seat4Computer.setBackground(Color.LIGHT_GRAY);
			seat4Empty.setBackground(Color.GREEN);
			seat4Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 3);
			players[3] = null;
			break;
		case "seat5Empty":
			seat5Human.setForeground(Color.DARK_GRAY);
			seat5Human.setBackground(Color.LIGHT_GRAY);
			seat5Computer.setForeground(Color.DARK_GRAY);
			seat5Computer.setBackground(Color.LIGHT_GRAY);
			seat5Empty.setBackground(Color.GREEN);
			seat5Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 4);
			players[4] = null;
			break;
		case "seat6Empty":
			seat6Human.setForeground(Color.DARK_GRAY);
			seat6Human.setBackground(Color.LIGHT_GRAY);
			seat6Computer.setForeground(Color.DARK_GRAY);
			seat6Computer.setBackground(Color.LIGHT_GRAY);
			seat6Empty.setBackground(Color.GREEN);
			seat6Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 5);
			players[5] = null;
			break;
		case "seat7Empty":
			seat7Human.setForeground(Color.DARK_GRAY);
			seat7Human.setBackground(Color.LIGHT_GRAY);
			seat7Computer.setForeground(Color.DARK_GRAY);
			seat7Computer.setBackground(Color.LIGHT_GRAY);
			seat7Empty.setBackground(Color.GREEN);
			seat7Empty.setForeground(Color.WHITE);
			seatChoices |= (1 << 6);
			players[6] = null;
			break;
		}
		checkIfSeatsFilled();		
	}
		

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
