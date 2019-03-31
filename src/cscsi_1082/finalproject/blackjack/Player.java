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
import cscsi_1082.finalproject.blackjack.PlayerType;

public class Player {
	
	private String playerName;
	private List<Card> playerCards;											// ArrayList to hold cards in players hand
	private double playerMoney;											
	private PlayerType type;												// Human or Computer Player
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
	public Player(String playerName, PlayerType type) {
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
	public PlayerType getType() {
		return type;
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

	/**
	 * Method to get the number of Hands a player has.
	 * This is used when a player splits
	 *
	 * @return integer representing number of hands
	 */
	public int getNumHands() {
		return numHands;
	}

	/**
	 * Method to set the number of hands a player has
	 * 
	 * @param numHands
	 */
	public void setNumHands(int numHands) {
		this.numHands = numHands;
	}
	
	/**
	 * Method to get the value of a players hand
	 * 
	 * @return integer value of the players hand
	 */
	public int getHandTotal() {
		return handTotal;
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
		return hasBlackJack;
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
	 * Method to display the players Cards in his hand
	 * 
	 * @param playerList
	 * @param player
	 */
	public void displayCards(List<Player> playerList, int player) {
		for (int card = 0; card < playerList.get(player).playerCards.size(); card ++) {
			System.out.println("Card " + (card + 1) + ": " + playerList.get(player).playerCards.get(card));
		}
	}
	
	/**
	 * Method to determine if the player should be provided the option to split this cards into 
	 * two hands.  
	 * 
	 * @param playerList
	 * @param player
	 * @return true if both cards are the same rank else false
	 */
	public boolean canSplit (List<Player> playerList, int player) {
			if (playerList.get(player).playerCards.get(0).getRank() == playerList.get(player).playerCards.get(1).getRank()) {
				return true;
			} else {
				return false;
			}		
	}

	/**
	 * toString method for this class
	 */
	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerCards=" + playerCards + ", playerMoney=" + playerMoney
				+ ", type=" + type + ", playerBet=" + playerBet + ", turnOver=" + turnOver + ", numHands=" + numHands
				+ ", handTotal=" + handTotal + ", hasBlackJack=" + hasBlackJack + "]";
	}
}