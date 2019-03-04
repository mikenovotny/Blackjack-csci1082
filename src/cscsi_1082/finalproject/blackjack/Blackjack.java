package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cscsi_1082.finalproject.blackjack.Player.playerType;

public class Blackjack {
	
	private List<Player>  playerList = new ArrayList<Player>();		// Declare array to hold player list
	private Dealer dealer = new Dealer();
	private Shoe deckShoe = new Shoe();
	
	public Blackjack (int players, int compPlayers) {
		// User must chose a valid number of players
		if (players + compPlayers > 7 || players < 1 || players > 7 || compPlayers > 6) {
			System.out.println("Error, invalid number of players");
		}
		else {
			this.startGame(players, compPlayers);
		}
	}
		
	private void startGame(int players, int compPlayers) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Create the players
		
		for (int player = 0; player < players; player++) {
			// Get player names
			System.out.print("Enter Player " + (player + 1) + "'s name: ");
			String playerName = input.nextLine();
			
			// Create the player
			Player humanPlayer = new Player(playerName, playerType.HUMAN);					// TODO: Need to add some protection if we fail to make a player
			playerList.add(humanPlayer);
		}
		for (int player = 0; player < compPlayers; player++) {
			String playerName = "Computer " + (player + 1);
			Player compPlayer = new Player(playerName, playerType.COMPUTER);				// TODO: Need to add some protection if we fail to make a player
			playerList.add(compPlayer);
		}
		
		// Close the Scanner
		input.close();
	
		// Start the first round
		this.startRound();
	}
	
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
	
	
	public static void main(String[] args) {
		Blackjack gameOfBlackjack = new Blackjack(1,0);
		
		}
	}
