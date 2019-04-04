/**
 * Dealer class extends player class.  Dealer is responsible for dealing cards
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.Random;
import java.util.Scanner;
import cscsi_1082.finalproject.blackjack.PlayerType;

public class Dealer extends Player{
	
	/**
	 * Constructor for the dealer.  
	 * @param playerName
	 * @param type
	 */
	public Dealer() {
		super("Dealer", PlayerType.DEALER, 0);
	}

	/**
	 * Method to display the dealer's Up card to the players. 
	 * This method is somewhat redundant to getDealersUpCard but is done this way so it's easier to 
	 * just call this method versus having to make extra code to display it after getting it each time.
	 * 
	 * @param playerList
	 * @param dealerIndex
	 */
	public void displayDealersUpCard(Player dealer) {
		System.out.println("Dealer is showing a " + this.getDealersUpCard(dealer));
	}
	
	/**
	 * Method to get the dealer's Up card.  This is used in the computer's Decision making Logic
	 * 
	 * @param playerList
	 * @param dealerIndex
	 * @return first Card in dealer's hand
	 */
	public Card getDealersUpCard(Player dealer) {	
		//return this.getPlayerHand().get(0);
		return dealer.getPlayerHands().get(0).getPlayerHand().get(0);
	}
	
	/**
	 * Method to pay out a winner
	 * 
	 * @param playerList
	 * @param player
	 */
	public void payPlayer(Player currentPlayer) {
		if (currentPlayer.getHasBlackJack()) {
			currentPlayer.addMoney(currentPlayer, (currentPlayer.getPlayerBet() * GameEngine.BLACKJACK_PAYOUT));
		}else {
			
		currentPlayer.addMoney(currentPlayer, currentPlayer.getPlayerBet() * 2);
		}
	}
	
	/**
	 * Overloaded method to pay out a player if they pushed
	 * 
	 * @param playerList
	 * @param player
	 * @param pushed
	 */
	public void payPlayer(Player currentPlayer, boolean pushed) {
		currentPlayer.addMoney(currentPlayer, currentPlayer.getPlayerBet());
	}
	
	/** 
	 * Method to get the bet amount from a player.  Human players will be asked.  
	 * Computer players bet a random amount.  Dealer doesn't bet.
	 */
	public void getBets(Player currentPlayer) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		switch (currentPlayer.getType()) {
			case HUMAN:
				do {
					System.out.print(currentPlayer.getPlayerName() + " enter your bet amount: $");
					double bet = input.nextDouble();
					currentPlayer.setPlayerBet(bet);
					
					if (!currentPlayer.checkFunds(currentPlayer)) {
						System.out.println("Insufficient money to bet this amount ($" + bet + ")!\n"+
											"You have: $" + currentPlayer.getPlayerMoney());
						
						// Set player bet to zero to ensure we stay in the loop
						currentPlayer.setPlayerBet(0);
					} else {
					currentPlayer.setPlayerBet(bet);
					}
					
					// Dump buffer
					if (input.hasNextLine()) {
						input.nextLine();
					}
				} while (currentPlayer.getPlayerBet() <= 0);
				
				// Remove the bet from the players total
				this.takeBet(currentPlayer);
				break;
			case COMPUTER:
				// Generate a random bet for the computer between 10 and 30
				Random randomBet = new Random();
				int computerBet = randomBet.nextInt((GameEngine.COMPUTER_MAX_BET - GameEngine.COMPUTER_MIN_BET) + 1) + GameEngine.COMPUTER_MIN_BET;
				
				System.out.println("The computers random bet is: " + computerBet);
				currentPlayer.setPlayerBet(computerBet);									// use a default bet for the computer
				
				// Computer doesn't have enough money.  Set his bet to the remainder of his money
				if (!currentPlayer.checkFunds(currentPlayer)) {
					currentPlayer.setPlayerBet(currentPlayer.getPlayerMoney());
				}
				// Remove the bet from the players total
				takeBet(currentPlayer);
				break;
			
				// The dealer doesn't bet.  Do nothing.
				case DEALER:
					break;
						
				default:
					System.out.println("Something went wrong!.  I am trying to get the type of player in the playGame function!");
					System.exit(0);
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
	public void takeBet(Player currentPlayer) {
		currentPlayer.removeMoney(currentPlayer.getPlayerBet());
	}
}
