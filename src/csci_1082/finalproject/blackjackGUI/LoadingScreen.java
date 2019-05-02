package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import csci_1082.finalproject.blackjack.PlayerType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LoadingScreen extends JPanel implements ItemListener, DocumentListener{
	
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
		buildArrayLists();
		buildLogoPanel();
		buildSeatPanel();
		buildLoadingScreen();
	}
	
	private void buildArrayLists() {
		this.seatLabels.add(new JLabel("Seat 1"));
		this.seatLabels.add(new JLabel("Seat 2"));
		this.seatLabels.add(new JLabel("Seat 3"));
		this.seatLabels.add(new JLabel("Seat 4"));
		this.seatLabels.add(new JLabel("Seat 5"));
		this.seatLabels.add(new JLabel("Seat 6"));
		this.seatLabels.add(new JLabel("Seat 7"));
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
		ImageIcon baseGameBoard = createImageIcon(baseImagePath + "blackjacklogo.png");
		panelBG.setLayout(new BorderLayout());
		panelBG.setIcon(baseGameBoard);
		panelBG.add(playButton, BorderLayout.SOUTH);
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(panelBG, BorderLayout.CENTER);
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
						sourceTextField.setText("Enter Player Name");
						sourceTextField.setEditable(false);
						break;
					case 1: 
						seatChoices |= (1 << 0);
						sourceTextField.setEditable(true);
						sourceTextField.setText("");
						players[0] = PlayerType.HUMAN;
						break;
					case 2:
						seatChoices |= (1 << 0);
						sourceTextField.setText("Computer Player");
						sourceTextField.setEditable(false);
						players[0] = PlayerType.COMPUTER;
						break;
					case 3:
						seatChoices |= (1 << 0);
						sourceTextField.setText("Empty Seat");
						sourceTextField.setEditable(false);
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
					sourceTextField.setText("Enter Player Name");
					sourceTextField.setEditable(false);
					break;
				case 1: 
					seatChoices |= (1 << 1);
					sourceTextField.setEditable(true);
					sourceTextField.setText("");
					players[1] = PlayerType.HUMAN;
					break;
				case 2:
					seatChoices |= (1 << 1);
					sourceTextField.setText("Computer Player");
					sourceTextField.setEditable(false);
					players[1] = PlayerType.COMPUTER;
					break;
				case 3:
					seatChoices |= (1 << 1);
					sourceTextField.setText("Empty Seat");
					sourceTextField.setEditable(false);
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
}
