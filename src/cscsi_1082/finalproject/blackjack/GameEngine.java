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

import cscsi_1082.finalproject.blackjack.Rank;
import cscsi_1082.finalproject.blackjack.Suit;
import cscsi_1082.finalproject.blackjack.Player.playerType;			// Import the playerType enum from Player Class
import cscsi_1082.finalproject.blackjack.Option;					// Import the Option enum

public class GameEngine {
	
	private Dealer dealer;
	private Shoe deckShoe;
	private boolean quit;
	private List<Player> playerList;								// Declare array to hold player list
	private boolean roundOver;
	private final int MAXTOTAL = 21;
	private final int DEALERPLAYER = 0;
	private final double BLACKJACK_PAYOUT = 1.5;					// Blackjack pays out at 3 to 2
	
	/**
	 * Constructor for the game Engine.  This will create the dealer and deckShoe
	 * A list of players must be passed in
	 * 
	 * @param playerList
	 */
	public GameEngine() {
		dealer = new Dealer("Dealer", playerType.DEALER);
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
		System.exit(0);
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
		
		System.out.print("Enter the number of Computer Players: ");
		int compPlayers = input.nextInt();
		
		// Drop the newLine character at the end of the buffer
		input.nextLine();
		
		// User must chose a valid number of players
		if (compPlayers + 1 > 7) {
			System.out.println("Error, invalid number of players");
		}
		else
		{	// Create the players
			// Get player names for Human Players
			System.out.print("Enter your name: ");
			String playerName = input.nextLine();
			
			// Create the Human Player
			Player humanPlayer = new Player(playerName, playerType.HUMAN);					// TODO: Need to add some protection if we fail to make a player
			gameEngine.playerList.add(humanPlayer);

		
			// Create the computer players if needed
			if (compPlayers > 0) {
				for (int player = 0; player < compPlayers; player++) {
					String compPlayerName = "Computer " + (player + 1);
					Player compPlayer = new Player(compPlayerName, playerType.COMPUTER);				// TODO: Need to add some protection if we fail to make a player
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
		// Continue until player is out of money or quits
		while (this.playerList.get(1).getPlayerMoney() > 0 || this.playerList.get(1).getPlayerBet() > 0 || this.isQuit() == false) {
			playGame();
		}
		
	}
	
	/**
	 * Method to begin the rounds
	 * 
	 * Lots of stuff needs to go here
	 */
	private void playGame() {	
		// Get the player bets
		this.getBets();
		
		// Message to give indication of what's happening
		System.out.println("Dealing Cards...");
		
		// Deal the cards to the players
		this.dealCards();
		System.out.println("Cards Delt");
		
		// Check if any player has blackjack.  They get paid first
		for (int player = 1; player < this.playerList.size(); player++) {
			if (this.isBlackJack(player)) {
				playerList.get(player).displayCards(this.playerList, player);
				System.out.println("Player: " + this.playerList.get(player).getPlayerName() + " has BlackJack! Pays out at 3 to 2");
				this.playerList.get(player).setHasBlackJack(true);
				this.playerList.get(player).addMoney(this.playerList.get(player).getPlayerBet() * BLACKJACK_PAYOUT);
				this.playerList.get(player).setTurnOver(true);
			}
		}
		
		// Check if the Dealer has blackjack
		if (this.isBlackJack(DEALERPLAYER)) {
			System.out.println("Dealer has BlackJack! Everyone loses!");
			this.setRoundOver(true);
			this.roundOver();
		}
		
		// Loop through each player and give their options
		for (int player = 1; player < this.playerList.size(); player++) {
			while (!playerList.get(player).isTurnOver()) {
				dealer.displaySingleCard(playerList, DEALERPLAYER);
				
				playerList.get(player).displayCards(this.playerList, player);
				
				// Check if they have busted
				if (this.checkIfBusted(player)) {
					System.out.println("OH NO! You've busted!\nYour Total is: " + this.playerList.get(player).getHandTotal());
					playerList.get(player).setTurnOver(true);
					continue;
				} else {
					System.out.println("Your Total is: " + this.playerList.get(player).getHandTotal());
				}
				
				// Ask the player what they want to do.  If a computer player or dealer it will auto decide
				int option = this.getOption(player);

				// Perform actions player wanted
				this.processOption(option, player);
				
		}
	}
		System.exit(0);

		
		
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
		while (cardsDelt < (playerList.size() * 2)) {
			
			// Loop through the player list, starting with the human player (element 1) and deal a card
			for (int player = 1; player < playerList.size(); player++) {
				
				// Deal a card to the dealer if we've given one to everyone else
				if (cardsDelt == (playerList.size() - 1) || cardsDelt == (playerList.size() * 2 - 1)) {
					playerList.get(DEALERPLAYER).setPlayerCards(dealer.dealCard(this.deckShoe));
					cardsDelt++;
					
					// Only reset player index to 0 if this is the first pass through dealing cards
					if (cardsDelt == playerList.size()) {
						player = 0;
					} else {
						// TODO: Need to look at this closer, I set it to this high number because it was looping too much.
						player = 999999;
					}
				}else {
				/*
				 * Get the top cards from the deck and add the card to the player's hand
				 */
				playerList.get(player).setPlayerCards(dealer.dealCard(this.deckShoe));
				cardsDelt++;
				}
			}
		}
		
		// Set all players initial total
		for (int player = 0; player < playerList.size(); player++) {
			this.getSumOfCards(player);
		}
	}
	
	/**
	 * Method for any player or the dealer to obtain a new card after being dealt their initial hand
	 * 
	 * @return another Card
	 */
	public Card hit() {
		return dealer.dealCard(this.deckShoe);
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
	
	public void roundOver() {
		// See if any player won
		for (int player = 1; player < this.playerList.size(); player++) {
			this.isWinner(player);
		}
		
		// Reset values for next round
		for (int player = 0; player < this.playerList.size(); player++) {
			this.playerList.get(player).getPlayerCards().removeAll(this.playerList.get(player).getPlayerCards());		// Clear any cards from thier hand
			this.playerList.get(player).setPlayerBet(0); 																// Reset bet to 0
			this.playerList.get(player).setTurnOver(false);																// Reset turn over flag
			this.playerList.get(player).setHandTotal(0);																// Set hand Total to zero
			this.playerList.get(player).setHasBlackJack(false);															// Set blackjack flag to false
			this.setRoundOver(false); 																					// set roundOver flag to false
		}
		
		// Start a new round
		this.playGame();
	}
	
	/**
	 * Method to determine if a player won the hand
	 * 
	 * @param player Card list and dealer Card list
	 * @return true if winner
	 */
	public boolean isWinner(int player) {
		
		// exclude them if they got blackjack  We will pay out blackjack another way.  Blackjack always pays out
		if (playerList.get(player).getHasBlackJack()) {
			return true;
		}
		
		// Player won 
		else if (playerList.get(player).getHandTotal() <= MAXTOTAL && playerList.get(player).getHandTotal() > playerList.get(DEALERPLAYER).getHandTotal()) {
			playerList.get(player).addMoney(playerList.get(player).getPlayerBet());
			System.out.println("You WON! You got $" + playerList.get(player).getPlayerBet() + 
							   "\nYour total money is $" + playerList.get(player).getPlayerMoney());
			return true;
		} 
		
		// Player pushed
		else if(playerList.get(player).getHandTotal() <= MAXTOTAL && playerList.get(player).getHandTotal() == playerList.get(DEALERPLAYER).getHandTotal()) {
			// TODO Get some way to track that they pushed and didn't straight up lose
			System.out.println("You Pushed!" + "\nYour total money is $" + playerList.get(player).getPlayerMoney());
			return false;
		}
		
		// Player Lost
		else {
			playerList.get(player).removeMoney(playerList.get(player).getPlayerBet());
			System.out.println("You Lost! You lost $" + playerList.get(player).getPlayerBet() + 
							   "\nYour total money is $" + playerList.get(player).getPlayerMoney());
			return false;
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
			
			// Handle Aces.  If they have Aces, check if an Ace being 11 would bust them.  If so, make it a one
			if (hasAce == true) {
				for (int ace = 0; ace < numberOfAces; ace++) {
					if (sumOfCards + playerCard.getCardRankValue(Rank.ACE) > MAXTOTAL) {
						sumOfCards += 1;														// Hard code Ace to be a one here
					} else {
						sumOfCards += playerCard.getCardRankValue(Rank.ACE);
					}
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
	 * Method to get the bet amount from a player.  Human players will be asked.  Computer players will
	 * bet a set amount.  Dealer doesn't bet.
	 */
	private void getBets() {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Loop through each player and get the bets
		for (int player = 0; player < this.playerList.size(); player++) {
			switch (playerList.get(player).getType()) {
				case HUMAN:
					System.out.print("Please enter your bet amount: $");
					double bet = input.nextDouble();
					playerList.get(player).setPlayerBet(bet);
					playerList.get(player).removeMoney(playerList.get(player).getPlayerBet());
					// Dump buffer
					if (input.hasNextLine()) {
						input.nextLine();
					}
					break;

				case COMPUTER:
					playerList.get(player).setPlayerBet(50);									// use a default bet for the computer
					playerList.get(player).removeMoney(playerList.get(player).getPlayerBet());
					break;
					
				case DEALER:
					playerList.get(player).setPlayerBet(0);										// The dealer doesn't bet
					break;
						
				default:
					System.out.println("Something went wrong!.  I am trying to get the type of player in the playGame function!");
					System.exit(0);
			}
		}
	}
	
	/**
	 * Method to find out what the player wants to do next.  Human players will be asked
	 * Computer and Dealer players will auto decide.
	 * 	
	 * @param player
	 * @return option player chose
	 */
	private int getOption(int player) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// variable to store the chosen option
		int option = 0;
		switch (playerList.get(player).getType()) {
			case HUMAN:			
				System.out.print("Available options:" +
								 "\n\t1: " + Option.HIT + 
								 "\n\t2: " + Option.STAND + 
						 		 "\n\t3: " + Option.DOUBLE_DOWN);
				// Check if the player can split
				if (playerList.get(player).canSplit(playerList, player)) {
					System.out.print("\n\t4: " + Option.SPLIT);
				}
				System.out.print("\n\t9: " + Option.QUIT + "\n");
				System.out.println("What would you like to do? ");
				option = input.nextInt();
				break;
			case COMPUTER:
				option = this.computerDecision(player);
				break;
			
			case DEALER:
				// TODO: put logic here for what the dealer can do
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
	private void processOption(int option, int player) {
		/*
		 *  The following statement will determine enum value that is represented by the int
		 *  that the customer chose in the previous step.  enum.values() returns an array of the 
		 *  items declared in the enum in the order they were declared.  Since an array is zero indexed,
		 *  We want the n - 1 option.
		 */
		Option playerOption = Option.values()[option - 1];
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
				if (playerList.get(player).canSplit(playerList, player)) {
					System.out.println("This is an available action");
				} else {
					System.out.println("INVALID OPTION");
					return;
				}
				break;
			case QUIT:
				this.setQuit();
				break;
			default:
				System.out.println("Something went wrong.  I was processing options in playGame method!");
				System.exit(0);
		}
	}
	
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
			return Option.SPLIT.getOptionValue();
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		else if (numberOfAces > 0) {
			
			// If Computer has 19 or better, Stand
			if (playerList.get(player).getHandTotal() >= 19) {
				return Option.STAND.getOptionValue();
			}
			
			// If Computer has 17 and Dealer is showing 9 or better, hit, else Stand
			else if (dealerUpCard.getRank().getRankValue() <= Rank.EIGHT.getRankValue() && playerList.get(player).getHandTotal() == 17) {
				return Option.STAND.getOptionValue();
			}
			
			// HIt everything else
			else {
				return Option.HIT.getOptionValue();
			}
		}
		
		/*
		 * Determine Hard total options.  No Ace in hand
		 */
		else if (numberOfAces == 0) {
			
			// Stand on everything greater than or equal to 17
			if (playerList.get(player).getHandTotal() >= 17) {
				return Option.STAND.getOptionValue();
			}
			
			// Stand on any total >= 13 if the dealer is showing a six or below
			else if (playerList.get(player).getHandTotal() >= 13 && dealerUpCard.getRank().getRankValue() <= Rank.SIX.getRankValue()) {
				return Option.STAND.getOptionValue();
			}
			
			// Hit on any total >= 12 and < 17 if the dealer is showing a 7 or above
			else if (playerList.get(player).getHandTotal() >= 12 && playerList.get(player).getHandTotal() < 17 &&
					 dealerUpCard.getRank().getRankValue() > Rank.SIX.getRankValue()) {
				return Option.HIT.getOptionValue();
			}
			
			// if hand total == 12 and dealer is showing a 4, 5, or 6 then stand
			else if (playerList.get(player).getHandTotal() == 12 && dealerUpCard.getRank().getRankValue() >= Rank.FOUR.getRankValue() &&
					 dealerUpCard.getRank().getRankValue() <= Rank.SIX.getRankValue()) {
				return Option.STAND.getOptionValue();
			}
			
			
			}
		// Hit anything else
		return Option.HIT.getOptionValue();
	}
}
