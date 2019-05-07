package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

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
	private final int ONE_SECOND = 1000;
	private int timerStartTime;
	private int numberOfHumanPlayer = 0;
	
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



	public int getNumberOfHumanPlayer() {
		return numberOfHumanPlayer;
	}

	public void setNumberOfHumanPlayer(int numberOfHumanPlayer) {
		this.numberOfHumanPlayer = numberOfHumanPlayer;
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
			Card newCard = gameEngine.hit();
			currentPlayer.getPlayerHands().get(0).addCard(newCard);
			displayNewCard(currentPlayer, newCard);
			gameEngine.getSumOfCards(currentPlayer.getPlayerHands().get(0));
			if (gameEngine.checkIfBusted(currentPlayer.getPlayerHands().get(0))) {
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " Busted!\n");
				gameBoard.updateHandStatus(currentPlayer.getSeat(), "busted");
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
				gameBoard.updateHandStatus(currentPlayer.getSeat(), "busted");
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
			if (loadingScreen.getPlayers()[loopIndex] == PlayerType.HUMAN) {
				switch (loopIndex) {
				case 0:
					if (loadingScreen.getSeat1TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 1:
					if (loadingScreen.getSeat2TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 2:
					if (loadingScreen.getSeat3TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 3:
					if (loadingScreen.getSeat4TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 4:
					if (loadingScreen.getSeat5TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 5:
					if (loadingScreen.getSeat6TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				case 6:
					if (loadingScreen.getSeat7TextField().getText().equalsIgnoreCase("")) {
						loadingScreen.emptyNameWarn(loopIndex);
						return false;
					}
					break;
				}
				numHumanPlayers++;
			}

		}
		if (numHumanPlayers == 0) {
			loadingScreen.noHumanPlayersWarn();
			return false;
		}
		numberOfHumanPlayer = numHumanPlayers;
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
			displayInitialCards();
		}
		
		//TODO: display information about the user
		switch (currentPlayer.getType()) {
		case COMPUTER:
			if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				actionPanel.getBetButton().doClick();
			}else {
				int computerChoice = gameEngine.computerDecision(currentPlayer, currentPlayer.getPlayerHands().get(0));
				// convert this to an Enum Friendly name
				PlayOption playerOption = PlayOption.values()[computerChoice - 1];
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
					
			}
			break;
		case HUMAN:
			if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				toggleActionPanelButtons(false);
				actionPanel.getBetButton().setEnabled(true);
				actionPanel.getIncreaseBet().setEnabled(true);
				actionPanel.getDecreaseBet().setEnabled(true);
			} else if (currentPlayer.getPlayerHands().get(0).getPlayerHand().size() > 2) {
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getSplitButton().setEnabled(false);
				actionPanel.getStandButton().setEnabled(true);
				actionPanel.getDoubleButton().setEnabled(false);
				actionPanel.getHitButton().setEnabled(true);
			} else {
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getSplitButton().setEnabled(false);
				actionPanel.getStandButton().setEnabled(true);
				actionPanel.getDoubleButton().setEnabled(true);
				actionPanel.getHitButton().setEnabled(true);				
			}
			break;
		case DEALER:
			if (gameEngine.isCardsDelt() && gameEngine.playerTurnsOver()) {
				displayDealersCards();
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
		int dealerCardsShown = 0;
		for (Player p : gameEngine.getPlayerList()) {
			for (PlayerHands hand : p.getPlayerHands()) {
				for (Card card : hand.getPlayerHand()) {
					String baseImagePath = "/cards/";
					ImageIcon cardIcon = null;
					if (p.getType() == PlayerType.DEALER && dealerCardsShown == 0) {
						cardIcon = createImageIcon(baseImagePath + "cardBack.png");
						dealerCardsShown++;
					}  else {			
						cardIcon = createImageIcon(baseImagePath + card.getFileName());
					}
					JLabel cardLabel = new JLabel();
					cardLabel.setIcon(cardIcon);
					gameBoard.setCardAttributes(p.getSeat(), cardLabel);
				}
			}
		}
		
	}
	
	private void displayNewCard(Player p, Card card) {
		String baseImagePath = "/cards/";
		ImageIcon cardIcon = createImageIcon(baseImagePath + card.getFileName());
		JLabel cardLabel = new JLabel();
		cardLabel.setIcon(cardIcon);
		gameBoard.setCardAttributes(p.getSeat(), cardLabel);
	}
	
	public void displayDealersCards() {
		gameBoard.clearDealerCards();
		for (Player p : gameEngine.getPlayerList()) {
			if (p.getType() == PlayerType.DEALER) {
				for (PlayerHands hand : p.getPlayerHands()) {
					for (Card card : hand.getPlayerHand()) {
						String baseImagePath = "/cards/";
						ImageIcon cardIcon = createImageIcon(baseImagePath + card.getFileName());
						JLabel cardLabel = new JLabel();
						cardLabel.setIcon(cardIcon);
						gameBoard.setCardAttributes(p.getSeat(), cardLabel);
					}
				}
			}
		}
		
		
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

	public void finishRound() {
		System.out.println("I'm in the finishRound method");
		for (Player p : gameEngine.getPlayerList()) {
			if (p.getType() != PlayerType.DEALER) {
				switch (p.getPlayerHands().get(0).getHandWinLossStatus()) {
				case "winner":
					gameBoard.updateHandStatus(p.getSeat(), "winner");
					break;
				case "push":
					gameBoard.updateHandStatus(p.getSeat(), "push");
					break;
				case "lost":
					gameBoard.updateHandStatus(p.getSeat(), "lost");
					break;
				case "busted":
					gameBoard.updateHandStatus(p.getSeat(), "busted");
					break;	
				}
			}
			
		}
		dispalyNextRoundTimer();
	}
	
	private void dispalyNextRoundTimer() {
		timerStartTime = 7;				// max value of progressbar in secon
		gameBoard.getGameMessagePanel().setLayout(new BoxLayout(gameBoard.getGameMessagePanel(), BoxLayout.Y_AXIS));
		
		// Make JPanels for organizing items
		JPanel nextRoundTimerPanel = new JPanel();
		JPanel progressBarPanel = new JPanel(new BorderLayout());
		
		// Set Panel Attributes
		progressBarPanel.setBackground(new Color(0,128,0,255));
		nextRoundTimerPanel.setPreferredSize(new Dimension(720, 10));
		nextRoundTimerPanel.setLayout(new BoxLayout(nextRoundTimerPanel, BoxLayout.X_AXIS));	
		nextRoundTimerPanel.setBackground(new Color(0,128,0,255));
		
		// Create JLabel that will act as Title for ProgressBar
		JLabel nextRoundLabel = new JLabel();
		nextRoundLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		nextRoundLabel.setForeground(Color.BLACK);
		nextRoundLabel.setBackground(Color.GREEN);
		nextRoundLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nextRoundLabel.setText("Next Round Starts In...");
		
		// Create ProgressBar
		JProgressBar nextRoundProgressBar = new JProgressBar(0, timerStartTime);
		nextRoundProgressBar.setValue(timerStartTime);
		
		// Build the helper Panel for the progressBar
		progressBarPanel.add(nextRoundProgressBar, BorderLayout.CENTER);
		
		// Build the helper panel to center progressBar
		nextRoundTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		nextRoundTimerPanel.add(progressBarPanel);
		nextRoundTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		
		// Display the progressBar
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,70)));
		gameBoard.getGameMessagePanel().add(nextRoundLabel);
		gameBoard.getGameMessagePanel().add(nextRoundTimerPanel);
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,70)));
		gameBoard.getGameMessagePanel().revalidate();
		
		// Create Timer and Start Timer
		Timer nextRoundTimer = new Timer(ONE_SECOND, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextRoundProgressBar.setValue(--timerStartTime);
				if (timerStartTime == 0) {
					startNewRound();
				}
			}
		});
		nextRoundTimer.start();
		}
		
	protected void startNewRound() {
		Player brokePlayer = null;
		System.out.println("new round stuff should go here");
		brokePlayer = gameEngine.checkForBrokePlayers();
		if (brokePlayer == null) {
			gameBoard.resetGameBoard();
			mainFrame.pack();
			gameEngine.resetPlayers();
			gameEngine.startRound();
		} else if (brokePlayer.getType() == PlayerType.COMPUTER) {
			actionPanel.getGameHistory().append(brokePlayer.getPlayerName() + " is out of money and has left the table.\n");
		} else if (brokePlayer.getType() == PlayerType.HUMAN) {
			numberOfHumanPlayer--;
			if (numberOfHumanPlayer == 0) {
				displayGameEndTimer();
			} else {
				actionPanel.getGameHistory().append(brokePlayer.getPlayerName() + " is out of money and has left the table.\n");			
			}
		}
	}

	private void displayGameEndTimer() {
		// Clear any current items in the game message panel
		gameBoard.getGameMessagePanel().removeAll();
		
		// max value of progressbar in secon
		timerStartTime = 15;				
		
		// Set Layout
		gameBoard.getGameMessagePanel().setLayout(new BoxLayout(gameBoard.getGameMessagePanel(), BoxLayout.Y_AXIS));
		
		// Make JPanels for organizing items
		JPanel gameEndTimerPanel = new JPanel();
		JPanel progressBarPanel = new JPanel(new BorderLayout());
		
		// Set Panel Attributes
		progressBarPanel.setBackground(new Color(0,128,0,255));
		gameEndTimerPanel.setPreferredSize(new Dimension(720, 10));
		gameEndTimerPanel.setLayout(new BoxLayout(gameEndTimerPanel, BoxLayout.X_AXIS));	
		gameEndTimerPanel.setBackground(new Color(0,128,0,255));
		
		// Create JLabel that will act as Title for ProgressBar
		JLabel gameEndLabel = new JLabel();
		gameEndLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		gameEndLabel.setForeground(Color.BLACK);
		gameEndLabel.setBackground(Color.GREEN);
		gameEndLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		gameEndLabel.setText("All Human players have no Money.  Ending Game in...");
		
		// Create ProgressBar
		JProgressBar gameEndProgressBar = new JProgressBar(0, timerStartTime);
		gameEndProgressBar.setValue(timerStartTime);
		
		// Build the helper Panel for the progressBar
		progressBarPanel.add(gameEndProgressBar, BorderLayout.CENTER);
		
		// Build the helper panel to center progressBar
		gameEndTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		gameEndTimerPanel.add(progressBarPanel);
		gameEndTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		
		// Display the progressBar
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,70)));
		gameBoard.getGameMessagePanel().add(gameEndLabel);
		gameBoard.getGameMessagePanel().add(gameEndTimerPanel);
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,70)));
		gameBoard.getGameMessagePanel().revalidate();
		
		// Create Timer and Start Timer
		Timer gameEndTimer = new Timer(ONE_SECOND, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameEndProgressBar.setValue(--timerStartTime);
				if (timerStartTime == 0) {
					mainFrame.dispose();
				}
			}
		});
		gameEndTimer.start();
	}
		

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BlackjackGUI newBlackjackGame = new BlackjackGUI();
			}
		});
	}
		
}

