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
import cscsi_1082.finalproject.blackjack.PlayerType;

public class Dealer extends Player{
	
	/**
	 * Constructor for the dealer.  
	 * @param playerName
	 * @param type
	 */
	public Dealer(String playerName, PlayerType type) {
		super(playerName, type);
	}

	/**
	 * This method deals a card to Player or the dealer.  This method first
	 * checks if the deckShoe is empty.  If True, it re-populates the deckShoe.
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
	
	/**
	 * Method to display the dealer's Up card to the players.
	 * This method is somewhat redundant to getDealersUpCard but is done this way so it's easier to 
	 * just call this method versus having to make extra code to display it after getting it each time.
	 * 
	 * @param playerList
	 * @param dealerIndex
	 */
	public void displayDealersUpCard(List<Player> playerList, int dealerIndex) {
		System.out.println("Dealer is showing a " + playerList.get(dealerIndex).getPlayerCards().get(0).getRank() + " of " + playerList.get(dealerIndex).getPlayerCards().get(0).getSuit());
	}
	
	/**
	 * Method to get the dealer's Up card.  This is used in the computer's Decision making Logic
	 * 
	 * @param playerList
	 * @param dealerIndex
	 * @return first Card in dealer's hand
	 */
	public Card getDealersUpCard(List<Player> playerList, int dealerIndex) {
		return playerList.get(dealerIndex).getPlayerCards().get(0);
	}
	
	/**
	 * Method to pay out a winner
	 * 
	 * @param playerList
	 * @param player
	 */
	public void payPlayer(List<Player> playerList, int player) {
		if (playerList.get(player).getHasBlackJack()) {
			playerList.get(player).addMoney((playerList.get(player).getPlayerBet() * GameEngine.BLACKJACK_PAYOUT));
		}else {
			
		playerList.get(player).addMoney(playerList.get(player).getPlayerBet() * 2);
		}
	}
	
	/**
	 * Overloaded method to pay out a player if they pushed
	 * 
	 * @param playerList
	 * @param player
	 * @param pushed
	 */
	public void payPlayer(List<Player> playerList, int player, boolean pushed) {
		playerList.get(player).addMoney(playerList.get(player).getPlayerBet());
	}
	
	/** 
	 * Method to get the bet amount from a player.  Human players will be asked.  
	 * Computer players bet a random amount.  Dealer doesn't bet.
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
					this.takeBet(playerList, player);
					break;

				case COMPUTER:
					// Generate a random bet for the computer between 10 and 30
					Random randomBet = new Random();
					int computerBet = randomBet.nextInt((GameEngine.COMPUTER_MAX_BET - GameEngine.COMPUTER_MIN_BET) + 1) + GameEngine.COMPUTER_MIN_BET;
					
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
	
	/**
	 * Method to remove the amount the players bet from their total.
	 * This is done right after betting so it is not available to them for other actions
	 * such as doubling down.
	 * 
	 * @param playerList
	 * @param player
	 */
	public void takeBet(List<Player> playerList, int player) {
		playerList.get(player).removeMoney(playerList.get(player).getPlayerBet());
	}
}
