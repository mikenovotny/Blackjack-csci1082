package cscsi_1082.finalproject.blackjack;

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

}
