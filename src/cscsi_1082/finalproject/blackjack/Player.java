/**
 * Player class is responsible for tracking the players cards, their money
 * their bets, and the type of player
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	/**
	 * Enum to put a human friendly name to the playerType attribute
	 * 
	 * @author Mike Novotny
	 */
	public enum playerType {
		HUMAN,
		COMPUTER;
	}
	
	private String playerName;
	private List<Card> playerCards;											// ArrayList to hold cards in players hand
	private int playerMoney;											
	private playerType type;												// Human or Computer Player
	private int playerBet;
	private boolean turnOver;
	private int numHands;
	
	/**
	 *  Constructor to create an instance of player.  This requires that a
	 *  playerName and playerType (Human or Computer) be specified.
	 *  
	 * @param playerName
	 * @param type
	 */
	public Player(String playerName, playerType type) {
		this.playerName = playerName;
		this.type = type;
		this.playerCards = new ArrayList<Card>();
		this.playerCards.removeAll(playerCards);
		this.playerMoney = 500;												// Player starts out with $500
		this.turnOver = false;
		this.numHands = 1;
	}
	
	/**
	 * Method to get the players name
	 * 
	 * @return playerNmae String
	 */
	public String getPlayerName() {
		return playerName;
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
	 * Method to get a card from the player's Card ArrayList
	 * An index is required to get the element
	 * 
	 * @param index
	 * @return Card from player's ArrayList
	 */
	public Card getPlayerCards(int index) {
		return this.playerCards.get(index);
	}
	
	/** 
	 * Method to add a card to the player's Card ArrayList
	 * 
	 * @param card
	 */
	public void setPlayerCards(Card card) {
		this.playerCards.add(card);
	}
	
	/**
	 * Method to get how much money the player currently has
	 * 
	 * @return money player has remaining
	 */
	public int getPlayerMoney() {
		return playerMoney;
	}
	
	/** 
	 * Method to set the money a player has.
	 * 
	 * Currently the player starts with $500 which is defined in the Constructor
	 * 
	 * @param playerMoney
	 */
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	
	/**
	 * Method to get the type of this player
	 * 
	 * @return HUMAN for user player
	 * @return COMPUTER for computer player
	 */
	public playerType getType() {
		return type;
	}
	
	/**
	 * Method to set the type of player this is: HUMAN or COMPUTER
	 * 
	 * @param type
	 */
	public void setType(playerType type) {
		this.type = type;
	}
	
	/**
	 * Method to return how much the player has bet
	 * 
	 * @return amount player has bet
	 */
	public int getPlayerBet() {
		return playerBet;
	}
	
	/**
	 * Method to set the amount a player has bet
	 * 
	 * @param playerBet
	 */
	public void setPlayerBet(int playerBet) {
		this.playerBet = playerBet;
	}
	
	/**
	 * Method to add money to a players total, for example, if they win the hand
	 * 
	 * @param amount of money to add to players total
	 */
	public void addMoney(int amount) {
		this.setPlayerMoney(this.getPlayerMoney() + amount);
	}
	
	/**
	 * Method to remove money from a players total.  This method has a check
	 * if the amount being removed would put the player negative.  This check
	 * will be elsewhere but putting secondary check just in case.  If it would
	 * make them negative, just set the players money to zero.
	 * 
	 * @param amount of money to remove from players total
	 */
	public void removeMoney(int amount) {
		if (this.getPlayerMoney() - amount < 0) {									
			this.setPlayerMoney(0);
		}
		else {
			this.setPlayerMoney(this.getPlayerMoney() - amount);
		}
	}

}