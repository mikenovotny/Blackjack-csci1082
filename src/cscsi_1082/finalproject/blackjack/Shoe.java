package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cscsi_1082.finalproject.blackjack.Card.Rank;
import cscsi_1082.finalproject.blackjack.Card.Suit;

public class Shoe {
	
	// A Casino Deck shoe typically holds 8 decks
	private final int NUMBEROFDECKS = 8;
	
	/*
	 * Blackjack uses and 8 shoe deck.  Create an ArrayList of cards
	 */
	private  List<Card> deckShoe = new ArrayList<Card>();
	
	/*
	 * Method to populate the deckShoe
	 */
	public void createDeckShoe() {
		for (int deck = 1; deck < NUMBEROFDECKS; deck++) {		// Iterate through these actions for the required amount of decks
			
			// For every suit, and for every rank, create a new instance of a Card class and add it to the deckShoe list			
			for (Suit suit : Suit.values()) {	
				for (Rank rank : Rank.values()) {
					Card card = new Card(suit, rank);
					deckShoe.add(card);
				}
			}
		}
	}
	
	/*
	 * Method to shuffle the deck.
	 */
	public void shuffleDeckShoe() {
		this.clearDeckShoe();	
		this.createDeckShoe();
		Collections.shuffle(this.deckShoe);
	}
	
	/*
	 * Method to ensure the deckShoe List has no elements in it
	 */
	private void clearDeckShoe() {
		deckShoe.removeAll(deckShoe);
	}

	@Override
	public String toString() {
		return "Shoe [deckShoe=" + deckShoe + "]";
	}
	
	public static void main(String[] args) {
		Shoe test = new Shoe();
		test.shuffleDeckShoe();
		for (Card card : test.deckShoe) {
			System.out.println(card);
		}
	}

}
