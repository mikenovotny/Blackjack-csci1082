package csci_1082.finalproject.blackjackGUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class ActionPanel extends JPanel {
	//Player Name components
	private JPanel playerNamePanel = new JPanel();
	private JLabel currentPlayerName = new JLabel();
	private JLabel currentPlayerNameTitle = new JLabel();
	
	// Player Info Components
	private JPanel playerInfoPanel = new JPanel();
	private JPanel playerFundsPanel = new JPanel();
	private JPanel playerFundsTitlePanel = new JPanel();
	private JLabel playerMoney = new JLabel();
	private JLabel playerBet = new JLabel();
	private JLabel playerMoneyTitle = new JLabel();
	private JLabel playerBetTitle = new JLabel();
	
	
	// Hand action panel components
	private JPanel handActionPanel = new JPanel();
	private JPanel hitStandPanel = new JPanel();
	private JPanel doubleSplitPanel = new JPanel();
	private JButton hitButton = new JButton("HIT");
	private JButton standButton = new JButton("STAND");
	private JButton doubleButton = new JButton("DOUBLE");
	private JButton splitButton = new JButton("SPLIT");
	
	// Bet panel components
	private BetPanel pokerChipBetPanel = new BetPanel();
	private JPanel betButtonPanel = new JPanel();
	private JButton increaseBet = new JButton("+");
	private JButton decreaseBet = new JButton("-");
	private JButton betButton = new JButton("BET");
	private JPanel mainBetPanel = new JPanel();
	private JPanel betSubPanel1 = new JPanel(new GridLayout(1, 2, 5, 0));
	private JPanel betSubPanel2 = new JPanel(new BorderLayout());
	
	// Game History Panel
	private JPanel gameHistoryPanel = new JPanel();
	private JPanel extrasPanel = new JPanel();
	private JButton cashOut = new JButton("Cash Out");
	private JButton cardCount = new JButton("Card Count");
	private JLabel cardCountDisplay = new JLabel();
	private JTextArea gameHistory = new JTextArea(5,10);
	
	private String baseImagePath = "/images/";
	
	
	// Constructor
	public ActionPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(300, 800));
		buildPlayerNamePanel();
		buildPlayerInfoPanel();
		buildMainBetPanel();
		buildHandActionPanel();		
		buildGameHistoryPanel();
		this.setBackground(Color.BLACK);
		this.add(playerNamePanel);
		this.add(Box.createVerticalStrut(5));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(Box.createVerticalStrut(5));
		this.add(playerInfoPanel);
		this.add(Box.createVerticalStrut(5));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(Box.createVerticalStrut(5));
		this.add(mainBetPanel);
		this.add(Box.createVerticalStrut(5));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(Box.createVerticalStrut(5));
		this.add(handActionPanel);
		this.add(Box.createVerticalStrut(5));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(Box.createVerticalStrut(5));
		this.add(gameHistoryPanel);
	}
	
	
	
	private void buildPlayerInfoPanel() {
		buildplayerFundsTitlePanel();
		buildPlayerFundsPanel();
		playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));
		playerInfoPanel.setPreferredSize(new Dimension(300, 50));
		playerInfoPanel.add(playerFundsTitlePanel);
		playerInfoPanel.add(playerFundsPanel);
		playerInfoPanel.setBackground(Color.BLACK);	
		playerInfoPanel.setVisible(true);
	}



	private void buildplayerFundsTitlePanel() {
		playerMoneyTitle.setForeground(Color.GREEN);
		playerMoneyTitle.setFont(new Font("Arial Black", Font.PLAIN, 12));
		playerMoneyTitle.setPreferredSize(new Dimension(90, 25));
		playerMoneyTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		playerMoneyTitle.setText("Player Money");
		playerBetTitle.setForeground(Color.RED);
		playerBetTitle.setFont(new Font("Arial Black", Font.PLAIN, 12));
		playerBetTitle.setPreferredSize(new Dimension(90, 25));
		playerBetTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		playerBetTitle.setText("Player Bet");
		playerFundsTitlePanel.setPreferredSize(new Dimension(300, 10));
		playerFundsTitlePanel.setLayout(new BoxLayout(playerFundsTitlePanel, BoxLayout.X_AXIS));
		playerFundsTitlePanel.add(Box.createRigidArea(new Dimension(40, 25)));
		playerFundsTitlePanel.add(playerMoneyTitle);
		playerFundsTitlePanel.add(Box.createRigidArea(new Dimension(40, 25)));
		playerFundsTitlePanel.add(playerBetTitle);
		playerFundsTitlePanel.add(Box.createRigidArea(new Dimension(40, 25)));
		playerFundsTitlePanel.setBackground(Color.BLACK);
		playerFundsTitlePanel.setVisible(true);
		
	}



	private void buildPlayerFundsPanel() {
		playerMoney.setForeground(Color.GREEN);
		playerMoney.setFont(new Font("Arial Black", Font.PLAIN, 14));
		playerMoney.setPreferredSize(new Dimension(75, 25));
		playerMoney.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		playerBet.setForeground(Color.RED);
		playerBet.setFont(new Font("Arial Black", Font.PLAIN, 14));
		playerBet.setPreferredSize(new Dimension(75, 25));
		playerBet.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		playerFundsPanel.setPreferredSize(new Dimension(300, 50));
		playerFundsPanel.setLayout(new BoxLayout(playerFundsPanel, BoxLayout.X_AXIS));
		//playerFundsPanel.add(Box.createRigidArea(new Dimension(50, 25)));
		playerFundsPanel.add(playerMoney);
		playerFundsPanel.add(Box.createRigidArea(new Dimension(50, 25)));
		playerFundsPanel.add(playerBet);
		//playerFundsPanel.add(Box.createRigidArea(new Dimension(50, 25)));
		playerFundsPanel.setBackground(Color.BLACK);
		playerFundsPanel.setVisible(true);
		
	}



	private void buildPlayerNamePanel() {
		playerNamePanel.setLayout(new BorderLayout());
		playerNamePanel.setPreferredSize(new Dimension(200, 50));
		currentPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayerName.setBackground(Color.BLACK);
		currentPlayerName.setForeground(Color.CYAN);
		currentPlayerName.setFont(new Font("Arial Black", Font.PLAIN, 25));
		currentPlayerNameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		currentPlayerNameTitle.setBackground(Color.BLACK);
		currentPlayerNameTitle.setForeground(Color.CYAN);
		currentPlayerNameTitle.setFont(new Font("Arial Black", Font.PLAIN, 20));
		currentPlayerNameTitle.setText("Current Player");
		playerNamePanel.add(currentPlayerNameTitle, BorderLayout.NORTH);
		playerNamePanel.add(currentPlayerName, BorderLayout.CENTER);
		playerNamePanel.setBackground(Color.BLACK);
		playerNamePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		playerNamePanel.setVisible(true);
	}

	private void buildHandActionPanel() {
		createButtons();
		buildHitStandPanel();
		buildDoubleSplitPanel();
		handActionPanel.setLayout(new BoxLayout(handActionPanel, BoxLayout.Y_AXIS));
		handActionPanel.setPreferredSize(new Dimension(300, 195));
		handActionPanel.setBackground(Color.BLACK);
		handActionPanel.add(Box.createRigidArea(new Dimension(300, 15)));
		handActionPanel.add(hitStandPanel);
		handActionPanel.add(Box.createRigidArea(new Dimension(300, 15)));
		handActionPanel.add(doubleSplitPanel);
		handActionPanel.add(Box.createRigidArea(new Dimension(300, 15)));
		handActionPanel.setVisible(true);
		
	}
	
	private void buildDoubleSplitPanel() {
		doubleSplitPanel.setLayout(new BoxLayout(doubleSplitPanel, BoxLayout.X_AXIS));
		doubleSplitPanel.setPreferredSize(new Dimension(300, 75));
		doubleSplitPanel.add(Box.createRigidArea(new Dimension(40, 75)));
		doubleSplitPanel.add(doubleButton);
		doubleSplitPanel.add(Box.createRigidArea(new Dimension(20, 75)));
		doubleSplitPanel.add(splitButton);
		doubleSplitPanel.add(Box.createRigidArea(new Dimension(40, 75)));
		doubleSplitPanel.setBackground(Color.BLACK);
		doubleSplitPanel.setVisible(true);
	}



	private void buildHitStandPanel() {
		hitStandPanel.setLayout(new BoxLayout(hitStandPanel, BoxLayout.X_AXIS));
		hitStandPanel.setPreferredSize(new Dimension(300, 75));
		hitStandPanel.add(Box.createRigidArea(new Dimension(40, 75)));
		hitStandPanel.add(hitButton);
		hitStandPanel.add(Box.createRigidArea(new Dimension(20, 75)));
		hitStandPanel.add(standButton);
		hitStandPanel.add(Box.createRigidArea(new Dimension(40, 75)));
		hitStandPanel.setBackground(Color.BLACK);
		hitStandPanel.setVisible(true);
	}



	
	private void createButtons() {
		hitButton.setActionCommand("HIT");
		standButton.setActionCommand("STAND");
		doubleButton.setActionCommand("DOUBLE");
		splitButton.setActionCommand("SPLIT");
		
		ImageIcon splitButtonIcon = createImageIcon(baseImagePath + "splitButtonIcon.jpg");
		ImageIcon splitButtonIconPressed = createImageIcon(baseImagePath + "splitButtonIconPressed.jpg");
		ImageIcon splitButtonIconDisabled = createImageIcon(baseImagePath + "splitButtonDisabled.jpg");
		formatButton(splitButton, splitButtonIcon, splitButtonIconPressed, splitButtonIconDisabled);
		splitButton.setEnabled(false);
		
				
		// Format and Add Double Down button
		ImageIcon doubleDownIcon = createImageIcon(baseImagePath + "doubleDownIcon.jpg");
		ImageIcon doubleDownIconPressed = createImageIcon(baseImagePath + "doubleDownIconPressed.jpg");
		ImageIcon doubleDownIconDisabled = createImageIcon(baseImagePath + "doubleDownDisabled.jpg");
		formatButton(doubleButton, doubleDownIcon, doubleDownIconPressed, doubleDownIconDisabled);
		
		
		// Format and add Stand button
		ImageIcon standButtonIcon = createImageIcon(baseImagePath + "standButtonIcon.jpg");
		ImageIcon standButtonIconPressed = createImageIcon(baseImagePath + "standButtonIconPressed.jpg");
		ImageIcon standButtonIconDisabled = createImageIcon(baseImagePath + "standButtonDisabled.jpg");
		formatButton(standButton, standButtonIcon, standButtonIconPressed, standButtonIconDisabled);
		
		
		// Format and add Hit button
		ImageIcon hitButtonIcon = createImageIcon(baseImagePath + "hitButtonIcon.jpg");
		ImageIcon hitButtonIconPressed = createImageIcon(baseImagePath + "hitButtonIconPressed.jpg");
		ImageIcon hitButtonIconDisabled = createImageIcon(baseImagePath + "hitButtonDisabled.jpg");
		formatButton(hitButton, hitButtonIcon, hitButtonIconPressed, hitButtonIconDisabled);
		
	}



	private void buildMainBetPanel() {
		buildBetSubPanel();
		buildBetButtonPanel();
		mainBetPanel.setLayout(new BoxLayout(mainBetPanel, BoxLayout.Y_AXIS));
		mainBetPanel.setPreferredSize(new Dimension(300, 240));
		mainBetPanel.add(Box.createRigidArea(new Dimension(300, 28)));
		mainBetPanel.add(betButtonPanel);
		mainBetPanel.add(Box.createRigidArea(new Dimension(300, 28)));
		mainBetPanel.setBackground(Color.BLACK);
		mainBetPanel.setVisible(true);
	}
	
	private void buildBetButtonPanel() {
		betButtonPanel.setLayout(new BoxLayout(betButtonPanel, BoxLayout.X_AXIS));
		betButtonPanel.setPreferredSize(new Dimension(300,200));
		// Format and add Bet button
		ImageIcon betButtonIcon = createImageIcon(baseImagePath + "betButtonIcon.jpg");
		ImageIcon betButtonIconPressed = createImageIcon(baseImagePath + "betButtonIconPressed.jpg");
		ImageIcon betButtonIconDisabled = createImageIcon(baseImagePath + "betButtonDisabled.jpg");
		formatButton(betButton, betButtonIcon, betButtonIconPressed, betButtonIconDisabled);
		betButton.setActionCommand("BET");
		betButton.setAlignmentY(JButton.CENTER_ALIGNMENT);
		betButtonPanel.add(betSubPanel2);
		betButtonPanel.add(Box.createRigidArea(new Dimension(25,200)));
		betButtonPanel.add(betButton);
		betButtonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		betButtonPanel.setBackground(Color.BLACK);
	}
	
	private void buildBetSubPanel() {
		
		pokerChipBetPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		
		betSubPanel1.add(increaseBet);
		betSubPanel1.add(decreaseBet);
		betSubPanel1.setBackground(Color.BLACK);
		betSubPanel1.setVisible(true);
		
		betSubPanel2.setPreferredSize(new Dimension(175, 200));
		betSubPanel2.add(betSubPanel1, BorderLayout.SOUTH);
		betSubPanel2.add(pokerChipBetPanel, BorderLayout.CENTER);	
		betSubPanel2.setBackground(Color.BLACK);
		betSubPanel2.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		betSubPanel2.setVisible(true);
	}
	
	
	private void buildGameHistoryPanel() {
		buildExtrasPanel();
		gameHistoryPanel.setLayout(new BorderLayout());
		gameHistoryPanel.setPreferredSize(new Dimension(200, 200));
		gameHistoryPanel.add(extrasPanel, BorderLayout.NORTH);
		gameHistoryPanel.add(new JScrollPane(gameHistory), BorderLayout.CENTER);
		gameHistoryPanel.setBackground(Color.BLACK);
		gameHistoryPanel.setVisible(true);
	}


	private void buildExtrasPanel() {
		cardCountDisplay.setPreferredSize(new Dimension(50, 50));
		cardCountDisplay.setFont(new Font("Arial Black", Font.PLAIN, 18));
		cardCountDisplay.setBackground(Color.BLACK);
		cardCount.setText("Display Card Count");
		cardCount.setActionCommand("displayCardCount");
		cardCount.setPreferredSize(new Dimension(125, 50));
		cashOut.setActionCommand("cashout");
		cardCount.setPreferredSize(new Dimension(50, 50));
		extrasPanel.setLayout(new BoxLayout(extrasPanel, BoxLayout.X_AXIS));
		extrasPanel.setPreferredSize(new Dimension(300, 50));
		extrasPanel.add(cashOut);
		extrasPanel.add(Box.createHorizontalStrut(5));
		extrasPanel.add(new JSeparator(SwingConstants.VERTICAL));
		extrasPanel.add(Box.createHorizontalStrut(5));
		extrasPanel.add(cardCount);
		extrasPanel.add(Box.createHorizontalStrut(10));
		extrasPanel.add(cardCountDisplay);
		extrasPanel.setBackground(Color.BLACK);		
		extrasPanel.setVisible(true);
	}



	public JLabel getCurrentPlayerName() {
		return currentPlayerName;
	}



	public void setCurrentPlayerName(JLabel currentPlayerName) {
		this.currentPlayerName = currentPlayerName;
	}



	public JLabel getPlayerMoney() {
		return playerMoney;
	}



	public void setPlayerMoney(JLabel playerMoney) {
		this.playerMoney = playerMoney;
	}



	public JLabel getPlayerBet() {
		return playerBet;
	}



	public void setPlayerBet(JLabel playerBet) {
		this.playerBet = playerBet;
	}



	public JButton getCashOut() {
		return cashOut;
	}



	public void setCashOut(JButton cashOut) {
		this.cashOut = cashOut;
	}



	public JButton getCardCountButton() {
		return cardCount;
	}



	public void setCardCount(JButton cardCount) {
		this.cardCount = cardCount;
	}



	public JLabel getCardCountDisplay() {
		return cardCountDisplay;
	}



	public void setCardCountDisplay(String count) {
		this.cardCountDisplay.setText(count);;
	}



	public JButton getHitButton() {
		return hitButton;
	}



	public void setHitButton(JButton hitButton) {
		this.hitButton = hitButton;
	}



	public JButton getStandButton() {
		return standButton;
	}



	public void setStandButton(JButton standButton) {
		this.standButton = standButton;
	}



	public JButton getDoubleButton() {
		return doubleButton;
	}



	public void setDoubleButton(JButton doubleButton) {
		this.doubleButton = doubleButton;
	}



	public JButton getSplitButton() {
		return splitButton;
	}



	public void setSplitButton(JButton splitButton) {
		this.splitButton = splitButton;
	}



	public JButton getIncreaseBet() {
		return increaseBet;
	}



	public void setIncreaseBet(JButton increaseBet) {
		this.increaseBet = increaseBet;
	}



	public JButton getDecreaseBet() {
		return decreaseBet;
	}



	public void setDecreaseBet(JButton decreaseBet) {
		this.decreaseBet = decreaseBet;
	}



	public JButton getBetButton() {
		return betButton;
	}


	public JTextArea getGameHistory() {
		return gameHistory;
	}


	
	public String getBetAmount() {
		return pokerChipBetPanel.getBetDisplay().getText();
	}



	public void setBetAmount(int betAmount) {
		pokerChipBetPanel.getBetDisplay().setText(Integer.toString(betAmount));
	}
	
	public void clearBetAmount() {
		pokerChipBetPanel.getBetDisplay().setText("");
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
	
	private void formatButton(JButton button, ImageIcon baseIcon, ImageIcon pressedIcon, ImageIcon disabledIcon) {
		button.setPreferredSize(new Dimension(100, 75));
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setIcon(baseIcon);
		button.setPressedIcon(pressedIcon);
		button.setDisabledIcon(disabledIcon);
	}

	

	
}
