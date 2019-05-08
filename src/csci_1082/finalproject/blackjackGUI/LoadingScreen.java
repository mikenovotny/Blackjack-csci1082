package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import csci_1082.finalproject.blackjack.PlayerType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoadingScreen extends JPanel implements ItemListener, DocumentListener{
	
	private BufferedImage backgroundImage = null;
	
	// Logo Panel
	private JPanel logoPanel = new JPanel();
	private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private JButton playButton = new JButton("Play");
	
	
	// Seat Panel
	private JPanel seatPanel = new JPanel(new GridLayout(3, 7));
	private String[] seatOptions = {"Choose a Player Type", "Human", "Computer", "Empty"};
	private ArrayList<JLabel> seatLabels = new ArrayList<JLabel>();
	private ArrayList<JComboBox<String>> seatComboBoxes = new ArrayList<JComboBox<String>>();
	private ArrayList<JTextField> seatTextFields = new ArrayList<JTextField>();
	private int seatChoices = 128;
	private PlayerType[] players = new PlayerType[7];
	
	public LoadingScreen() {
		try {
			backgroundImage = ImageIO.read(getClass().getResource("/images/blackjacklogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildArrayLists();
		buildLogoPanel();
		buildSeatPanel();
		buildLoadingScreen();
	}
	
	private void buildArrayLists() {
		this.seatLabels.add(new JLabel("Seat 7"));
		this.seatLabels.add(new JLabel("Seat 6"));
		this.seatLabels.add(new JLabel("Seat 5"));
		this.seatLabels.add(new JLabel("Seat 4"));
		this.seatLabels.add(new JLabel("Seat 3"));
		this.seatLabels.add(new JLabel("Seat 2"));
		this.seatLabels.add(new JLabel("Seat 1"));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatComboBoxes.add(new JComboBox<String>(seatOptions));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		this.seatTextFields.add(new JTextField("Enter Player Name", 10));
		
	}

	private void buildLogoPanel() {
		playButton.setEnabled(false);
		playButton.setPreferredSize(new Dimension(40, 40));
		logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
		logoPanel.add(panelBG, BorderLayout.CENTER);
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
		gamePanel.setBackground(new Color(0, 153, 0, 255));
		gamePanel.setVisible(true);
		
	}


	private void buildCenterPanel() {
		buildbottomPanel();
		buildDealerpanel();
		buildGameMessagePanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(dealerPanel);
		centerPanel.add(gameMessagePanel);
		centerPanel.add(Box.createRigidArea(new Dimension(720,170)));
		centerPanel.add(bottomPanel);
		centerPanel.setBackground(new Color(0, 153, 0, 255));
		centerPanel.setVisible(true);
	}


	private void buildDealerpanel() {
		seat0Panel.add(seatPlayerLabels.get(0), BorderLayout.NORTH);
		seat0Panel.add(seat0Cards, BorderLayout.CENTER);
		seat0Panel.setBackground(new Color(0, 153, 0, 255));
		dealerPanel.add(seat0Panel);
		dealerPanel.setBackground(new Color(0, 153, 0, 255));
		dealerPanel.setVisible(true);
		
	}


	private void configurePanelSizes() {
		dealerPanel.setPreferredSize(new Dimension(720, 200));
		bottomPanel.setPreferredSize(new Dimension(720, 230));
		gameMessagePanel.setPreferredSize(new Dimension(720, 200));
		seat0Panel.setPreferredSize(new Dimension(240, 200));
		seat1Panel.setPreferredSize(new Dimension(240, 300));
		seat2Panel.setPreferredSize(new Dimension(240, 300));
		seat3Panel.setPreferredSize(new Dimension(240, 230));
		seat4Panel.setPreferredSize(new Dimension(240, 230));
		seat5Panel.setPreferredSize(new Dimension(240, 230));
		seat6Panel.setPreferredSize(new Dimension(240, 300));
		seat7Panel.setPreferredSize(new Dimension(240, 300));
		seat0Cards.setPreferredSize(new Dimension(200,170));
		seat1Cards.setPreferredSize(new Dimension(200,250));
		seat2Cards.setPreferredSize(new Dimension(200,250));
		seat3Cards.setPreferredSize(new Dimension(200,200));
		seat4Cards.setPreferredSize(new Dimension(200,200));
		seat5Cards.setPreferredSize(new Dimension(200,200));
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
		bottomPanel.setBackground(new Color(0, 153, 0, 255));
		bottomPanel.setVisible(true);
	}


	private void buildSeat3Panel() {
		seat3Panel.add(seatPlayerLabels.get(3), BorderLayout.NORTH);
		seat3Panel.add(seat3Cards, BorderLayout.CENTER);
		seat3Panel.setBackground(new Color(0, 153, 0, 255));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat4Panel() {
		seat4Panel.add(seatPlayerLabels.get(4), BorderLayout.NORTH);
		seat4Panel.add(seat4Cards, BorderLayout.CENTER);
		seat4Panel.setBackground(new Color(0, 153, 0, 255));
		seat3Panel.setVisible(true);
		
	}


	private void buildSeat5Panel() {
		seat5Panel.add(seatPlayerLabels.get(5), BorderLayout.NORTH);
		seat5Panel.add(seat5Cards, BorderLayout.CENTER);
		seat5Panel.setBackground(new Color(0, 153, 0, 255));
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
		rightPanel.setBackground(new Color(0, 153, 0, 255));
		rightPanel.setVisible(true);
		
	}


	private void buildSeat2Panel() {
		seat2Panel.add(seatPlayerLabels.get(2), BorderLayout.NORTH);
		seat2Panel.add(seat2Cards, BorderLayout.CENTER);
		seat2Panel.setBackground(new Color(0, 153, 0, 255));
		seat2Panel.setVisible(true);
		
	}


	private void buildSeat1Panel() {
		seat1Panel.add(seatPlayerLabels.get(1), BorderLayout.NORTH);
		seat1Panel.add(seat1Cards, BorderLayout.CENTER);
		seat1Panel.setBackground(new Color(0, 153, 0, 255));
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
		leftPanel.setBackground(new Color(0, 153, 0, 255));
		leftPanel.setVisible(true);
		
	}


	private void buildSeat6Panel() {
		seat6Panel.add(seatPlayerLabels.get(6), BorderLayout.NORTH);
		seat6Panel.add(seat6Cards, BorderLayout.CENTER);
		seat6Panel.setBackground(new Color(0, 153, 0, 255));
		seat6Panel.setVisible(true);
		
	}


	private void buildSeat7Panel() {
		seat7Panel.add(seatPlayerLabels.get(7), BorderLayout.NORTH);
		seat7Panel.add(seat7Cards, BorderLayout.CENTER);
		seat7Panel.setBackground(new Color(0, 153, 0, 255));
		seat7Panel.setVisible(true);
		
	}


	private void buildGameMessagePanel() {
		gameMessagePanel.setLayout(new BorderLayout());
		gameMessagePanel.add(background, BorderLayout.CENTER);
	}
	
	private void buildSeatPanel() {
		for (JLabel seatLabel : this.seatLabels) {
			seatPanel.add(seatLabel);
		}
		
		for (JComboBox<String> seatComboBox : this.seatComboBoxes) {
			seatPanel.add(seatComboBox);
			seatComboBox.addItemListener(this);
		}
		
		for (JTextField seatTextField : this.seatTextFields) {
			seatTextField.setEditable(false);
			seatTextField.getDocument().addDocumentListener(this);
			seatPanel.add(seatTextField);
		}
	}
	
	private void buildLoadingScreen() {
		this.setLayout(new BorderLayout());
		this.add(seatPanel, BorderLayout.SOUTH);
		this.add(logoPanel, BorderLayout.CENTER);
	}
	
	
	private void checkIfSeatsFilled() {
		// Bitshifts during the JComboBox events should add up to 255 when all seats have been selected
		if (seatChoices == 255) {
			playButton.setEnabled(true);
		} else {
			playButton.setEnabled(false);
		}
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
	
	public void noHumanPlayersWarn() {
		JOptionPane.showMessageDialog(null, "Error: You must have at least one Human Player!", "Error Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public void emptyNameWarn(int seat) {
		// Increment the incoming seat value as this came from a zero indexed for loop
		seat += 1;
		JOptionPane.showMessageDialog(null, "Error: You must enter a Player Name for seat " + seat, "Error Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public PlayerType[] getPlayers() {
		return players;
	}

	public JButton getPlayButton() {
		return playButton;
	}
	
	

	public JTextField getSeat1TextField() {
		return this.seatTextFields.get(0);
	}

	public JTextField getSeat2TextField() {
		return this.seatTextFields.get(1);
	}

	public JTextField getSeat3TextField() {
		return this.seatTextFields.get(2);
	}

	public JTextField getSeat4TextField() {
		return this.seatTextFields.get(3);
	}

	public JTextField getSeat5TextField() {
		return this.seatTextFields.get(0);
	}

	public JTextField getSeat6TextField() {
		return this.seatTextFields.get(5);
	}

	public JTextField getSeat7TextField() {
		return this.seatTextFields.get(6);
	}
	
	private void checkText() {
		for (JTextField seatTextField : this.seatTextFields) {
			if (seatTextField.isEditable()) {
				seatTextField.setBackground(seatTextField.getText().equalsIgnoreCase("") ? Color.RED : Color.GREEN);
			} else {
				seatTextField.setBackground(SystemColor.text);
			}
		}
	}

	/**
	 * Function to perform actions based on selections in the JComboBoxes
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {

		// Only do someting if the event recieved is the user selecting something
		if (event.getStateChange() == ItemEvent.SELECTED) {
		
			/*
			 * Each of the event.getSource() calls is required to tell what JComboBox the event
			 * came from.  The logic does a bitshift on seatChoices to ensure that all seats
			 * have been selected before enabling the playButton
			 */
			JComboBox<String> sourceSeat = null;
			JTextField sourceTextField = null;
			if (event.getSource() == this.seatComboBoxes.get(0)) {
				sourceSeat = this.seatComboBoxes.get(0);
				sourceTextField = this.seatTextFields.get(0);
				/* 
				 * For all seat events, if the selected index is not the first JComboBox entry 
				 * then change the associated bit to a one.
				 */
				switch (sourceSeat.getSelectedIndex()) {
					case 0: 
						seatChoices &= ~(1 << 0);
						sourceTextField.setEditable(false);
						sourceTextField.setText("Enter Player Name");
						break;
					case 1: 
						seatChoices |= (1 << 0);
						sourceTextField.setEditable(true);
						sourceTextField.setText("");
						players[0] = PlayerType.HUMAN;
						break;
					case 2:
						seatChoices |= (1 << 0);
						sourceTextField.setEditable(false);
						sourceTextField.setText("Computer Player");
						players[0] = PlayerType.COMPUTER;
						break;
					case 3:
						seatChoices |= (1 << 0);
						sourceTextField.setEditable(false);
						sourceTextField.setText("Empty Seat");
						players[0] = null;
						break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(1)) {
				sourceSeat = this.seatComboBoxes.get(1);
				sourceTextField = this.seatTextFields.get(1);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 1);
					sourceTextField.setEditable(false);
					sourceTextField.setText("Enter Player Name");
					break;
				case 1: 
					seatChoices |= (1 << 1);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[1] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 1);
					sourceTextField.setEditable(false);
					sourceTextField.setText("Computer Player");
					players[1] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 1);
					sourceTextField.setEditable(false);
					sourceTextField.setText("Empty Seat");
					players[1] = null;
					break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(2)) {
				sourceSeat = this.seatComboBoxes.get(2);
				sourceTextField = this.seatTextFields.get(2);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 2);
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 2);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[2] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 2);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[2] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 2);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
					players[2] = null;
					break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(3)) {
				sourceSeat = this.seatComboBoxes.get(3);
				sourceTextField = this.seatTextFields.get(3);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 3);
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 3);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[3] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 3);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[3] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 3);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
					players[3] = null;
					break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(4)) {
				sourceSeat = this.seatComboBoxes.get(4);
				sourceTextField = this.seatTextFields.get(4);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 4);
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 4);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[4] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 4);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[4] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 4);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
					players[4] = null;
					break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(5)) {
				sourceSeat = this.seatComboBoxes.get(5);
				sourceTextField = this.seatTextFields.get(5);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 5);
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 5);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[5] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 5);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[5] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 5);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
					players[5] = null;
					break;
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == this.seatComboBoxes.get(6)) {
				sourceSeat = this.seatComboBoxes.get(6);
				sourceTextField = this.seatTextFields.get(6);
				switch (sourceSeat.getSelectedIndex()) {
				case 0: 
					seatChoices &= ~(1 << 6);
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 6);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[6] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 6);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[6] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 6);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
					players[6] = null;
					break;
				}
				checkIfSeatsFilled();
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent event) {
		checkText();
	}



	@Override
	public void insertUpdate(DocumentEvent event) {
		checkText();
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		checkText();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, null);
	}
}
