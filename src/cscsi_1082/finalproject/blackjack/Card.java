/**
 * Card class is responsible for tracking the suit and rank of each Card
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import cscsi_1082.finalproject.blackjack.Rank;
import cscsi_1082.finalproject.blackjack.Suit;

public class Card {
	
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
	
	/**
	 * Method to get the integer value of a Rank
	 * 
	 * @param Card
	 * @return integer value equivalent of card rank
	 */
	public int getCardRankValue() {
		return this.getRank().getRankValue();
	}

	/**
	 * toString method for this Class
	 */
	@Override
	public String toString() {
		String cardInfo = rank + " of " + suit;
		return cardInfo;
	}
}
