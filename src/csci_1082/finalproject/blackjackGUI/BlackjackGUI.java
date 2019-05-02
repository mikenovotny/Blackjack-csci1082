package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BlackjackGUI extends JFrame implements ActionListener {
	
	private ActionPanel actionPanel = new ActionPanel();
	private GameBoard gameBoard = new GameBoard();
	private LoadingScreen loadingScreen = new LoadingScreen();
	private SeatPanel seatPanel = new SeatPanel();
	
	// Constructor
	public BlackjackGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("BlackJack");
		setLayout(new BorderLayout());
		add(seatPanel, BorderLayout.SOUTH);
		add(loadingScreen, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
	
	
	public ActionPanel getActionPanel() {
		return actionPanel;
	}



	public void setActionPanel(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}



	public GameBoard getGameBoard() {
		return gameBoard;
	}



	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}



	public LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}



	public void setLoadingScreen(LoadingScreen loadingScreen) {
		this.loadingScreen = loadingScreen;
	}



	public SeatPanel getSeatPanel() {
		return seatPanel;
	}



	public void setSeatPanel(SeatPanel seatPanel) {
		this.seatPanel = seatPanel;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
