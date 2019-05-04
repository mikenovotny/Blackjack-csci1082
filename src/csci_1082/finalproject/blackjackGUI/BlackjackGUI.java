package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import csci_1082.finalproject.blackjack.Card;
import csci_1082.finalproject.blackjack.GameEngine;
import csci_1082.finalproject.blackjack.PlayOption;
import csci_1082.finalproject.blackjack.Player;
import csci_1082.finalproject.blackjack.PlayerHands;
import csci_1082.finalproject.blackjack.PlayerType;


public class BlackjackGUI extends JPanel implements ActionListener {
	
	private JFrame mainFrame = new JFrame();
	
	private ActionPanel actionPanel;
	private GameBoard gameBoard;
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
		loadingScreen.getPlayButton().addActionListener(this);
		mainFrame.setVisible(true);
	}
	
	private void buildGamePanel() {
		gamePanel.add(loadingScreen, BorderLayout.CENTER);		
	}

	public void addActionPanelListeners() {
		actionPanel.getBetButton().addActionListener(this);
		actionPanel.getIncreaseBet().addActionListener(this);
		actionPanel.getDecreaseBet().addActionListener(this);
		actionPanel.getStandButton().addActionListener(this);
		actionPanel.getSplitButton().addActionListener(this);
		actionPanel.getDoubleButton().addActionListener(this);
		actionPanel.getHitButton().addActionListener(this);
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
		Player currentPlayer;
		switch (event.getActionCommand()) {
		case "Play":
			if (checkIfHumanPlayer()) {
				gameEngine = GameEngine.newTable();
				getPlayerNames();
				gameEngine.addDealerToList();
				changeToGameBoard();
			}
			break;
		case "BET":
			currentPlayer = gameEngine.getCurrentGUIPlayer();
			if (currentPlayer.getType() == PlayerType.COMPUTER) {
				double computerBet = currentPlayer.generateComputerBet();
				if (!currentPlayer.checkFunds(computerBet)) {
					computerBet = currentPlayer.getPlayerMoney();
				}
				actionPanel.setBetAmount(computerBet);
			}
			if (currentPlayer.checkFunds(Double.parseDouble(actionPanel.getBetAmount().getText()))) {
				currentPlayer.getPlayerHands().get(0).setHandBet(Double.parseDouble(actionPanel.getBetAmount().getText()));
				gameEngine.getDealer().getBets(currentPlayer, currentPlayer.getPlayerHands().get(0));
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " bet " + currentPlayer.getPlayerHands().get(0).getHandBet() + "\n");
				actionPanel.clearBetAmount();
				gameEngine.switchToNextPlayer();
			} else {
				fundsWarning();
			}
			break;
		case "+":
			double bet = Double.parseDouble(actionPanel.getBetAmount().getText());
			bet += 5;
			actionPanel.setBetAmount(bet);
			break;
		case "-":
			double bet2 = Double.parseDouble(actionPanel.getBetAmount().getText());
			bet2 -= 5;
			actionPanel.setBetAmount(bet2);
			break;
		case "HIT":
			currentPlayer = gameEngine.getCurrentGUIPlayer();
			System.out.println(currentPlayer.getPlayerName() + " is in the Hit action");
			Card newCard = gameEngine.hit();
			currentPlayer.getPlayerHands().get(0).addCard(newCard);
			displayNewCard(newCard);
			gameEngine.getSumOfCards(currentPlayer.getPlayerHands().get(0));
			if (gameEngine.checkIfBusted(currentPlayer.getPlayerHands().get(0))) {
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " Busted!\n");
				currentPlayer.setTurnOver(true);
				gameEngine.switchToNextPlayer();
			} else {
				this.processUser();
			}
			break;
		case "STAND":
			currentPlayer = gameEngine.getCurrentGUIPlayer();
			currentPlayer.setTurnOver(true);
			gameEngine.switchToNextPlayer();
			break;
		case "SPLIT":
			/*
			 * Split functionality has been disabled for now due to time constraints
			 */
			break;
		case "DOUBLE":
			currentPlayer = gameEngine.getCurrentGUIPlayer();
			currentPlayer.getPlayerHands().get(0).addCard(gameEngine.hit());
			gameEngine.getSumOfCards(currentPlayer.getPlayerHands().get(0));
			if (gameEngine.checkIfBusted(currentPlayer.getPlayerHands().get(0))) {
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " Busted!\n");
			}
				currentPlayer.setTurnOver(true);
				gameEngine.switchToNextPlayer();
			break;
		}
			
		
	}
	
	private void fundsWarning() {
		JOptionPane.showMessageDialog(null, "Error: You do not have enough money to make this Bet!", "Error Message", JOptionPane.ERROR_MESSAGE);
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
		gameBoard = new GameBoard(gameEngine.getPlayerList());
		actionPanel = new ActionPanel();
		gamePanel.removeAll();
		gamePanel.add(actionPanel, BorderLayout.SOUTH);
		gamePanel.add(gameBoard, BorderLayout.CENTER);
		mainFrame.pack();
		addActionPanelListeners();
		gameEngine.setGUIObject(this);
		gameEngine.startRound();
	}
	
	private void updateGUI() {
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	private void toggleActionPanelButtons(boolean status) {
		if (status) {
			actionPanel.getSplitButton().setEnabled(false);
			actionPanel.getStandButton().setEnabled(true);
			actionPanel.getDoubleButton().setEnabled(true);
			actionPanel.getHitButton().setEnabled(true);
		} else
			actionPanel.getSplitButton().setEnabled(false);
			actionPanel.getStandButton().setEnabled(false);
			actionPanel.getDoubleButton().setEnabled(false);
			actionPanel.getHitButton().setEnabled(false);
	}

	public void processUser() {
		Player currentPlayer = gameEngine.getCurrentGUIPlayer();
		actionPanel.getGameHistory().append("Current Player is: " + currentPlayer.getPlayerName() + "\n");
		
		// If the bets are all made, deal the cards
		if (gameEngine.isBetsComplete() && !(gameEngine.isCardsDelt())) {
			gameEngine.dealCards();
			for (Player p : gameEngine.getPlayerList()) {
				System.out.println(p.toString());
			}
			displayInitialCards();
		}
		//TODO: display information about the user
		switch (currentPlayer.getType()) {
		case COMPUTER:
			//toggleActionPanelButtons(false);
			actionPanel.getGameHistory().append("I found a Computer Player!\n");
			if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				actionPanel.getBetButton().doClick();
			}else {
				//toggleActionPanelButtons(true);
				System.out.println(currentPlayer.getPlayerName() + " is making a decision...");
				int computerChoice = gameEngine.computerDecision(currentPlayer, currentPlayer.getPlayerHands().get(0));
				System.out.println(currentPlayer.getPlayerName() + " is chosing option " + computerChoice);
				// convert this to an Enum Friendly name
				PlayOption playerOption = PlayOption.values()[computerChoice - 1];
				switch (playerOption) {
				case HIT:
					System.out.println(currentPlayer.getPlayerName() + " is in the HIT Case...");
					actionPanel.getHitButton().doClick();
					break;
				case STAND:
					System.out.println(currentPlayer.getPlayerName() + " is in the STAND Case...");
					actionPanel.getStandButton().doClick();
					break;
				case DOUBLE_DOWN:
					System.out.println(currentPlayer.getPlayerName() + " is in the DOUBLE_DOWN Case...");
					actionPanel.getDoubleButton().doClick();
					break;
				case SPLIT:
					// Do nothing, we shouldn't get here.  Log a message just in case
					actionPanel.getGameHistory().append("Oh No, the split option was chose somehow!\n");
					break;
				}
					
			}
			break;
		case HUMAN:
			if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				//toggleActionPanelButtons(false);
			} else {
				//toggleActionPanelButtons(true);
			}
			break;
		case DEALER:
			//toggleActionPanelButtons(true);
			if (gameEngine.isCardsDelt()) {
				int dealerChoice = gameEngine.dealerDecision();
				// convert this to an Enum Friendly name
				PlayOption playerOption = PlayOption.values()[dealerChoice - 1];
				switch (playerOption) {
				case HIT:
					actionPanel.getHitButton().doClick();
					break;
				case STAND:
					actionPanel.getStandButton().doClick();
					break;
				case DOUBLE_DOWN:
					actionPanel.getDoubleButton().doClick();
					break;
				case SPLIT:
					// Do nothing, we shouldn't get here.  Log a message just in case
					actionPanel.getGameHistory().append("Oh No, the split option was chose somehow!\n");
					break;
				}
			} else {
				gameEngine.switchToNextPlayer();
			}
			break;
		}
	}

	private void displayInitialCards() {
		for (Player p : gameEngine.getPlayerList()) {
			for (PlayerHands hand : p.getPlayerHands()) {
				for (Card card : hand.getPlayerHand()) {
					String baseImagePath = "/cards/";
					ImageIcon cardIcon = createImageIcon(baseImagePath + card.getFileName());
					JLabel cardLabel = new JLabel();
					cardLabel.setIcon(cardIcon);
					gameBoard.updateCardPanel(p.getSeat(), cardLabel);
				}
			}
		}
		
	}
	
	private void displayNewCard(Card card) {
		String baseImagePath = "/cards/";
		ImageIcon cardIcon = createImageIcon(baseImagePath + card.getFileName());
		JLabel cardLabel = new JLabel();
		cardLabel.setIcon(cardIcon);
		gameBoard.updateCardPanel(gameEngine.getCurrentGUIPlayer().getSeat(), cardLabel);
	}
	
	private ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
				return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find File: " + path);
		}
		return null;
	}
}
