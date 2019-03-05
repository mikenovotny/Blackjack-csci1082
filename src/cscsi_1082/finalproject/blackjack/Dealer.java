/**
 * Dealer class is responsible for dealing cards to players, taking
 * and paying out bets, tracking its own cards, Determining winners
 * and losers.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	
	private final String dealerName = "Dealer";									
	private List<Card> dealerCards = new ArrayList<Card>();					// List to hold cards in Dealers hand
	
	/**
	 * Constructor
	 * 
	 * Requires no Params to call.  Constructor ensures that the dealer's
	 * Card ArrayList is empty upon creation.
	 */
	public Dealer() {
		this.dealerCards.removeAll(dealerCards);
	}
	
	/**
	 * Method to get the dealerName.  This is a constant so there is no Setter
	 * 
	 * @return dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}
	
	/**
	 * Method to get the dealers cards.  This requires an index to call.
	 * The index is to retrieve the element in the list
	 * 
	 * @param index
	 * @return Card from dealer's card ArrayList
	 */
	public Card getDealerCards(int index) {
		return this.dealerCards.get(index);
	}
	
	/**
	 * Method to add a card to the Dealer's Card ArrayList
	 * 
	 * @param card
	 */
	public void setDealerCards(Card card) {
		this.dealerCards.add(card);
	}

	/**
	 * This method deals a card to Player or the dealer.  This method first
	 * checks if the deckShoe is empty.  If True, it repopulates the deckShoe.
	 * Then it returns the first card from the deckShoe ArrayList. 
	 * 
	 * @param deckShoe
	 * @return a Card
	 */
	public Card dealCard(Shoe deckShoe) {
		if (deckShoe.isEmpty()) {
			deckShoe.createDeckShoe();
		}
		return deckShoe.getNextCard();
	}	
}
