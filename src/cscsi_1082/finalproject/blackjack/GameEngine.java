/**
 * GameEngine class is the main logic engine for the game.
 * 
 * This class is responsible for carrying out player actions,
 * for determining winners, and creating instances of the dealer
 * and deckShoe.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameEngine extends Blackjack {
	
	private Dealer dealer;
	private Shoe deckShoe;
	private boolean quit;
	
	/**
	 * Constructor for the game Engine.  This will create the dealer and deckShoe
	 * A list of players must be passed in
	 * 
	 * @param playerList
	 */
	public GameEngine(List<Player> playerList) {
		dealer = new Dealer();
		deckShoe = new Shoe();
		this.quit = false;
	}
	
	/**
	 * Method to return dealer object.
	 * 
	 * @return Dealer object
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * Method to return Shoe object
	 * 
	 * @return deckShoe object
	 */
	public Shoe getDeckShoe() {
		return deckShoe;
	}

	/**
	 * Method to determine if the player has chosen to quit the game
	 * 
	 * @return boolean
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Method to set the quit variable to true
	 */
	public void setQuit() {
		this.quit = true;
	}
	
	
	/**
	 * Method to begin the rounds
	 * 
	 * Lots of stuff needs to go here
	 */
	public void playGame() {	
		
		// TODO: Fill in all the game logic here
		
	}
	
	/**
	 * Method for any player or the dealer to obtain a new card after being dealt thier initial hand
	 * 
	 * @return another Card
	 */
	public Card hit() {
		return dealer.dealCard(this.deckShoe);
	}
	
	/**
	 * This method should be called to set the turnOver boolean on the player to true
	 * to indicate that their turn is now over
	 * 
	 * @return true that player has decided to end their turn
	 */
	public boolean stand() {
		return true;		
	}
	
	/**
	 * Method to allow a player to split their hand.  This creates a new playerCard list,
	 * copies the 2nd card from the original list to the new one, then removes the 2nd card from
	 * the original list.  This leaves both lists with an index 0.
	 * 
	 * The expectation would be that the function calling this increments a counter for how many hands
	 * the players has so they can be iterate through later.
	 * 
	 * @param playerCards
	 * @return new list of cards
	 */
	public List<Card> split(List<Card> playerCards){
		List<Card> newHand = new ArrayList<Card>();
		newHand.add(playerCards.get(1));
		playerCards.remove(1);
		return newHand;		
	}
	
	/**
	 * Method to determine if a player won the hand
	 * 
	 * @param player Card list and dealer Card list
	 * @return true if winner
	 */
	public boolean isWinner(List<Card> playerCards, List<Card> dealerCards) {
		if (
			//code that means they won
			) {
			// This should call the addMoney method in the player Class
			return true;
		}
		else {
			// This should call the removeMoney method in the Player Class
			return false;
		}
	}
	
	/**
	 * Method to determine if the player has blackjack.  The dealer should call this as well
	 * 
	 * @param Card list
	 * @return true if blackjack has been made
	 */
	public boolean isBlackJack(List<Card> Cards) {
		if (
				// Code that determines if they have blackjack
			) {
			return true;
		}
		else {
			return false;
		}			
	}
	
}
