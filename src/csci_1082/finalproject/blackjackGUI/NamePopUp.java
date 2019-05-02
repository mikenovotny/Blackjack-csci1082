package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NamePopUp extends JFrame {
	
	private JPanel namePopUpPanel = new JPanel(new BorderLayout());
	private JPanel okButtonPanel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JLabel nameLabel = new JLabel("Enter Player's Name");
	private JTextField nameTextField = new JTextField(30);
	private JButton okButton = new JButton("OK");
	private String name = null;
	
	public NamePopUp() {
		buildNamePopUpPanel();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Enter Player Name for Seat");
		this.add(namePopUpPanel, BorderLayout.CENTER);
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((dim.width / 2) - (this.getSize().width / 2)), ((dim.height / 2) - (this.getSize().height / 2)));
	}

	public void warn() {
		if (Integer.parseInt(nameTextField.getText()) <= 0) {
			JOptionPane.showMessageDialog(null, "Error: You must enter a Name", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	private void buildNamePopUpPanel() {
		buildNamePanel();
		buildokButtonPanel();
		namePopUpPanel.add(namePanel, BorderLayout.NORTH);
		namePopUpPanel.add(okButtonPanel, BorderLayout.SOUTH);		
	}

	private void buildokButtonPanel() {
		okButtonPanel.add(okButton);		
	}

	private void buildNamePanel() {
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);	
	}

	public JButton getOkButton() {
		return okButton;
	}
	
	

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public String getName() {
		return name;
	}
/*
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "OK") {
			name = nameTextField.getText();
			if (name.length() < 1) {
				warn();
			} else {
				this.dispose();
			}
		
		}
	
	*/

	public void clearNameTextField() {
		nameTextField.setText("");		
	}
}
