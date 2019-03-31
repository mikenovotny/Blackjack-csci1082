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
		COMPUTER,
		DEALER;
	}
	
	private String playerName;
	private List<Card> playerCards;											// ArrayList to hold cards in players hand
	private double playerMoney;											
	private playerType type;												// Human or Computer Player
	private double playerBet;
	private boolean turnOver;
	private int numHands;
	private int handTotal;													// Value to hold the sum of their hand
	private boolean hasBlackJack;
	
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
		this.handTotal = 0;
		this.hasBlackJack = false;
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
	public List<Card> getPlayerCards() {
		return this.playerCards;
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
	public double getPlayerMoney() {
		return playerMoney;
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
	public double getPlayerBet() {
		return playerBet;
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
		return turnOver;
	}

	/**
	 * Method to set a players turn to be over
	 * 
	 */
	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}
	
	/**
	 * Method to reset all player's turnOver to false for the next round
	 * 
	 * @param playerList
	 */
	public void resetTurnOver(List<Player> playerList) {
		for (int player = 0; player < playerList.size(); player++) {
			playerList.get(player).setTurnOver(false);
		}
	}

	public int getNumHands() {
		return numHands;
	}

	public void setNumHands(int numHands) {
		this.numHands = numHands;
	}
	

	public int getHandTotal() {
		return handTotal;
	}

	public void setHandTotal(int handTotal) {
		this.handTotal = handTotal;
	}
	
	

	public boolean getHasBlackJack() {
		return hasBlackJack;
	}

	public void setHasBlackJack(boolean hasBlackJack) {
		this.hasBlackJack = hasBlackJack;
	}

	/**
	 * Method to add money to a players total, for example, if they win the hand
	 * 
	 * @param amount of money to add to players total
	 */
	public void addMoney(double amount) {
		this.setPlayerMoney(this.getPlayerMoney() + amount);
	}
	
	public boolean checkFunds(double amount) {
		if (this.getPlayerMoney() - amount < 0) {									
			return false;
		} else {
			return true;
		}
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
	
	public void displayCards(List<Player> playerList, int player) {
		for (int card = 0; card < playerList.get(player).playerCards.size(); card ++) {
			System.out.println("Card " + (card + 1) + ": " + playerList.get(player).playerCards.get(card));
		}
	}
	
	public boolean canSplit (List<Player> playerList, int player) {
			if (playerList.get(player).playerCards.get(0).getRank() == playerList.get(player).playerCards.get(1).getRank()) {
				return true;
			} else {
				return false;
			}		
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerCards=" + playerCards + ", playerMoney=" + playerMoney
				+ ", type=" + type + ", playerBet=" + playerBet + ", turnOver=" + turnOver + ", numHands=" + numHands
				+ ", handTotal=" + handTotal + ", hasBlackJack=" + hasBlackJack + "]";
	}
	
	

}