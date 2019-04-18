/**
 * Shoe class is responsible for creating the deckShoe.
 * This class populates and shuffles the deckShoe. It also
 * provides the ability to get cards from the deckShoe.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;

import cscsi_1082.finalproject.blackjack.Rank;					// Import the Rank enum
import cscsi_1082.finalproject.blackjack.Suit;					// Import the Suit enum

public class Shoe {
		
	private final int NUMBEROFDECKS = 8;								// A Casino Deck shoe typically holds 8 decks
	private  List<Card> deckShoe = new ArrayList<Card>();				// Create a ArrayList to hold all the cards
	
	/**
	 * Constructor
	 * 
	 * Calls createDeckShoe method to initialize the ArrayList
	 * 
	 * @param None
	 */
		public Shoe() {
			createDeckShoe();
		}
		
	/**
	 * This method initializes the deckShoe.  It ensures the ArrayList is clear
	 * then it repopulates and finally randomizes the Cards in the ArrayList
	 * 
	 * @param None
	 * @return Nothing
	 */
	public void createDeckShoe() {
		this.clearDeckShoe();	
		this.populateDeckShoe();
		Collections.shuffle(this.deckShoe);
	}
		
	/**
	 * This method clears the deckShoe ArrayList of all entries.
	 * This ensures we are starting with a fresh group of cards
	 * 
	 * TODO: Future functionality would be to repopulate the deckShoe
	 * 		 ArrayList when a certain percentage of Card elements are left.
	 * 		 This method will be crucial to ensure the ArrayList is clear
	 * 
	 * @param None
	 * @return Nothing
	 */
	private void clearDeckShoe() {
		deckShoe.removeAll(deckShoe);
	}
	
	/**
	 * This method populates the deckShoe with the required number of instances of the
	 * Card class into the ArrayList.
	 * 
	 * It iterate through these actions for the required amount of decks
	 * For every suit, and for every rank, create a new instance of a Card 
	 * class and add it to the deckShoe list
	 * 
	 * @param None
	 * @return Nothing
	 */
	private void populateDeckShoe() {
		for (int deck = 1; deck < NUMBEROFDECKS; deck++) {				
			for (Suit suit : Suit.values()) {	
				for (Rank rank : Rank.values()) {
					Card card = new Card(suit, rank);
					deckShoe.add(card);
				}
			}
		}
	}
	
	/**
	 * This method checks if the deckShoe ArrayList is empty.
	 * 
	 * @param None
	 * @return true if empty
	 * @return false if not empty
	 */
	public boolean isEmpty() {
		if (deckShoe.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method deals a card to Player or the dealer.  This method first
	 * checks if the deckShoe is empty.  If True, it re-populates the deckShoe.
	 * Then it returns the first card from the deckShoe ArrayList. 
	 * 
	 * @param deckShoe
	 * @return a Card
	 */
	public Card dealCard() {
		if (this.isEmpty()) {
			this.createDeckShoe();
		}
		return this.getNextCard();
	}	
	/**
	 * Method to get the first Card element from the list.  This is the
	 * equivalent to dealing the top card from the deck.  This method
	 * stores the first Card element in a temp value and then removes
	 * the first element from the ArrayList to ensure it is not used again.
	 * 
	 * @param None
	 * @return first Card element from ArrayList
	 */
	public Card getNextCard() {
		Card card = deckShoe.get(0);									// Get first element from ArrayList
		deckShoe.remove(0); 											// remove card from the deckShoe
		return card;
	}
	
	@Override
	public String toString() {
		return "Shoe [deckShoe=" + deckShoe + "]";
	}
}
