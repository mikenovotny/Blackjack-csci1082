package cscsi_1082.finalproject.blackjack;

public class Card {
	
	/*
	 * Enum to define suits of cards
	 */
	public enum Suit {
	    SPADES, 
	    HEARTS, 
	    DIAMONDS,
	    CLUBS;  
	}
	
	/*
	 * Enum to define rank of cards
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
		JACK,
		QUEEN,
		KING,
		ACE;
	}
	
	/*
	 * A card should know it's suit and its value
	 */
	private Suit suit;
	private Rank rank;
	
	/*
	 * Getters and Setters
	 */
	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Card [suit=" + suit + ", rank=" + rank + "]";
	}

	/*
	 * Must give a suit and rank when creating an instance of Card
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
}
