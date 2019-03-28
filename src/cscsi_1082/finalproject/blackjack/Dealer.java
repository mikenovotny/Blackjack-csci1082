/**
 * Dealer class extends player class.  Dealer is responsible for dealing cards
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

public class Dealer extends Player{
	
	
	//private String playerName = "Dealer";									
//	private List<Card> dealerCards = new ArrayList<Card>();					// List to hold cards in Dealers hand
	
	/**
	 * Constructor for the dealer.  
	 * @param playerName
	 * @param type
	 */
	public Dealer(String playerName, playerType type) {
		super(playerName, type);

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
