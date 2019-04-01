/**
 * GameEngine class is the main logic engine for the game.
 * 
 * This class is responsible for carrying out player actions,
 * for determining winners, and creating instances of the dealer
 * and deckShoe.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cscsi_1082.finalproject.blackjack.Rank;						// Import the Rank enum
import cscsi_1082.finalproject.blackjack.PlayerType;				// Import the playerType enum
import cscsi_1082.finalproject.blackjack.PlayOption;				// Import the PlayOption enum
import cscsi_1082.finalproject.blackjack.StartingOption;			// Import the StartingOption enum

public class GameEngine {
	
	private Dealer dealer;
	private Shoe deckShoe;
	private boolean quit;
	private List<Player> playerList;								// Declare array to hold player list
	private boolean roundOver;
	public static final int MAXTOTAL = 21;
	public static final int DEALERPLAYER = 0;
	public static final double BLACKJACK_PAYOUT = 2.5;					// Blackjack pays out at 3 to 2.  This is set at 2.5 as we've already removed the original bet from the players money
	public static final int COMPUTER_MAX_BET = 30;
	public static final int COMPUTER_MIN_BET = 10;
	
	/**
	 * Constructor for the game Engine.  This will create the dealer and deckShoe
	 * A list of players must be passed in
	 * 
	 * @param playerList
	 */
	public GameEngine() {
		dealer = new Dealer("Dealer", PlayerType.DEALER);
		deckShoe = new Shoe();
		this.quit = false;
		this.playerList = new ArrayList<Player>();
		this.roundOver = false;
		this.playerList.add(dealer);								// Add the dealer to the playerList.  It will always be at element 0
	}
	

	/**
	 * Method to determine if the round should be over.  Either the dealer has gotten blackjack right away
	 * or everyone has completed all their actions.
	 * 
	 * @return true if round is over
	 */
	public boolean isRoundOver() {
		return roundOver;
	}

	/**
	 * Method to set the roundOver variable.  This should be set if the Dealer got blackjack right away or 
	 * if everyone's turn is over.
	 * 
	 * @param roundOver
	 */
	public void setRoundOver(boolean roundOver) {
		this.roundOver = roundOver;
	}

	/**
	 * Method to return dealer object.
	 * 
	 * @return Dealer object
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * Method to return Shoe object
	 * 
	 * @return deckShoe object
	 */
	public Shoe getDeckShoe() {
		return deckShoe;
	}

	/**
	 * Method to determine if the player has chosen to quit the game
	 * 
	 * @return boolean
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Method to set the quit variable to true
	 */
	public void setQuit() {
		this.quit = true;
	}
	
	/**
	 * Method to start a new blackjack table.  This will get the players
	 * and populate them in a player list.  It will then start the game.
	 *  
	 * @return Nothing
	 */
	public static void newTable() {
		// Create the game engine
		GameEngine gameEngine = new GameEngine();
		
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		/*
		 * Only supporting one human player at the moment
		 */
		//System.out.print("Enter the number of Human Players: ");
		//int players = input.nextInt();
		
		// Loop for validation input on valid amount of computer players.
		int compPlayers = 0;
		do {
				System.out.print("Enter the number of Computer Players: ");
				compPlayers = input.nextInt();
		
				// Drop the newLine character at the end of the buffer
				input.nextLine();
		
				// User must chose a valid number of players
				if (compPlayers + 1 > 7) {
					System.out.println("Error, invalid number of players");
				}
			} while (compPlayers + 1 > 7);
			
	{	// Create the players
		// Get player names for Human Players
		System.out.print("Enter your name: ");
		String playerName = input.nextLine();
		
		// Create the Human Player
		Player humanPlayer = new Player(playerName, PlayerType.HUMAN);
		gameEngine.playerList.add(humanPlayer);
		
		// Create the computer players if needed
		if (compPlayers > 0) {
			for (int player = 0; player < compPlayers; player++) {
				String compPlayerName = "Computer " + (player + 1);
				Player compPlayer = new Player(compPlayerName, PlayerType.COMPUTER);
				gameEngine.playerList.add(compPlayer);
			}
		}
	}
			
		// Start the first round
		gameEngine.startTable();
				
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
	private void startTable() {
		// Player has no money and no active bet.  Quit
		if (this.playerList.get(1).getPlayerMoney() <= 0) {
			System.out.println("You are Broke!  Come again when you have more money!");
			System.exit(0);		
		}
		
		// Let player play or quit
		this.getStartOption();
		
		if (this.isQuit() == true) {
			System.out.println("Thanks for Playing!");
			System.exit(0);
		}
		
		playGame();
	}
	
	/**
	 * Method to begin the rounds
	 * 
	 * This is the main game flow logic engine.  This makes all the major calls to other methods
	 * to control how the game works
	 */
	private void playGame() {	
		// Get the player bets
		this.dealer.getBets(this.playerList);
		System.out.println();
		// Message to give indication of what's happening
		System.out.println("Dealing Cards...");
		
		// Deal the cards to the players
		this.dealCards();
		System.out.println("Cards Delt");
		System.out.println();
		
		// Check if any player has blackjack.  They get paid first
		for (int player = 1; player < this.playerList.size(); player++) {
			if (this.isBlackJack(player)) {
				this.playerList.get(player).displayCards(this.playerList, player);
				System.out.println("Player: " + this.playerList.get(player).getPlayerName() + " has BlackJack! Pays out at 3 to 2");
				this.playerList.get(player).setHasBlackJack(true);
				this.dealer.payPlayer(playerList, player);
				this.playerList.get(player).setTurnOver(true);
			}
		}
		
		// Check if the Dealer has blackjack
		if (this.isBlackJack(DEALERPLAYER)) {
			System.out.println("\n*****************************************\n" + 
							   "Dealer has BlackJack! Everyone loses!\n" + 
							   "*****************************************\n");
			this.setRoundOver(true);
			this.roundOver();
		}
		
		// Loop through each player and give their options
		for (int player = 1; player < this.playerList.size(); player++) {
			while (!this.playerList.get(player).isTurnOver()) {
				System.out.println();
				
				// Display dealer's Up card
				this.dealer.displayDealersUpCard(this.playerList, DEALERPLAYER);
				System.out.println();
				
				this.playerList.get(player).displayCards(this.playerList, player);
				
				// Check if they have busted
				if (this.checkIfBusted(player)) {
					System.out.println("\nOH NO " + this.playerList.get(player).getPlayerName() + "! You've busted!\nYour Total is: " + this.playerList.get(player).getHandTotal());
					this.playerList.get(player).setTurnOver(true);
					break;
				} else {
					System.out.println("\n" + this.playerList.get(player).getPlayerName() + "'s Total is: " + this.playerList.get(player).getHandTotal());
				}
				
				// Ask the player what they want to do.  If a computer player or dealer it will auto decide
				int option = this.getPlayOption(player);

				// Perform actions player wanted
				this.processPlayOption(option, player);
				
			}
		}
		
		// Process the dealer's turn
		while (!this.playerList.get(DEALERPLAYER).isTurnOver()) {
			// Display all of the Dealer's cards
			this.playerList.get(DEALERPLAYER).displayCards(this.playerList, DEALERPLAYER);
			
			// Check if they have busted
			if (this.checkIfBusted(DEALERPLAYER)) {
				System.out.println("The Dealer Busted!!!\nYour Total is: " + this.playerList.get(DEALERPLAYER).getHandTotal());
				this.playerList.get(DEALERPLAYER).setTurnOver(true);
				continue;
			} else {
				System.out.println("Your Total is: " + this.playerList.get(DEALERPLAYER).getHandTotal());
			}
			
			// Ask the player what they want to do.  If a computer player or dealer it will auto decide
			int option = this.getPlayOption(DEALERPLAYER);

			// Perform actions player wanted
			this.processPlayOption(option, DEALERPLAYER);
		}
		
		// Everyone's turn is over.  End the round
		this.roundOver();		
	}
	
	/**
	 * Method to deal the initial set of cards to every player.  This deals to the Human player first, then the computer
	 * players, then it will deal a dealer card.  This will repeat twice.
	 * 
	 * @return nothing
	 */
	private void dealCards() {
		// Variable to track how many cards have been dealt
		int cardsDelt = 0;
		
		// Keep going until every has 2 cards
		while (cardsDelt < (this.playerList.size() * 2)) {
			
			// Loop through the player list, starting with the human player (element 1) and deal a card
			for (int player = 1; player < this.playerList.size(); player++) {
				
				// Deal a card to the dealer if we've given one to everyone else
				if (cardsDelt == (this.playerList.size() - 1) || cardsDelt == (this.playerList.size() * 2 - 1)) {
					this.playerList.get(DEALERPLAYER).setPlayerCards(this.dealer.dealCard(this.deckShoe));
					cardsDelt++;
					
					// Only reset player index to 0 if this is the first pass through dealing cards
					if (cardsDelt == this.playerList.size()) {
						player = 0;
					} else {
						// TODO: Need to look at this closer, I set it to this high number because it was looping too much.
						player = 999999;
					}
				}else {
				/*
				 * Get the top cards from the deck and add the card to the player's hand
				 */
				this.playerList.get(player).setPlayerCards(this.dealer.dealCard(this.deckShoe));
				cardsDelt++;
				}
			}
		}
		
		// Set all players initial total
		for (int player = 0; player < this.playerList.size(); player++) {
			this.getSumOfCards(player);
		}
	}
	
	/**
	 * Method for any player or the dealer to obtain a new card after being dealt their initial hand
	 * 
	 * @return another Card
	 */
	public Card hit() {
		return this.dealer.dealCard(this.deckShoe);
	}
	
	/**
	 * This method should be called to set the turnOver boolean on the player to true
	 * to indicate that their turn is now over
	 * 
	 * @return true that player has decided to end their turn
	 */
	public void stand(int player) {
		this.playerList.get(player).setTurnOver(true);		
	}
	
	/**
	 * Method to be called when the player can double down.
	 */
	public void doubleDown() {
		// TODO Fill in code for doubling
	}
	
	/**
	 * Method to allow a player to split their hand.  This creates a new playerCard list,
	 * copies the 2nd card from the original list to the new one, then removes the 2nd card from
	 * the original list.  This leaves both lists with an index 0.
	 * 
	 * The expectation would be that the function calling this increments a counter for how many hands
	 * the players has so they can be iterate through later.
	 * 
	 * @param playerCards
	 * @return new list of cards
	 */
	public List<Card> split(List<Card> playerCards){
		List<Card> newHand = new ArrayList<Card>();
		newHand.add(playerCards.get(1));
		playerCards.remove(1);
		return newHand;		
	}
	
	/**
	 * Method to handle the actions after all players have had their turn.
	 * This method checks for winners, which will in turn pay out the winners
	 * 
	 * It will also reset all the necessary variables to get ready for the next round of play
	 */
	public void roundOver() {
		// See if any player won and then reset the variables for the next round
		for (int player = 1; player < this.playerList.size(); player++) {
			this.checkForWinners(player);
			System.out.println(this.playerList.get(player).toString());
		}
		System.out.println(this.playerList.get(DEALERPLAYER).toString());
		for (int player = 0; player < this.playerList.size(); player++) {
			this.playerList.get(player).getPlayerCards().removeAll(this.playerList.get(player).getPlayerCards());		// Clear any cards from thier hand
			this.playerList.get(player).setPlayerBet(0); 																// Reset bet to 0
			this.playerList.get(player).setTurnOver(false);																// Reset turn over flag
			this.playerList.get(player).setHandTotal(0);																// Set hand Total to zero
			this.playerList.get(player).setHasBlackJack(false);															// Set blackjack flag to false
			System.out.println();
			System.out.println(this.playerList.get(player).toString());	
			System.out.println();
		}
		this.setRoundOver(false); 
		
		// Start a new round
		this.startTable();
	}
	
	/**
	 * Method to determine if a player won the hand
	 * 
	 * @param player Card list and dealer Card list
	 * @return true if winner
	 */
	public void checkForWinners(int player) {
		
		// exclude them if they got blackjack  We will pay out blackjack another way.  Blackjack always pays out
		if (this.playerList.get(player).getHasBlackJack()) {
			return;
		}
		
		// Dealer Busted, everyone wins that didn't bust
		else if (this.playerList.get(player).getHandTotal() <= MAXTOTAL && this.playerList.get(DEALERPLAYER).getHandTotal() > MAXTOTAL) {
			// Pay the player
			this.dealer.payPlayer(playerList, player);
			System.out.println(this.playerList.get(player).getPlayerName() + " WON! You got $" + this.playerList.get(player).getPlayerBet() + 
					   "\nYour total money is $" + this.playerList.get(player).getPlayerMoney());
		
		}		
		
		// Player won 
		else if (this.playerList.get(player).getHandTotal() <= MAXTOTAL && this.playerList.get(player).getHandTotal() > this.playerList.get(DEALERPLAYER).getHandTotal()) {
			this.playerList.get(player).addMoney(playerList.get(player).getPlayerBet());
			// Pay the player
			this.dealer.payPlayer(playerList, player);
			System.out.println(this.playerList.get(player).getPlayerName() + " WON! You got $" + this.playerList.get(player).getPlayerBet() + 
							   "\nYour total money is $" + this.playerList.get(player).getPlayerMoney());
			return;
		} 
		
		// Player pushed
		else if(this.playerList.get(player).getHandTotal() <= MAXTOTAL && this.playerList.get(player).getHandTotal() == this.playerList.get(DEALERPLAYER).getHandTotal()) {
			// TODO Get some way to track that they pushed and didn't straight up lose
			boolean pushed = true;
			this.dealer.payPlayer(playerList, player, pushed);
			System.out.println(this.playerList.get(player).getPlayerName() + " Pushed!" + "\nYour total money is $" + this.playerList.get(player).getPlayerMoney());
			return;
		}
		
		// Player Lost.  Don't need to collect the money.  We already removed it from their total during the bet stage.
		else {
			System.out.println(this.playerList.get(player).getPlayerName() + " Lost! You lost $" + this.playerList.get(player).getPlayerBet() + 
							   "\nYour total money is $" + this.playerList.get(player).getPlayerMoney());
			return;
		}
	}
	
	/**
	 * Method to determine if the player has blackjack.  The player must only have two cards in their hand
	 * and the sum of those cards must be MAXTOTAL
	 * 
	 * @param Card list
	 * @return true if blackjack has been made
	 */
	public boolean isBlackJack(int player) {
		if (this.playerList.get(player).getPlayerCards().size() == 2 && this.playerList.get(player).getHandTotal() == MAXTOTAL) {
			this.playerList.get(player).setHasBlackJack(true);
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Method to get the total of the cards in the players hand
	 * 
	 * @param player
	 * @return sum of the cards in the players hand
	 */
	public void getSumOfCards(int player) {
		
		int sumOfCards = 0;									// Accumulator to add sum
		boolean hasAce = false;								// Flag that the player has Aces in their hand
		int numberOfAces = 0;								// Accumulator to track how many aces they have in their hand
		
		// Loop through the players cards
		for (int card = 0; card < this.playerList.get(player).getPlayerCards().size(); card++) {
			
			// Store each card in a temporary Card instance
			Card playerCard = this.playerList.get(player).getPlayerCards().get(card);
			
			// Give a real value to each rank
			switch (playerCard.getRank()) {
				case TWO:
					sumOfCards += playerCard.getCardRankValue(Rank.TWO);
					break;
				case THREE:
					sumOfCards += playerCard.getCardRankValue(Rank.THREE);
					break;
				case FOUR:
					sumOfCards += playerCard.getCardRankValue(Rank.FOUR);
					break;
				case FIVE:
					sumOfCards += playerCard.getCardRankValue(Rank.FIVE);
					break;
				case SIX:
					sumOfCards += playerCard.getCardRankValue(Rank.SIX);
					break;
				case SEVEN:
					sumOfCards += playerCard.getCardRankValue(Rank.SEVEN);
					break;
				case EIGHT:
					sumOfCards += playerCard.getCardRankValue(Rank.EIGHT);
					break;
				case NINE:
					sumOfCards += playerCard.getCardRankValue(Rank.NINE);
					break;
				case TEN:
				case JACK:
				case QUEEN:
				case KING:
					sumOfCards += playerCard.getCardRankValue(Rank.TEN);
					break;
				
				/*
				 * Aces are unique as they can be two different values.  We handle this outside the switch
				 * just track how many they have for now
				 */
				case ACE:
					hasAce = true;
					numberOfAces++;
					break;
			}
			
			
		}
		/*
		 * Handle Aces outside of loop so they are factored in after all other cards have been summed
		 * If they have Aces, check if an Ace being 11 would bust them.  If so, make it a one
		 */
		if (hasAce == true) {
			for (int ace = 0; ace < numberOfAces; ace++) {
					if (sumOfCards + Rank.ACE.getRankValue() > MAXTOTAL) {
						sumOfCards += 1;														// Hard code Ace to be a one here
					} else {
						sumOfCards += Rank.ACE.getRankValue();
				}
			}
		}
		
		this.playerList.get(player).setHandTotal(sumOfCards);
	}
	
	/**
	 * Method to check if the player has busted.
	 * 
	 * @param player
	 * @return true if their hand is over MAXTOTAL
	 */
	public boolean checkIfBusted(int player) {
		if (this.playerList.get(player).getHandTotal() > MAXTOTAL) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to get the starting option for the human players.  This gives the player
	 * the option to bet or quit the game.  Other starting options could be added.
	 */
	public void getStartOption() {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Initialize option variable for use outside of loops
		int option = 0;
		
		do {
			// variable to store the chosen option
			System.out.print("Available options:" +
							"\n\t1: " + StartingOption.BET + 
							"\n\t2: " + StartingOption.QUIT +
							"\nWhat would you like to do? ");
			option = input.nextInt();
			input.nextLine();													// Dump buffer
			if (option < 1 || option > 2) {
				System.out.println("Invalid Option");
			}
		} while (option < 1 || option > 2);
			
		/*
		 *  The following statement will determine enum value that is represented by the int
		 *  that the customer chose in the previous step.  enum.values() returns an array of the 
		 *  items declared in the enum in the order they were declared.  Since an array is zero indexed,
		 *  We want the n - 1 option.
		 */
		StartingOption startOption = StartingOption.values()[option - 1];
		switch(startOption) {
			// No action needed if player wants to bet
			case BET:
				break;
			case QUIT:		
				this.setQuit();
				break;
			default:
				System.out.println("Something went wrong in the getStartOption function");
				System.exit(0);
		}
	}
	
	/**
	 * Method to find out what the player wants to do next.  Human players will be asked
	 * Computer and Dealer players will auto decide.
	 * 	
	 * @param player
	 * @return option player chose
	 */
	private int getPlayOption(int player) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// variable to store the chosen option
		int option = 0;
		
		switch (playerList.get(player).getType()) {
			case HUMAN:		
				// Input validation loop
				do {
					System.out.print("Available options:" +
									"\n\t1: " + PlayOption.HIT + 
									"\n\t2: " + PlayOption.STAND + 
									"\n\t3: " + PlayOption.DOUBLE_DOWN);
					// Check if the player can split
					if (playerList.get(player).canSplit(playerList, player)) {
						System.out.print("\n\t4: " + PlayOption.SPLIT);
					}
					System.out.print("\nWhat would you like to do? ");
					option = input.nextInt();
					input.nextLine();											// Dump Buffer
				} while (option < 1 || option > 4);
				
				// Input validated.  Exit loop
				break;
			case COMPUTER:
				option = this.computerDecision(player);
				break;
			case DEALER:
				option = this.dealerDecision();
				break;
			default:
				System.out.println("Something went wrong.  I'm trying to get what actions a player should take in the playGame method");
				System.exit(0);
		}

		return option;
	}
	
	/**
	 * Method to process the option the player chose to do
	 * 
	 * @param option
	 * @param player
	 */
	private void processPlayOption(int option, int player) {
		/*
		 *  The following statement will determine enum value that is represented by the int
		 *  that the customer chose in the previous step.  enum.values() returns an array of the 
		 *  items declared in the enum in the order they were declared.  Since an array is zero indexed,
		 *  We want the n - 1 option.
		 */
		PlayOption playerOption = PlayOption.values()[option - 1];
		System.out.println("Player: " + this.playerList.get(player).getPlayerName() + " Chose to " + playerOption);
		// Switch on the enum options.
		switch (playerOption) {
			case HIT:
				this.playerList.get(player).setPlayerCards(this.hit());					// Add a new card to players Hand
				this.getSumOfCards(player);												// Update the players hand total		
				break;
			case STAND:
				this.stand(player);
				break;
			case DOUBLE_DOWN:
				//this.doubleDown();
				System.out.println("Need to implement double down method");
				break;
			case SPLIT:
				// Check if the player can split
				if (this.playerList.get(player).canSplit(this.playerList, player)) {
					System.out.println("This is an available action");
				} else {
					System.out.println("INVALID OPTION");
					return;
				}
				break;
			default:
				System.out.println("Something went wrong in the processPlayOption Method!");
				System.exit(0);
		}
	}
	
	/**
	 * Method that controls the main computer player decision making process.  It looks at the 
	 * dealer's up card and determines what the best step it should do is.  The computer will 
	 * always follow the standard hit/stand chart logic.
	 * 
	 * @param player
	 * @return option the computer should do
	 */
	private int computerDecision(int player) {
		// Get the dealer's upCard
		Card dealerUpCard = dealer.getDealersUpCard(playerList, DEALERPLAYER);
				
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (int card = 0; card < playerList.get(player).getPlayerCards().size(); card ++) {
			if (playerList.get(player).getPlayerCards().get(card).getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		// TODO: Code for when to split.
		
		// Always split aces
		if (numberOfAces == 2 && playerList.get(player).getPlayerCards().size() == 2) {
			return PlayOption.SPLIT.getPlayOptionValue();
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		else if (numberOfAces > 0) {
			
			// If Computer has 19 or better, Stand
			if (playerList.get(player).getHandTotal() >= 19) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// If Computer has 17 and Dealer is showing 9 or better, hit, else Stand
			else if (dealerUpCard.getRank().getRankValue() <= Rank.EIGHT.getRankValue() && playerList.get(player).getHandTotal() == 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// HIt everything else
			else {
				return PlayOption.HIT.getPlayOptionValue();
			}
		}
		
		/*
		 * Determine Hard total options.  No Ace in hand
		 */
		else if (numberOfAces == 0) {
			
			// Stand on everything greater than or equal to 17
			if (playerList.get(player).getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Stand on any total >= 13 if the dealer is showing a six or below
			else if (playerList.get(player).getHandTotal() >= 13 && dealerUpCard.getRank().getRankValue() <= Rank.SIX.getRankValue()) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Hit on any total >= 12 and < 17 if the dealer is showing a 7 or above
			else if (playerList.get(player).getHandTotal() >= 12 && playerList.get(player).getHandTotal() < 17 &&
					 dealerUpCard.getRank().getRankValue() > Rank.SIX.getRankValue()) {
				return PlayOption.HIT.getPlayOptionValue();
			}
			
			// if hand total == 12 and dealer is showing a 4, 5, or 6 then stand
			else if (playerList.get(player).getHandTotal() == 12 && dealerUpCard.getRank().getRankValue() >= Rank.FOUR.getRankValue() &&
					 dealerUpCard.getRank().getRankValue() <= Rank.SIX.getRankValue()) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			
			}
		// Hit anything else
		return PlayOption.HIT.getPlayOptionValue();
	}
	/**
	 * Method to drive the logic behind the dealer's actions with his hand
	 * THe Dealer cannot split or double down
	 * Dealer must hit on soft 17
	 * 
	 * @param player
	 * @return option dealer wants to take
	 */
	private int dealerDecision() {
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (int card = 0; card < playerList.get(DEALERPLAYER).getPlayerCards().size(); card ++) {
			if (playerList.get(DEALERPLAYER).getPlayerCards().get(card).getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		if (numberOfAces > 0) {
			
			// If Dealer has 18 or better, Stand
			if (playerList.get(DEALERPLAYER).getHandTotal() >= 18) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		}
		
		/*
		 * Determine Hard total options.  No Ace in hand
		 */
		else if (numberOfAces == 0) {
			
			// Stand on everything greater than or equal to 17
			if (playerList.get(DEALERPLAYER).getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		} 
			
		// Hit anything else
		return PlayOption.HIT.getPlayOptionValue();
	}
}
