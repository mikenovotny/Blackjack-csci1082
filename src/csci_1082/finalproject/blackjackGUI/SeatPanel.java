package csci_1082.finalproject.blackjackGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SeatPanel extends JPanel implements ActionListener {
	
	private String[] seatOptions = {"Choose a Player Type", "Human", "Computer", "Empty"};
	private JComboBox<String> seat1 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat2 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat3 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat4 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat5 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat6 = new JComboBox<String>(seatOptions);
	private JComboBox<String> seat7 = new JComboBox<String>(seatOptions);
	private byte seatChoices = (byte)-128;
	private byte baseSeatByte = (byte)0;
	private boolean seatsFilled = false;
	
	public SeatPanel() {
		this.add(seat7);
		this.add(seat6);
		this.add(seat5);
		this.add(seat4);
		this.add(seat3);
		this.add(seat2);
		this.add(seat1);
		addListeners();
		validate();
	}
	
	private void addListeners() {
		seat1.addActionListener(this);
		seat2.addActionListener(this);
		seat3.addActionListener(this);
		seat4.addActionListener(this);
		seat5.addActionListener(this);
		seat6.addActionListener(this);
		seat7.addActionListener(this);
	}
	
	private void checkIfSeatsFilled() {
		byte result = (byte) (seatChoices | baseSeatByte);
		if (result == seatChoices) {
			seatsFilled = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case "seat1":
			if(seat1.getSelectedIndex() > 0) {
				seatChoices |= (1 << 0);
			} else {
				seatChoices &= ~(1 << 0);
			}
			checkIfSeatsFilled();
			break;
		case "seat2":
			if(seat2.getSelectedIndex() > 0) {
				seatChoices |= (1 << 1);
			} else {
				seatChoices &= ~(1 << 1);
			}
			checkIfSeatsFilled();
			break;
		case "seat3":
			if(seat3.getSelectedIndex() > 0) {
				seatChoices |= (1 << 2);
			} else {
				seatChoices &= ~(1 << 2);
			}
			checkIfSeatsFilled();
			break;
		case "seat4":
			if(seat4.getSelectedIndex() > 0) {
				seatChoices |= (1 << 3);
			} else {
				seatChoices &= ~(1 << 3);
			}
			checkIfSeatsFilled();
			break;
		case "seat5":
			if(seat5.getSelectedIndex() > 0) {
				seatChoices |= (1 << 4);
			} else {
				seatChoices &= ~(1 << 4);
			}
			checkIfSeatsFilled();
			break;
		case "seat6":
			if(seat6.getSelectedIndex() > 0) {
				seatChoices |= (1 << 5);
			} else {
				seatChoices &= ~(1 << 5);
			}
			checkIfSeatsFilled();
			break;
		case "seat7":
			if(seat7.getSelectedIndex() > 0) {
				seatChoices |= (1 << 6);
			} else {
				seatChoices &= ~(1 << 6);
			}
			checkIfSeatsFilled();
			break;
		}
		
	}

	public boolean isSeatsFilled() {
		return seatsFilled;
	}	
	
}
