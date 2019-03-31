/**
 * Dealer class extends player class.  Dealer is responsible for dealing cards
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
	
	public void displaySingleCard(List<Player> playerList, int dealerIndex) {
		System.out.println("Dealer is showing a " + playerList.get(dealerIndex).getPlayerCards().get(0).getRank() + " of " + playerList.get(dealerIndex).getPlayerCards().get(0).getSuit());
		
	}
	
	public Card getDealersUpCard(List<Player> playerList, int dealerIndex) {
		return playerList.get(dealerIndex).getPlayerCards().get(0);
	}
	
	public void payPlayer(List<Player> playerList, int player) {
		if (playerList.get(player).getHasBlackJack()) {
			playerList.get(player).addMoney((playerList.get(player).getPlayerBet() * GameEngine.BLACKJACK_PAYOUT));
		}else {
			
		playerList.get(player).addMoney(playerList.get(player).getPlayerBet() * 2);
		}
	}
	
	public void payPlayer(List<Player> playerList, int player, boolean pushed) {
		playerList.get(player).addMoney(playerList.get(player).getPlayerBet());
	}
	
	/** 
	 * Method to get the bet amount from a player.  Human players will be asked.  Computer players will
	 * bet a set amount.  Dealer doesn't bet.
	 */
	public void getBets(List<Player> playerList) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Loop through each player and get the bets
		for (int player = 1; player < playerList.size(); player++) {
			switch (playerList.get(player).getType()) {
				case HUMAN:
					do {
						System.out.print("Please enter your bet amount: $");
						double bet = input.nextDouble();
						
						if (!playerList.get(player).checkFunds(bet)) {
							System.out.println("Insufficient money to bet this amount ($" + bet + ")!\n"+
												"You have: $" + playerList.get(player).getPlayerMoney());
							
							// Set player bet to zero to ensure we stay in the loop
							playerList.get(player).setPlayerBet(0);
						} else {
						playerList.get(player).setPlayerBet(bet);
						}
						
						// Dump buffer
						if (input.hasNextLine()) {
							input.nextLine();
						}
					} while (playerList.get(player).getPlayerBet() <= 0);
					
					// Remove the bet from the players total
					takeBet(playerList, player);
					break;

				case COMPUTER:
					// Generate a random bet for the computer between 10 and 30
					Random randomBet = new Random();
					int computerBet = randomBet.nextInt((30-10)+1) + 10;
					
					System.out.println("The computers random bet is: " + computerBet);
					playerList.get(player).setPlayerBet(computerBet);									// use a default bet for the computer
					
					// Computer doesn't have enough money.  Set his bet to the remainder of his money
					if (!playerList.get(player).checkFunds(playerList.get(player).getPlayerBet())) {
						playerList.get(player).setPlayerBet(playerList.get(player).getPlayerMoney());
					}
					// Remove the bet from the players total
					takeBet(playerList, player);
					break;
				
				// The dealer doesn't bet.  Do nothing.
				case DEALER:
					break;
						
				default:
					System.out.println("Something went wrong!.  I am trying to get the type of player in the playGame function!");
					System.exit(0);
			}
		}
	}
	
	public void takeBet(List<Player> playerList, int player) {
		playerList.get(player).removeMoney(playerList.get(player).getPlayerBet());
	}
}
