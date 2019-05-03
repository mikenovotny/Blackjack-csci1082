package csci_1082.finalproject.blackjack;

import java.util.ArrayList; 
import java.util.List;
import java.util.ListIterator;

public class PlayerHands {
	
	
	private List<Card> hand;											// ArrayList to hold cards in players hand
	private int handTotal = 0;
	private boolean handOver = false;
	private double handBet = 0;
	private final int FIRSTCARD = 0;
	private final int SECONDCARD = 1;
	private static final int MAXTOTAL = 21;
	
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
	 * Method to get the flag that the hand is over
	 * 
	 * @return true if all options on this hand are over
	 */
	public boolean isHandOver() {
		return handOver;
	}
	
/**
 * Method to set the flag that the hand is over
 * 
 * @param handOver
 */
	public void setHandOver(boolean handOver) {
		this.handOver = handOver;
	}

	/**
	 * Method to get the amount bet on this hand
	 * 
	 * @return amount bet on this hand as a double
	 */
	public double getHandBet() {
		return handBet;
	}

	/**
	 * Method to set the amount bet on this hand
	 * 
	 * @param handBet
	 */
	public void setHandBet(double handBet) {
		this.handBet = handBet;
	}

	/**
	 * Method to display the cards in a player's hand
	 * 
	 * @param PlayerHands
	 */
	public void displayPlayerHand() {
		for (Card card : this.getPlayerHand()) {
			System.out.println(card);
		}
	}
	
	/**
	 * Method to determine if the player has blackjack.  The player must only have two cards in their hand
	 * and the sum of those cards must be MAXTOTAL
	 * 
	 * @return true if hand is blackjack else false
	 */
	public boolean isBlackJack() {
		if (this.getPlayerHand().size() == 2 && this.getHandTotal() == MAXTOTAL) {
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Method to determine if the player can split this hand  into two hands. 
	 * 10-K all count as the same and can be split
	 * 
	 * @param PlayerHands
	 * @return true if both cards are the same rank else false
	 */
	public boolean canSplit () {
		/*
		 * Disabling all canSplit functionality for now
		 * 
		if (this.getPlayerHand().size() == 2 &&
		    this.getPlayerHand().get(FIRSTCARD).getCardRankValue() == this.getPlayerHand().get(SECONDCARD).getCardRankValue()) { 
				return true; 
		}
		else {
		*/ 
			return false; 

	}
	
	public void splitHands (Player currentPlayer, PlayerHands hand, ListIterator<PlayerHands> hands) {
		// Create new list of cards
		List<Card> newHand = new ArrayList<Card>();
		
		
		// Create a new PlayerHand
		PlayerHands newPlayerHand = new PlayerHands(newHand);
		
		// Add the second card from the original hand to this new list
		newHand.add(hand.getPlayerHand().get(SECONDCARD));
		
		// Set the bet amount on this hand
		newPlayerHand.setHandBet(hand.getHandBet());
		
		// Remove the second card from the original hand
		hand.getPlayerHand().remove(SECONDCARD);
				
		// Add the new hand to the player
		hands.add(newPlayerHand);
	}
	

	@Override
	public String toString() {
		return "PlayerHands [hand=" + hand + "]";
	}

}
