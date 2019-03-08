/**
 * Blackjack class is the starting point for the Blackjack game.
 * 
 * This class is responsible for getting players and initializing an
 * instance of the game engine.
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
	
	/**
	 * Constructor - calls a private method to start the game
	 */
	public Blackjack () {
		this.newTable();
	}
	
	/**
	 * Method to start a new blackjack table.  This will get the players
	 * and populate them in a player list.  It will then start the game.
	 *  
	 * @return Nothing
	 */
	private void newTable() {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the number of Human Players: ");
		int players = input.nextInt();
		
		System.out.print("Enter the number of Computer Players: ");
		int compPlayers = input.nextInt();
		
		// User must chose a valid number of players
		if (players + compPlayers > 7 || players < 1 || players > 7 || compPlayers > 6) {
			System.out.println("Error, invalid number of players");
		}
		else
		{	// Create the players
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
		}
		
		// Close the Scanner
		input.close();
	
		// Start the first round
		this.startTable(playerList);
				
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
	private void startTable(List<Player> playerList) {
		// Create the game engine
		GameEngine gameEngine = new GameEngine(playerList);
		
		// Continue until player is out of money or quits
		while (this.playerList.get(0).getPlayerMoney() > 0 || this.playerList.get(0).getPlayerBet() > 0 || gameEngine.isQuit() == false) {
			gameEngine.playGame();
		}
		
	}
	
	/**
	 * Main method.  This starts and instance of the BlackJack class
	 * @param args
	 */
	public static void main(String[] args) {
		Blackjack gameOfBlackjack = new Blackjack();
		
		}
	}
