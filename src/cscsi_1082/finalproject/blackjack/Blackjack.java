/**
 * Blackjack class is the starting point for the Blackjack game.
 * 
 * This class is responsible for getting players, creating instances
 * of the other classes and starting the game itself.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cscsi_1082.finalproject.blackjack.Player.playerType;			// Import the playerType enum from Player Class

public class Blackjack {
	
	private List<Player>  playerList = new ArrayList<Player>();		// Declare array to hold player list
	private Dealer dealer = new Dealer();							// Create instance of Dealer
	private Shoe deckShoe = new Shoe();								// Create instance of the deckShoe
	
	/**
	 * Constructor - This class cannot be created without a valid number of players
	 * Creating this class creates an Arraylist of players
	 * Creating this class creates a Dealer instance
	 * Creating this class creates a deckShoe
	 * 
	 * TODO: Should implement some better exception handling
	 * TODO: It might be better to take the number of player check
	 * 		 and move it to the startGame method.
	 * 
	 * @param players
	 * @param compPlayers
	 * 
	 */
	public Blackjack (int players, int compPlayers) {
		// User must chose a valid number of players
		if (players + compPlayers > 7 || players < 1 || players > 7 || compPlayers > 6) {
			System.out.println("Error, invalid number of players");
		}
		else {
			this.startGame(players, compPlayers);
		}
	}
	
	/**
	 * Method to begin the actual game.  This method gets the player names
	 * and adds them to a player list.  Computer players are created if selected
	 *  
	 * @param players
	 * @param compPlayers
	 * @return Nothing
	 */
	private void startGame(int players, int compPlayers) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Create the players
		for (int player = 0; player < players; player++) {
			// Get player names for Human Players
			System.out.print("Enter Player " + (player + 1) + "'s name: ");
			String playerName = input.nextLine();
			
			// Create the Human Player
			Player humanPlayer = new Player(playerName, playerType.HUMAN);					// TODO: Need to add some protection if we fail to make a player
			playerList.add(humanPlayer);
		}
		
		// Create the computer players if needed
		if (compPlayers > 0) {
			for (int player = 0; player < compPlayers; player++) {
			String playerName = "Computer " + (player + 1);
			Player compPlayer = new Player(playerName, playerType.COMPUTER);				// TODO: Need to add some protection if we fail to make a player
			playerList.add(compPlayer);
			}
		}
		
		// Close the Scanner
		input.close();
	
		// Start the first round
		this.startRound();
	}
	
	/**
	 * Method to begin the cycle of rounds
	 * 
	 * TODO: This needs a lot of expansion.  Right now it's in a test phase
	 * 		 This method should have some looping mechanism to keep cycling
	 * 		 through the playerList and providing the options to the player
	 * 
	 * @param None
	 * @return Nothing
	 */
	private void startRound() {
	
	//while (this.playerList.get(0).getPlayerMoney() > 0 || this.playerList.get(0).getPlayerBet() > 0) {
		for (int numcards = 0; numcards < 2; numcards++) {
			playerList.get(0).setPlayerCards(this.dealer.dealCard(this.deckShoe));
		}
		System.out.println("The players cards are: ");
		for (int cardnumber = 0; cardnumber < 2; cardnumber++) {
			 System.out.println(playerList.get(0).getPlayerCards(cardnumber));
		}
	}
	
	/**
	 * Main method.  This starts and instance of the BlackJack class
	 * @param args
	 */
	public static void main(String[] args) {
		Blackjack gameOfBlackjack = new Blackjack(1,0);
		
		}
	}
