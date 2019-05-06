/**
 * Dealer class extends player class.  Dealer is responsible for dealing cards
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package csci_1082.finalproject.blackjack;

import java.util.Random;
import java.util.Scanner;

import csci_1082.finalproject.blackjack.PlayerType;

public class Dealer extends Player{
	
	/**
	 * Constructor for the dealer.  
	 * @param playerName
	 * @param type
	 */
	public Dealer() {
		super("Dealer", PlayerType.DEALER, 0);
	}

	/**
	 * Method to display the dealer's Up card to the players. 
	 * This method is somewhat redundant to getDealersUpCard but is done this way so it's easier to 
	 * just call this method versus having to make extra code to display it after getting it each time.
	 * 
	 * @param playerList
	 * @param dealerIndex
	 */
	public void displayDealersUpCard(Player dealer) {
		System.out.println("Dealer is showing a " + dealer.getPlayerHands().get(0).getPlayerHand().get(1).getRank() + " of " + dealer.getPlayerHands().get(0).getPlayerHand().get(0).getSuit());
	}
	
	/**
	 * Method to get the dealer's Up card.  This is used in the computer's Decision making Logic
	 * 
	 * @param playerList
	 * @param dealerIndex
	 * @return first Card in dealer's hand
	 */
	public Card getDealersUpCard(Player dealer) {
		return dealer.getPlayerHands().get(0).getPlayerHand().get(1);
	}
	
	/**
	 * Method to pay out a winner
	 * 
	 * @param playerList
	 * @param player
	 */
	public void payPlayer(Player currentPlayer, PlayerHands hand) {
		if (hand.isBlackJack()) {
			currentPlayer.addMoney(hand.getHandBet() * GameEngine.BLACKJACK_PAYOUT);
		} else {
		currentPlayer.addMoney(hand.getHandBet() * 2);
		}
	}
	
	/**
	 * Overloaded method to pay out a player if they pushed
	 * 
	 * @param playerList
	 * @param player
	 * @param pushed
	 */
	public void payPlayer(Player currentPlayer, PlayerHands hand, boolean pushed) {
		currentPlayer.addMoney(hand.getHandBet());
	}
	
	
	/** 
	 * Method to get the bet amount from a player.  Human players will be asked.  
	 * Computer players bet a random amount.  Dealer doesn't bet.
	 */
	public void getBets(Player currentPlayer, PlayerHands hand) {
		switch (currentPlayer.getType()) {
			case HUMAN:
			case COMPUTER:
				this.takeBet(currentPlayer, hand);
				break;
			// The dealer doesn't bet.  Do nothing.
			case DEALER:
				break;
		}
	}
	
	/**
	 * Method to remove the amount the players bet from their total.
	 * This is done right after betting so it is not available to them for other actions
	 * such as doubling down.
	 * 
	 * @param playerList
	 * @param player
	 */
	public void takeBet(Player currentPlayer, PlayerHands hand) {
		currentPlayer.removeMoney(hand.getHandBet());
	}
}
