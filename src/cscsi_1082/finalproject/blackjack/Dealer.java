package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	// Instance Variables
	private String dealerName = "Dealer";
	private List<Card> dealerCards = new ArrayList<Card>();		// List to hold cards in Dealers hand
	
	// Getters and Setters
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public List<Card> getDealerCards() {
		return dealerCards;
	}
	public void setDealerCards(List<Card> dealerCards) {
		this.dealerCards = dealerCards;
	}


	// Constructor
	public Dealer() {
		this.dealerCards.removeAll(dealerCards);
	}
	
	/*
	 * Method to deal a new card
	 */
	public Card dealCard(Shoe deckShoe) {
		if (this.getCard(deckShoe) == null) {			// If getCard returns null then the deckShoe is empty.  Must create a new one
			deckShoe.createDeckShoe();				// TODO: Must put in some notice to user that deck is being reshuffled
		}
		return this.getCard(deckShoe);					// Return a card
	}
	
	/*
	 * Method to get a card from the deckShoe
	 */
	private Card getCard(Shoe deckShoe) {
		if (deckShoe.isEmpty()) {
			return null;
		}
		else {
			Card card = deckShoe.getNextCard();		// Get the first card off the deck
			return card;
		}
	}
	
}
