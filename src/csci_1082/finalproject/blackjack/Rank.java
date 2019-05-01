/**
 * Rank Enum.  This enum give friendly names to card Ranks.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package csci_1082.finalproject.blackjack; 

public enum Rank {
	TWO			(2),
	THREE		(3),
	FOUR		(4),
	FIVE		(5),
	SIX			(6),
	SEVEN		(7),
	EIGHT		(8),
	NINE		(9),
	TEN			(10),
	JACK		(10),
	QUEEN		(10),
	KING		(10),
	ACE			(11);								// Aces will default to being an 11 for ease of relational comparisons.  The game logic will set to 1 if needed

	private final int rankValue;					// Instance variable that stores the integer value of each card
	
	/**
	 * Constructor for enum
	 * 
	 * @param rankValue
	 */
	Rank(int rankValue) {
		this.rankValue = rankValue;
	}
	
	/**
	 * Method to get the integer value associated with the enum
	 * 
	 * @return integer equivalent of rank
	 */
	public final int getRankValue() {
		return rankValue;
	}
}


