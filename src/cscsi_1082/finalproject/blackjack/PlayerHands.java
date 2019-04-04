package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class PlayerHands {
	
	private List<Card> hand;											// ArrayList to hold cards in players hand
	private final int FIRSTCARD = 0;
	private final int SECONDCARD = 1;
	
	/**
	 * Constructor.  Requires an ArrayList of Cards to be passed in
	 *  
	 * @param ArrayList of Cards
	 */
	public PlayerHands(List<Card> hand) {
		this.hand = hand;
	}
	
	/**
	 * Method to get a hand the player has
	 * 
	 * @return ArrayList of Cards
	 */
	public List<Card> getPlayerHand() {
		return hand;
	}

	/**
	 * Method to add a Card to a hand
	 * 
	 * @param Card
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * Method to display the cards in a player's hand
	 * 
	 * @param PlayerHands
	 */
	public void displayPlayerHand(PlayerHands hand) {
		for (Card card : hand.getPlayerHand()) {
			System.out.println(card);
		}
	}
	
	/**
	 * Method to determine if the player can split this hand  into two hands. 
	 * 10-K all count as the same and can be split
	 * 
	 * @param PlayerHands
	 * @return true if both cards are the same rank else false
	 */
	public boolean canSplit (PlayerHands hand) {
			if (hand.getPlayerHand().size() == 2 &&  hand.getPlayerHand().get(FIRSTCARD).getCardRankValue() == hand.getPlayerHand().get(SECONDCARD).getCardRankValue()) {
				return true;
			} else {
				return false;
			}		
	}
	
	public void splitHands (Player currentPlayer, PlayerHands hand) {
		// Create new list of cards
		List<Card> newHand = new ArrayList<Card>();
		
		// Create a new PlayerHand
		PlayerHands newPlayerHand = new PlayerHands(newHand);
		
		// Add the second card from the original hand to this new list
		newHand.add(hand.getPlayerHand().get(SECONDCARD));
		
		// Remove the second card from the original hand
		hand.getPlayerHand().remove(SECONDCARD);
		
		// Add the new hand to the player
		currentPlayer.addPlayerHand(newPlayerHand);
	}
	

	@Override
	public String toString() {
		return "PlayerHands [hand=" + hand + "]";
	}

}
