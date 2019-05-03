/**
 * Player class is responsible for tracking the players cards, their money
 * their bets, and the type of player
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package csci_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import csci_1082.finalproject.blackjack.PlayerType;

public class Player {
	
	private String playerName;
	private List<PlayerHands> playerHands;									// ArrayList to hold the hands a player has
	private double playerMoney;											
	private PlayerType type;												// Human or Computer Player
	private int seat;
	private boolean turnOver;
	//private boolean hasBlackJack;
	
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
		//this.hasBlackJack = false;
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
	 * Method to add money to a players total, for example, if they win the hand
	 * 
	 * @param amount of money to add to players total
	 */
	public void addMoney(double amount) {
		this.setPlayerMoney(this.getPlayerMoney() + amount);
	}
	
	/**
	 * Method to check if a player has enough money to make the bet they want
	 * 
	 * @param amount
	 * @return true if they have enough money else false
	 */
	public boolean checkFunds(double amount) {
		if (this.getPlayerMoney() - amount < 0) {									
			return false;
		} else {
			return true;
		}
	}
	
	public double generateComputerBet() {
		// Generate a random bet for the computer between 10 and 30
		Random randomBet = new Random();
		int computerBet = randomBet.nextInt((GameEngine.COMPUTER_MAX_BET - GameEngine.COMPUTER_MIN_BET) + 1) + GameEngine.COMPUTER_MIN_BET;
		return computerBet;

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
	
	public void resetPlayer() {
		this.playerHands.removeAll(this.getPlayerHands());
		this.createFirstHand(); 																// Create default hand object
		this.setTurnOver(false);																// Reset turn over flag
	}

	/**
	 * toString method for this class
	 */
	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerHands=" + playerHands + ", playerMoney=" + playerMoney
				+ ", type=" + type + ", turnOver=" + turnOver + "]";
	}
}