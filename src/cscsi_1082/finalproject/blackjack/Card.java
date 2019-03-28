/**
 * Card class is responsible for tracking the suit and rank of each Card
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

public class Card {
	
	/**
	 * Enum to make human readable suits for the Cards
	 * 
	 * @author Mike Novotny
	 */
	public enum Suit {
	    SPADES, 
	    HEARTS, 
	    DIAMONDS,
	    CLUBS;  
	}
	
	/**
	 * Enum to make human readable ranks for the Cards
	 * 
	 * @author Mike Novotny
	 */
	public enum Rank {
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN,
		JACK,
		QUEEN,
		KING,
		ACE;
	}
	
	private Suit suit;
	private Rank rank;
	
	/**
	 * Constructor.  A suit and rank must be specified to create an instance of this class
	 * 
	 * @param suit
	 * @param rank
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * Method to get the suit of a Card
	 * 
	 * @return Suit of card
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Method to get the Rank of a Card
	 * 
	 * @return Rank of Card
	 */
	public Rank getRank() {
		return rank;
	}

	
	@Override
	public String toString() {
		String cardInfo = rank + " of " + suit;
		return cardInfo;
	}
}
