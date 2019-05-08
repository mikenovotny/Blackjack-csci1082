package csci_1082.finalproject.blackjackGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
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
	private Timer nextRoundTimer = new Timer(ONE_SECOND, null);
	private JProgressBar nextRoundProgressBar = new JProgressBar();
	private Timer gameEndTimer = new Timer(ONE_SECOND, null);
	private JProgressBar gameEndProgressBar = new JProgressBar();
	private boolean delayDone = false;

	
	// Constructor
	public BlackjackGUI() {
		setTimerSettings();
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
	
	private void setTimerSettings() {
		nextRoundTimer.setActionCommand("nextRoundTimer");
		nextRoundTimer.addActionListener(this);
		gameEndTimer.setActionCommand("gameEndTimer");
		gameEndTimer.addActionListener(this);
		
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
		actionPanel.getCardCountButton().addActionListener(this);
		actionPanel.getCashOut().addActionListener(this);
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
				gameEngine = new GameEngine();
				getPlayerNames();
				gameEngine.addDealerToList();
				changeToGameBoard();
			}
			break;
		case "BET":
			currentPlayer = gameEngine.getCurrentGUIPlayer();
			if (currentPlayer.getType() == PlayerType.COMPUTER) {
				int computerBet = currentPlayer.generateComputerBet();
				if (!currentPlayer.checkFunds(computerBet)) {
					computerBet = currentPlayer.getPlayerMoney();
				}
				actionPanel.setBetAmount(computerBet);
			}
			
			if (actionPanel.getBetAmount().isEmpty()) {
				invalidBetWarning();
			} else if (currentPlayer.checkFunds(Integer.parseInt(actionPanel.getBetAmount()))) {
				currentPlayer.getPlayerHands().get(0).setHandBet(Integer.parseInt(actionPanel.getBetAmount()));
				gameEngine.getDealer().getBets(currentPlayer, currentPlayer.getPlayerHands().get(0));
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " bet " + currentPlayer.getPlayerHands().get(0).getHandBet() + "\n");
				actionPanel.clearBetAmount();
				gameEngine.switchToNextPlayer();
			} else {
				fundsWarning();
			}
			break;
		case "+":
			int bet;
			if (actionPanel.getBetAmount().isEmpty()) {
				bet = 0;
			} else {
				bet = Integer.parseInt(actionPanel.getBetAmount());
			}
			bet += 5;
			actionPanel.setBetAmount(bet);
			break;
		case "-":
			int bet2;
			if (actionPanel.getBetAmount().isEmpty()) {
				bet2 = 0;
			} else {
				bet2 = Integer.parseInt(actionPanel.getBetAmount());
			}
			
			if (bet2 - 5 < 0) {
				invalidBetWarning();
			} else {
				bet2 -= 5;
				actionPanel.setBetAmount(bet2);
			}
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
			Card doubleDownCard = gameEngine.hit();
			currentPlayer.getPlayerHands().get(0).addCard(doubleDownCard);
			displayNewCard(currentPlayer, doubleDownCard);
			gameEngine.getSumOfCards(currentPlayer.getPlayerHands().get(0));
			if (gameEngine.checkIfBusted(currentPlayer.getPlayerHands().get(0))) {
				actionPanel.getGameHistory().append(currentPlayer.getPlayerName() + " Busted!\n");
				gameBoard.updateHandStatus(currentPlayer.getSeat(), "busted");
			}
				currentPlayer.setTurnOver(true);
				gameEngine.switchToNextPlayer();
			break;
		case "nextRoundTimer":
			this.nextRoundProgressBar.setValue(--timerStartTime);
			if (timerStartTime == 0) {
				nextRoundTimer.stop();
				newRoundPreChecks();
			}
			break;
		case "gameEndTimer":
			this.gameEndProgressBar.setValue(--timerStartTime);
			if (timerStartTime == 0) {
				gameEndTimer.stop();
				mainFrame.dispose();
			}
			break;
		case "displayCardCount":
			String cardCountPrefix = "";
			int currentCardCount = gameEngine.getDeckShoe().getCardCount();
			if (currentCardCount > 0) {
				actionPanel.getCardCountDisplay().setForeground(Color.GREEN);
				cardCountPrefix = "+";
			} else if (currentCardCount < 0) {
				actionPanel.getCardCountDisplay().setForeground(Color.RED);
				cardCountPrefix = "-";
			} else {
				actionPanel.getCardCountDisplay().setForeground(Color.ORANGE);
			}
			actionPanel.getCardCountDisplay().setText(cardCountPrefix + Integer.toString(currentCardCount));
			actionPanel.getCardCountButton().setText("Hide Card Count");
			actionPanel.getCardCountButton().setActionCommand("hideCardCount");
			break;
		case "hideCardCount":
			actionPanel.getCardCountDisplay().setText("");
			actionPanel.getCardCountButton().setText("Display Card Count");
			actionPanel.getCardCountButton().setActionCommand("displayCardCount");
			break;
		case "cashout":
			 int result = JOptionPane.showConfirmDialog(mainFrame,
			            "Are you sure you want to Cash OUt?", "This will end your game.",
			            JOptionPane.YES_NO_OPTION);
			        if (result == JOptionPane.YES_OPTION) {
			        	numberOfHumanPlayer--;
						if (numberOfHumanPlayer == 0) {
							mainFrame.dispose();
						} else {
							Player exitingPlayer = gameEngine.getCurrentGUIPlayer();
							gameEngine.getPlayerList().remove(exitingPlayer);
							newRoundPreChecks();
						}
			        }
			        else if (result == JOptionPane.NO_OPTION) {
			        	// Do Nothing
			        }
			break;
		}	
	}
	

	private void invalidBetWarning() {
		JOptionPane.showMessageDialog(null, "Error: Invalid Bet Amount!", "Error Message", JOptionPane.ERROR_MESSAGE);
		
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
		for (int loopIndex = (loadingScreen.getPlayers().length -1); loopIndex > 0; loopIndex--) {
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
		gamePanel.add(actionPanel, BorderLayout.EAST);
		gamePanel.add(gameBoard, BorderLayout.CENTER);
		mainFrame.pack();
		addActionPanelListeners();
		gameEngine.setGUIObject(this);
		gameEngine.startRound();
	}
	
	
	public void processUser() {
		boolean status = updateActionPanel();
	
		// If the bets are all made, deal the cards
		if (gameEngine.areBetsDone() && !(gameEngine.isCardsDelt())) {
			gameEngine.dealCards();
			displayInitialCards();
		} else {
			getPlayerActions();
		}
	}
	private boolean updateActionPanel() {
		Player currentPlayer = gameEngine.getCurrentGUIPlayer();
		switch (currentPlayer.getType()) {
		case HUMAN:
		case COMPUTER:

			actionPanel.getGameHistory().append("Current Player is: " + currentPlayer.getPlayerName() + "\n");
			actionPanel.getCurrentPlayerName().setText(currentPlayer.getPlayerName());
			actionPanel.getPlayerMoney().setText("$" + Integer.toString(currentPlayer.getPlayerMoney()));
			if (currentPlayer.getPlayerHands().get(0).getHandBet() > 0); {
				actionPanel.getPlayerBet().setText(Integer.toString(currentPlayer.getPlayerHands().get(0).getHandBet()));
			}
			
			if (currentPlayer.isTurnOver()) {
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getHitButton().setEnabled(false);
				actionPanel.getStandButton().setEnabled(false);
				actionPanel.getDoubleButton().setEnabled(false);
				actionPanel.getSplitButton().setEnabled(false);
				boolean delayNextPlayer = delay(3000);
				gameEngine.switchToNextPlayer();
			} else if (currentPlayer.getPlayerHands().get(0).getHandBet() > 0 && currentPlayer.getPlayerHands().get(0).getPlayerHand().size() > 2) {
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getHitButton().setEnabled(true);
				actionPanel.getStandButton().setEnabled(true);
				actionPanel.getDoubleButton().setEnabled(false);
				actionPanel.getSplitButton().setEnabled(false);
			}else if (currentPlayer.getPlayerHands().get(0).getHandBet() > 0 && currentPlayer.getPlayerHands().get(0).getPlayerHand().size() == 2) {
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getHitButton().setEnabled(true);
				actionPanel.getStandButton().setEnabled(true);
				actionPanel.getDoubleButton().setEnabled(true);
				actionPanel.getSplitButton().setEnabled(false);
			} else if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				actionPanel.getIncreaseBet().setEnabled(true);
				actionPanel.getDecreaseBet().setEnabled(true);
				actionPanel.getBetButton().setEnabled(true);
				actionPanel.getHitButton().setEnabled(false);
				actionPanel.getStandButton().setEnabled(false);
				actionPanel.getDoubleButton().setEnabled(false);
				actionPanel.getSplitButton().setEnabled(false);
			}
			else {
				actionPanel.getIncreaseBet().setEnabled(false);
				actionPanel.getDecreaseBet().setEnabled(false);
				actionPanel.getBetButton().setEnabled(false);
				actionPanel.getHitButton().setEnabled(true);
				actionPanel.getStandButton().setEnabled(true);
				actionPanel.getDoubleButton().setEnabled(true);
				actionPanel.getSplitButton().setEnabled(false);
		}
		break;
		case DEALER:
			actionPanel.getGameHistory().append("Current Player is: " + currentPlayer.getPlayerName() + "\n");
			actionPanel.getCurrentPlayerName().setText(currentPlayer.getPlayerName());
			actionPanel.getIncreaseBet().setEnabled(false);
			actionPanel.getDecreaseBet().setEnabled(false);
			actionPanel.getBetButton().setEnabled(false);
			actionPanel.getHitButton().setEnabled(true);
			actionPanel.getStandButton().setEnabled(true);
			actionPanel.getDoubleButton().setEnabled(true);
			actionPanel.getSplitButton().setEnabled(false);
			actionPanel.getPlayerMoney().setText("N/A");
			actionPanel.getPlayerBet().setText("N/A");
			break;
		}
		return true;
		
	}

	private boolean delay(int milliseconds) {
		delayDone = false;
		SwingWorker<Boolean, String> delayThread = new SwingWorker<Boolean, String>() {

			@Override
			protected Boolean doInBackground() throws Exception {
					Thread.sleep(milliseconds);	
					return true;
				}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				delayDone = true;
			}
		};
		
		delayThread.execute();
		return delayDone;
	}

	public void getPlayerActions() {
		Player currentPlayer = gameEngine.getCurrentGUIPlayer();
		switch (currentPlayer.getType()) {
		case COMPUTER:
			// Create Worker Thread to handle computer decision
			SwingWorker<Boolean, String> computerActionThread = new SwingWorker<Boolean, String>() {
				int computerChoice = 0;
				@Override
				protected Boolean doInBackground() throws Exception {
						computerChoice = gameEngine.computerDecision(currentPlayer, currentPlayer.getPlayerHands().get(0));
						Thread.sleep(500);	
						return true;
					}

				@Override
				protected void done() {
					boolean status = false;
					try {
						status = get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					processComputerOption(computerChoice);
				}
			};
			
			if (currentPlayer.getPlayerHands().get(0).getHandBet() == 0) {
				actionPanel.getBetButton().doClick();
			} else {
				computerActionThread.execute();
			}
			
			
			break;
		case HUMAN:
			// Do nothing but sit here until the user presses a button
			break;
		case DEALER:
			
			SwingWorker<Boolean, String> dealerActionThread = new SwingWorker<Boolean, String>() {
				int dealerChoice = 0;
				
				@Override
				protected Boolean doInBackground() throws Exception {
						displayDealersCards();
						dealerChoice = gameEngine.dealerDecision();
						Thread.sleep(500);
						return true;
				}

				@Override
				protected void done() {
					boolean status = false;
					try {
						status = get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					processDealerOption(dealerChoice);
				}
			};
		
			if (gameEngine.isCardsDelt() && gameEngine.playerTurnsOver()) {
			dealerActionThread.execute();
			} else {
				gameEngine.switchToNextPlayer();
			}
			break;
		}
	}
	
	protected void processComputerOption(int computerChoice) {
		
		// convert this to an Enum Friendly name
		PlayOption playerOption = PlayOption.values()[computerChoice - 1];
		
		Player p = gameEngine.getCurrentGUIPlayer();
		actionPanel.getGameHistory().append(p.getPlayerName() + " chose to: " + playerOption + "!\n");
		
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
	
	protected void processDealerOption(int dealerChoice) {
		// convert this to an Enum Friendly name
		PlayOption playerOption = PlayOption.values()[dealerChoice - 1];
		actionPanel.getGameHistory().append("Dealer chose to: " + playerOption + "!\n"); 
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

	private void displayInitialCards() {
		SwingWorker<Boolean, String> displayCardsThread = new SwingWorker<Boolean, String>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				int cardsDisplayed = 0;
				int dealerCardsShown = 0;
				setProgress(0);
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
							cardsDisplayed++;
							setProgress((cardsDisplayed / (gameEngine.getPlayerList().size() * 2)) * 100);
							publish("displayed Card");
							Thread.sleep(200);
						}
					}
				}
				return true;
			}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				checkForPlayerBlackJack();
			}
		};
		displayCardsThread.execute();		
	}
	
	protected void checkForPlayerBlackJack() {
		SwingWorker<Boolean, String> checkForPlayerBJThread = new SwingWorker<Boolean, String>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				int playersChecked = 0;
				setProgress(0);
				for (Player p : gameEngine.getPlayerList()) {
					if (p.getType() != PlayerType.DEALER) {
						for (PlayerHands hand : p.getPlayerHands()) {
							if (hand.isBlackJack()) {
								gameEngine.getDealer().payPlayer(p, hand);
								gameBoard.updateHandStatus(p.getSeat(), "blackjack");
								p.setTurnOver(true);
							}
						}
					}
					playersChecked++;
					setProgress((playersChecked / gameEngine.getPlayerList().size()) * 100);
					publish("checked player for blackjack");
					Thread.sleep(100);
				}
				return true;
			}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				checkForDealerBlackJack();
			}
		};
		checkForPlayerBJThread.execute();		
	}
		

	protected void checkForDealerBlackJack() {
		SwingWorker<Boolean, String> checkForDealerBJThread = new SwingWorker<Boolean, String>() {
			boolean dealerHasBlackjack = false;
			@Override
			protected Boolean doInBackground() throws Exception {
				setProgress(0);
				for (Player p : gameEngine.getPlayerList()) {
					if (p.getType() == PlayerType.DEALER) {
						for (PlayerHands hand : p.getPlayerHands()) {
							if (hand.isBlackJack()) {
								dealerHasBlackjack = true;
								displayDealersCards();
								gameBoard.updateHandStatus(p.getSeat(), "blackjack");
								p.setTurnOver(true);
							}
						}
					}
					setProgress(100);
					publish("checked dealer for blackjack");
				}
				return true;
			}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (dealerHasBlackjack) {
					processRoundEnd();
				} else {
					getPlayerActions();
				}
			}
		};
		checkForDealerBJThread.execute();		
	}

	protected void processRoundEnd() {
		SwingWorker<Boolean, String> processRoundEndThread = new SwingWorker<Boolean, String>() {
			int playersProcessed = 0;
			@Override
			protected Boolean doInBackground() throws Exception {
				setProgress(0);
				for (Player p : gameEngine.getPlayerList()) {
					if (p.getType() != PlayerType.DEALER) {
						for (PlayerHands hand : p.getPlayerHands()) {
							hand.setHandWinLossStatus("lost");
							p.setTurnOver(true);
							}
						}
					playersProcessed++;
					setProgress((playersProcessed / gameEngine.getPlayerList().size()) * 100);
					publish("set player hand status to lost");
				}
				return true;
			}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
					finishRound();
			}
		};
		processRoundEndThread.execute();
		
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
		SwingWorker<Boolean, String> finishRoundThread = new SwingWorker<Boolean, String>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				setProgress(0);
				int playerResultsUpdated = 0;
				for (Player p : gameEngine.getPlayerList()) {
					if (p.getType() != PlayerType.DEALER) {
						switch (p.getPlayerHands().get(0).getHandWinLossStatus()) {
						case "winner":
							gameBoard.updateHandStatus(p.getSeat(), "winner");
							actionPanel.getGameHistory().append(p.getPlayerName() + " WON!  Payout: $" + (p.getPlayerHands().get(0).getHandBet() * 2) + "!\n"); 
							break;
						case "push":
							gameBoard.updateHandStatus(p.getSeat(), "push");
							actionPanel.getGameHistory().append(p.getPlayerName() + " PUSHED!  Payout: $" + p.getPlayerHands().get(0).getHandBet() + "!\n");
							break;
						case "lost":
							gameBoard.updateHandStatus(p.getSeat(), "lost");
							actionPanel.getGameHistory().append(p.getPlayerName() + " LOST!  Lost: $" + p.getPlayerHands().get(0).getHandBet() + "!\n");
							break;
						case "busted":
							gameBoard.updateHandStatus(p.getSeat(), "busted");
							actionPanel.getGameHistory().append(p.getPlayerName() + " BUSTED!  Lost: $" + p.getPlayerHands().get(0).getHandBet() + "!\n");
							break;	
						case "blackjack":
							actionPanel.getGameHistory().append(p.getPlayerName() + " got BLACKJACK!  Payout: $" + ((int)(p.getPlayerHands().get(0).getHandBet() * GameEngine.BLACKJACK_PAYOUT)) + "!\n");
							break;
						}
					} 
					playerResultsUpdated++;
					setProgress((playerResultsUpdated / (gameEngine.getPlayerList().size())) * 100);
					publish("displayed hand status");
					Thread.sleep(100);
				}
				return true;
			}

			@Override
			protected void done() {
				boolean status = false;
				try {
					status = get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				displayNextRoundTimer();
			}
		};	
		finishRoundThread.execute();
	}
	
	private void displayNextRoundTimer() {
		timerStartTime = 7;				// max value of progressbar in secon
		gameBoard.getGameMessagePanel().setLayout(new BoxLayout(gameBoard.getGameMessagePanel(), BoxLayout.Y_AXIS));
		
		// Make JPanels for organizing items
		JPanel nextRoundTimerPanel = new JPanel();
		JPanel progressBarPanel = new JPanel(new BorderLayout());
		
		// Set Panel Attributes
		progressBarPanel.setBackground(new Color(0,153,0,255));
		nextRoundTimerPanel.setPreferredSize(new Dimension(720, 10));
		nextRoundTimerPanel.setLayout(new BoxLayout(nextRoundTimerPanel, BoxLayout.X_AXIS));	

		
		// Create JLabel that will act as Title for ProgressBar
		JLabel nextRoundLabel = new JLabel();
		nextRoundLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		nextRoundLabel.setForeground(Color.BLACK);
		nextRoundLabel.setBackground(new Color(0,153,0,255));
		nextRoundLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		nextRoundLabel.setText("Next Round Starts In...");
		
		// Create ProgressBar
		nextRoundProgressBar.setMinimum(0);
		nextRoundProgressBar.setMaximum(timerStartTime);
		nextRoundProgressBar.setValue(timerStartTime);
		
		// Build the helper Panel for the progressBar
		progressBarPanel.add(nextRoundProgressBar, BorderLayout.CENTER);
		
		// Build the helper panel to center progressBar
		nextRoundTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		nextRoundTimerPanel.add(progressBarPanel);
		nextRoundTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		nextRoundTimerPanel.setBackground(new Color(0,153,0,255));
		nextRoundTimerPanel.setVisible(true);
		

		// Display the progressBar
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,20)));
		gameBoard.getGameMessagePanel().add(nextRoundLabel);
		gameBoard.getGameMessagePanel().add(nextRoundTimerPanel);
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,120)));
		gameBoard.getGameMessagePanel().setBackground(new Color(0, 153, 0, 255));
		gameBoard.getGameMessagePanel().revalidate();
		
		this.nextRoundTimer.start();		
		}
	
	protected void newRoundPreChecks() {
		Player brokePlayer = null;
		brokePlayer = gameEngine.checkForBrokePlayers();
		if (brokePlayer == null) {
			startNewRound();
		} else if (brokePlayer.getType() == PlayerType.COMPUTER) {
			actionPanel.getGameHistory().append(brokePlayer.getPlayerName() + " is out of money and has left the table.\n");
			startNewRound();
		} else if (brokePlayer.getType() == PlayerType.HUMAN) {
			numberOfHumanPlayer--;
			if (numberOfHumanPlayer == 0) {
				displayGameEndTimer();
			} else {
				actionPanel.getGameHistory().append(brokePlayer.getPlayerName() + " is out of money and has left the table.\n");	
				startNewRound();
			}
		}
	}
		
	protected void startNewRound() {
		actionPanel.getGameHistory().append("Starting New Round...\n");
		gameBoard.resetGameBoard(gameEngine.getPlayerList());
		
		mainFrame.revalidate();
		mainFrame.repaint();
		gameEngine.resetPlayers();
		gameEngine.setCardsDelt(false); 
		gameEngine.startRound();
	}

	private void displayGameEndTimer() {
		// Clear any current items in the game message panel
		gameBoard.getGameMessagePanel().removeAll();
		
		// max value of progressbar in seconds
		timerStartTime = 15;				
		
		// Set Layout
		gameBoard.getGameMessagePanel().setLayout(new BoxLayout(gameBoard.getGameMessagePanel(), BoxLayout.Y_AXIS));
		gameBoard.getGameMessagePanel().setBackground(new Color(0, 153, 0, 255));
		
		// Make JPanels for organizing items
		JPanel gameEndTimerPanel = new JPanel();
		JPanel progressBarPanel = new JPanel(new BorderLayout());
		
		// Set Panel Attributes
		progressBarPanel.setBackground(new Color(0,153,0,255));
		gameEndTimerPanel.setPreferredSize(new Dimension(720, 10));
		gameEndTimerPanel.setLayout(new BoxLayout(gameEndTimerPanel, BoxLayout.X_AXIS));	
		
		
		// Create JLabel that will act as Title for ProgressBar
		JLabel gameEndLabel = new JLabel();
		gameEndLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		gameEndLabel.setForeground(Color.BLACK);
		gameEndLabel.setBackground(new Color(0,153,0,255));
		gameEndLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		gameEndLabel.setText("All Human players have no Money.  Ending Game...");
		
		// Create ProgressBar
		gameEndProgressBar.setMinimum(0);
		gameEndProgressBar.setMaximum(timerStartTime);
		gameEndProgressBar.setValue(timerStartTime);
		
		// Build the helper Panel for the progressBar
		progressBarPanel.add(gameEndProgressBar, BorderLayout.CENTER);
		
		// Build the helper panel to center progressBar
		gameEndTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		gameEndTimerPanel.add(progressBarPanel);
		gameEndTimerPanel.add(Box.createRigidArea(new Dimension(160, 10)));
		gameEndTimerPanel.setBackground(new Color(0,153,0,255));
		gameEndTimerPanel.setVisible(true);
		
		// Display the progressBar
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,20)));
		gameBoard.getGameMessagePanel().add(gameEndLabel);
		gameBoard.getGameMessagePanel().add(gameEndTimerPanel);
		gameBoard.getGameMessagePanel().add(Box.createRigidArea(new Dimension(720,120)));
		gameBoard.getGameMessagePanel().setBackground(new Color(0, 153, 0, 255));
		gameBoard.getGameMessagePanel().revalidate();
		
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

