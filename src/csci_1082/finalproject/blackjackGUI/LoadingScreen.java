package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class LoadingScreen extends JPanel implements ActionListener, ItemListener{
	
	// Logo Panel
	private JPanel logoPanel = new JPanel();
	private JLabel panelBG = new JLabel();
	private String baseImagePath = "/images/"; 
	private JButton playButton = new JButton("Play");
	
	
	// Seat Panel
	private JPanel seatPanel = new JPanel();
	private String[] seatOptions = {"Choose a Player Type", "Human", "Computer", "Empty"};
	private JComboBox<String> seat1 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat2 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat3 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat4 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat5 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat6 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat7 = new JComboBox<String>(seatOptions);
	private int seatChoices = 128;
	private int baseSeatByte = 0;

	
	public LoadingScreen() {
		buildLogoPanel();
		buildSeatPanel();
		buildLoadingScreen();
		validate();
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
		seatPanel.add(seat7);
		seatPanel.add(seat6);
		seatPanel.add(seat5);
		seatPanel.add(seat4);
		seatPanel.add(seat3);
		seatPanel.add(seat2);
		seatPanel.add(seat1);
	}
	
	private void buildLoadingScreen() {
		this.setLayout(new BorderLayout());
		this.add(seatPanel, BorderLayout.SOUTH);
		this.add(logoPanel, BorderLayout.CENTER);
		addListeners();
	}
	
	private void addListeners() {
		seat1.addItemListener(this);
		seat2.addItemListener(this);
		seat3.addItemListener(this);
		seat4.addItemListener(this);
		seat5.addItemListener(this);
		seat6.addItemListener(this);
		seat7.addItemListener(this);
		playButton.addActionListener(this);
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
			if (event.getSource() == seat1) {
				
				/* 
				 * For all seat events, if the selected index is not the first JComboBox entry 
				 * then change the associated bit to a one.
				 */
				if(seat1.getSelectedIndex() > 0) {
					seatChoices |= (1 << 0);
				} 
				
				/*
				 * For all seat events, if the selected Index goes back to being the first JComboBox
				 * entry then set the associated bit to a zero.
				 */
				else {
					seatChoices &= ~(1 << 0);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat2) {
				if(seat2.getSelectedIndex() > 0) {
					seatChoices |= (1 << 1);
				} else {
					seatChoices &= ~(1 << 1);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat3) {
				if(seat3.getSelectedIndex() > 0) {
					seatChoices |= (1 << 2);
				} else {
					seatChoices &= ~(1 << 2);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat4) {
				if(seat4.getSelectedIndex() > 0) {
					seatChoices |= (1 << 3);
				} else {
					seatChoices &= ~(1 << 3);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat5) {
				if(seat5.getSelectedIndex() > 0) {
					seatChoices |= (1 << 4);
				} else {
					seatChoices &= ~(1 << 4);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat6) {
				if(seat6.getSelectedIndex() > 0) {
					seatChoices |= (1 << 5);
				} else {
					seatChoices &= ~(1 << 5);
				}
				checkIfSeatsFilled();
			}
			else if (event.getSource() == seat7) {
				if(seat7.getSelectedIndex() > 0) {
					seatChoices |= (1 << 6);
				} else {
					seatChoices &= ~(1 << 6);
				}
				checkIfSeatsFilled();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
