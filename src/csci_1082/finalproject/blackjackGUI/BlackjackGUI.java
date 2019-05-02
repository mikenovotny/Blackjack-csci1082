package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import csci_1082.finalproject.blackjack.GameEngine;
import csci_1082.finalproject.blackjack.Player;
import csci_1082.finalproject.blackjack.PlayerType;


public class BlackjackGUI extends JPanel implements ActionListener {
	
	private JFrame mainFrame = new JFrame();
	
	private ActionPanel actionPanel = new ActionPanel();
	private GameBoard gameBoard = new GameBoard();
	private LoadingScreen loadingScreen = new LoadingScreen();
	private JPanel gamePanel = new JPanel(new BorderLayout());
	private GameEngine gameEngine;
	private String tempName = null;
	
	// Constructor
	public BlackjackGUI() {
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("BlackJack");
		buildGamePanel();
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(((dim.width / 2) - (mainFrame.getSize().width / 2)), ((dim.height / 2) - (mainFrame.getSize().height / 2)));
		addListeners();
		mainFrame.setVisible(true);
	}
	
	private void buildGamePanel() {
		gamePanel.add(loadingScreen, BorderLayout.CENTER);		
	}

	public void addListeners() {
		loadingScreen.getPlayButton().addActionListener(this);
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


	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case "Play":
			if (checkIfHumanPlayer()) {
				gameEngine = GameEngine.newTable();
				getPlayerNames();
				changeToGameBoard();
			}
			break;
		}
			
		
	}

	private boolean checkIfHumanPlayer() {
		int numHumanPlayers = 0;
		for (int loopIndex = 0; loopIndex < loadingScreen.getPlayers().length; loopIndex++) {
			if (loadingScreen.getPlayers()[loopIndex]== PlayerType.HUMAN) {
				numHumanPlayers++;
			}
		}
		
		if (numHumanPlayers == 0) {
			loadingScreen.warn();
			return false;
		}
		
		return true;		
	}

	private void getPlayerNames() {
		int compPlayerIndex = 1;
		for (int loopIndex = 0; loopIndex < loadingScreen.getPlayers().length; loopIndex++) {
			if (loadingScreen.getPlayers()[loopIndex] == PlayerType.COMPUTER) {
				gameEngine.addPlayer("Computer " + compPlayerIndex, loadingScreen.getPlayers()[loopIndex], loopIndex + 1);
				compPlayerIndex++;
			} else if (loadingScreen.getPlayers()[loopIndex]== PlayerType.HUMAN) {
				switch (loopIndex) {
				case 0:
					tempName = loadingScreen.getSeat1TextField().getText();
					
					break;
				case 1:
					tempName = loadingScreen.getSeat2TextField().getText();
					break;
				case 2:
					tempName = loadingScreen.getSeat3TextField().getText();
					break;
				case 3:
					tempName = loadingScreen.getSeat4TextField().getText();
					break;
				case 4:
					tempName = loadingScreen.getSeat5TextField().getText();
					break;
				case 5:
					tempName = loadingScreen.getSeat6TextField().getText();
					break;
				case 6:
					tempName = loadingScreen.getSeat7TextField().getText();
					break;
				}
				
				gameEngine.addPlayer(tempName, loadingScreen.getPlayers()[loopIndex], loopIndex + 1);
			} else {
				// do Nothing
			}
		}
		for (Player player : gameEngine.getPlayerList()) {
			System.out.println(player.toString());
		}
	}

	private void changeToGameBoard() {
		gamePanel.removeAll();
		gamePanel.add(actionPanel, BorderLayout.SOUTH);
		gamePanel.add(gameBoard, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
		gameEngine.startRound(this);
	}
	
	private void updateGUI() {
		mainFrame.revalidate();
		mainFrame.repaint();
	}
}
