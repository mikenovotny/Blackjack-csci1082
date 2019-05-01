package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BlackjackGUI extends JFrame implements ActionListener {
	
	private ActionPanel actionPanel = new ActionPanel();
	private GameBoard gameBoard = new GameBoard();
	private LoadingScreen loadingScreen = new LoadingScreen();
	
	// Constructor
	public BlackjackGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BlackJack");
		setLayout(new BorderLayout());
		add(actionPanel, BorderLayout.SOUTH);
		add(loadingScreen, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
