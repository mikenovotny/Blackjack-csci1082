/**
 * Player class is responsible for tracking the players cards, their money
 * their bets, and the type of player
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import cscsi_1082.finalproject.blackjack.PlayerType;

public class Player {
	
	private String playerName;
	private List<PlayerHands> playerHands;									// ArrayList to hold the hands a player has
	private double playerMoney;											
	private PlayerType type;												// Human or Computer Player
	private int seat;
	private double playerBet;
	private boolean turnOver;
	private int handTotal;													// Value to hold the sum of their hand
	private boolean hasBlackJack;
	
	/**
	 *  Constructor to create an instance of player.  This requires that a
	 *  playerName and playerType (Human or Computer) be specified.
	 *  
	 * @param playerName
	 * @param type
	 */
	public Player(String playerName, PlayerType type, int seat) {
		this.playerName = playerName;
		this.type = type;
		this.playerHands = new ArrayList<PlayerHands>();
		this.createFirstHand();												// Create default hand object		
		this.playerMoney = 500;												// Player starts out with $500
		this.turnOver = false;
		this.handTotal = 0;
		this.hasBlackJack = false;
		this.seat = seat;
	}
	
	/**
	 * Method to get the players name
	 * 
	 * @return playerNmae String
	 */
	public String getPlayerName() {
		return this.playerName;
	}
	
	/**
	 * Method to set the player name
	 * 
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	/**
	 * Method to return the hands the player has
	 *
	 * @return ArrayList holding the players hands
	 */
	public List<PlayerHands> getPlayerHands() {
		return this.playerHands;
	}
	
	/**
	 * Method to add a new hand to a player
	 * 
	 * @param hand
	 */
	public void addPlayerHand(PlayerHands hand) {
		this.playerHands.add(hand);
	}
	
	/**
	 * Method to get how much money the player currently has
	 * 
	 * @return money player has remaining
	 */
	public double getPlayerMoney() {
		return this.playerMoney;
	}
	
	/** 
	 * Method to set the money a player has.
	 * 
	 * Currently the player starts with $500 which is defined in the Constructor
	 * 
	 * @param playerMoney
	 */
	public void setPlayerMoney(double playerMoney) {
		this.playerMoney = playerMoney;
	}
	
	/**
	 * Method to get the type of this player
	 * 
	 * @return HUMAN for user player
	 * @return COMPUTER for computer player
	 */
	public PlayerType getType() {
		return this.type;
	}
	
	/**
	 * Method to set the type of player this is: HUMAN or COMPUTER
	 * 
	 * @param type
	 */
	public void setType(PlayerType type) {
		this.type = type;
	}
	
	/**
	 * Method to return how much the player has bet
	 * 
	 * @return amount player has bet
	 */
	public double getPlayerBet() {
		return this.playerBet;
	}
	
	/**
	 * Method to set the amount a player has bet
	 * 
	 * @param playerBet
	 */
	public void setPlayerBet(double playerBet) {
		this.playerBet = playerBet;
	}
	
	
	/**
	 * Method to determine if the players turn is over
	 * 
	 * @return true if turn is over
	 */
	public boolean isTurnOver() {
		return this.turnOver;
	}

	/**
	 * Method to set a players turn to be over
	 * 
	 */
	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}
	
	/**
	 * Method to get the value of a players hand
	 * 
	 * @return integer value of the players hand
	 */
	public int getHandTotal() {
		return this.handTotal;
	}

	/**
	 * Method to set the value of a players hand
	 * 
	 * @param handTotal
	 */
	public void setHandTotal(int handTotal) {
		this.handTotal = handTotal;
	}

	/**
	 * Method to get the flag that the player has BlackJack
	 * 
	 * @return true if player has blackjack else false
	 */
	public boolean getHasBlackJack() {
		return this.hasBlackJack;
	}

	/**
	 * Method to set the flag that a player has BlackJack
	 * 
	 * @param hasBlackJack
	 */
	public void setHasBlackJack(boolean hasBlackJack) {
		this.hasBlackJack = hasBlackJack;
	}

	/**
	 * Method to get a players seat index	
	 * 
	 * @return seat index
	 */
	public int getSeat() {
		return seat;
	}

	/**
	 * Method to set a players seat index
	 * 
	 * @param seat
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * Method to add money to a players total, for example, if they win the hand
	 * 
	 * @param amount of money to add to players total
	 */
	public void addMoney(Player currentPlayer, double amount) {
		this.setPlayerMoney(this.getPlayerMoney() + amount);
	}
	
	/**
	 * Method to check if a player has enough money to make the bet they want
	 * 
	 * @param amount
	 * @return true if they have enough money else false
	 */
	public boolean checkFunds(Player currentPlayer) {
		if (currentPlayer.getPlayerMoney() - currentPlayer.getPlayerBet() < 0) {									
			return false;
		}
		return true;
	}
	
	/**
	 * Overloaded method to check for funds when Doubling down 
	 * 
	 * @param currentPlayer
	 * @param option must be DOUBLE_DOWN
	 * @return true if they have enough money to double the bet
	 */
	public boolean checkFunds(Player currentPlayer, PlayOption option) {
		// Only allow this to be called with the option DOUBLE_DOWN
		if (option != PlayOption.DOUBLE_DOWN) {
			System.out.println("Invalid Option!  This only supports DOUBLE_DOWN");
			return false;
		}
		
		// Check if the player has the funds to double his bet
		if (currentPlayer.getPlayerMoney() - (currentPlayer.getPlayerBet() * 2) < 0 ) {									
			return false;
		}
		return true;
	}
	
	/**
	 * Method to add a default hand object to the player
	 */
	public void createFirstHand() {
		// Create a default hand
		List<Card> firstHand = new ArrayList<Card>();
		PlayerHands firstPlayerHand = new PlayerHands(firstHand);
		this.addPlayerHand(firstPlayerHand);
	}
	
	/**
	 * Method to remove money from a players total.  This method has a check
	 * if the amount being removed would put the player negative.  This check
	 * will be elsewhere but putting secondary check just in case.  If it would
	 * make them negative, just set the players money to zero.
	 * 
	 * @param amount of money to remove from players total
	 */
	public void removeMoney(double amount) {
			this.setPlayerMoney(this.getPlayerMoney() - amount);
	}
	
	/**
	 * Method to reset a player's attributes for the start of the next round
	 */
	
	public void resetPlayer(Player currentPlayer) {
		currentPlayer.playerHands.removeAll(currentPlayer.getPlayerHands());
		currentPlayer.createFirstHand(); 																// Create default hand object
		currentPlayer.setPlayerBet(0); 																	// Reset bet to 0
		currentPlayer.setTurnOver(false);																// Reset turn over flag
		currentPlayer.setHandTotal(0);																	// Set hand Total to zero
		currentPlayer.setHasBlackJack(false);															// Set blackjack flag to false
	}

	/**
	 * toString method for this class
	 */
	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerHands=" + playerHands + ", playerMoney=" + playerMoney
				+ ", type=" + type + ", playerBet=" + playerBet + ", turnOver=" + turnOver 
				+ ", handTotal=" + handTotal + ", hasBlackJack=" + hasBlackJack + "]";
	}
}