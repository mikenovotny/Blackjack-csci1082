package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jmx.mbeanserver.NamedObject;

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
	private boolean nameValid = false;
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
		namePopUpFrame.getOkButton().addActionListener(this);
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
			gameEngine = GameEngine.newTable();
			getPlayerNames();
			break;
		case "OK":
			tempName = namePopUpFrame.getNameTextField().getText();
			if (tempName.length() < 1) {
				namePopUpFrame.warn();
			} else {
				nameValid = true;
				namePopUpFrame.setVisible(false);
			}
			break;
		}
			updateGamePanel();
		
	}

	private void getPlayerNames() {
		int compPlayerIndex = 1;
		for (int loopIndex = 0; loopIndex < loadingScreen.getPlayers().length; loopIndex++) {
			if (loadingScreen.getPlayers()[loopIndex] == PlayerType.COMPUTER) {
				gameEngine.addPlayer("Computer " + compPlayerIndex, loadingScreen.getPlayers()[loopIndex], loopIndex + 1);
				compPlayerIndex++;
			} else if (loadingScreen.getPlayers()[loopIndex]== PlayerType.HUMAN) {
				nameValid = false;
				
				while (!nameValid) {System.out.println("waiting for name to be valid....");}
				gameEngine.addPlayer(tempName, loadingScreen.getPlayers()[loopIndex], loopIndex + 1);
			} else {
				// do Nothing
			}
		}
		for (Player player : gameEngine.getPlayerList()) {
			System.out.println(player.toString());
		}
	}

	private void updateGamePanel() {
		gamePanel.removeAll();
		gamePanel.add(actionPanel, BorderLayout.SOUTH);
		gamePanel.add(gameBoard, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
	}

}
