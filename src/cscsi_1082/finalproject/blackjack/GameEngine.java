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
	private List<Player> playerList;									// Declare array to hold player list
	private boolean roundOver;
	private boolean isMutable;
	private int numHumanPlayers;
	public static Player THEDEALER;										// Static Constant to the Dealer Player Object
	public static final int MAXTOTAL = 21;
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
		dealer = new Dealer("Dealer", PlayerType.DEALER, 0);
		deckShoe = new Shoe();
		this.quit = false;
		this.playerList = new ArrayList<Player>();
		this.roundOver = false;
		this.isMutable = true;
		this.numHumanPlayers = 0;
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
	 * Method to prevent changes to the DealerPlayer index.  Set this to true to block changes
	 * 
	 * @param mutable
	 */
	public void setMutable(boolean mutable) {
		this.isMutable = mutable;
	}
	
	/**
	 * Method to set THEDEALER Object 
	 * 
	 * @param index
	 */
	public void setDealerObject() {
		if (this.isMutable) {
			GameEngine.THEDEALER = this.playerList.get(this.playerList.size() - 1);
			this.setMutable(false);
		} else {
			System.out.println("ERROR! THEDEALER is not mutable!");
		}
	}
	
	
	/**
	 * Method to start a new blackjack table.  
	 *  
	 * @return Nothing
	 */
	public static void newTable() {
		// Create the game engine
		GameEngine gameEngine = new GameEngine();
		
		// Start a new BlackJack table
		gameEngine.startTable();
	}
	
	/**
	 * Method to populate the new BlackJack table with players
	 */
	public void startTable() {
		
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Loop through and populate the seats.  Dealer is seat 0, start at 1
		int newSeat = 1;
		int compPlayers = 0;
		do {
			System.out.print("Seat " + (newSeat) + ":" + 
							 "\n\t(e)mpty" + 
							 "\n\t(h)uman player" + 
							 "\n\t(c)omputer player" + 
							 "\nHow do you want to fill Seat " + (newSeat) + "? ");
			
			char seatOption = input.nextLine().toLowerCase().charAt(0);
			
			switch (seatOption) {
				case 'e':
					newSeat++;
					break;
				case 'h':
					System.out.print("Enter your name: ");
					String playerName = input.nextLine();
					Player humanPlayer = new Player(playerName, PlayerType.HUMAN, newSeat);
					this.playerList.add(humanPlayer);
					this.numHumanPlayers++;
					newSeat++;
					break;
				case 'c':
					String compPlayerName = "Computer " + (compPlayers + 1);
					Player compPlayer = new Player(compPlayerName, PlayerType.COMPUTER, newSeat);
					this.playerList.add(compPlayer);
					newSeat++;
					compPlayers++;
					break;
				default:
					System.out.println("Error!  Invalid selection");
					break;
			} 
		} while (newSeat < 8);
			
		// Add the dealer to the playerList.
		this.playerList.add(this.dealer);	
		
		// Create a constant pointer to the dealer:
		this.setMutable(true);
		this.setDealerObject();
		
		// Start the first round
		this.startRound();
				
	}

	
	/**
	 * Method to begin the cycle of rounds.  This method first checks the players has chosen
	 * to quit or is out of money.  If false, then it gives the player the option to quit or play.
	 * If they want to play it starts the BlackJack round.
	 * 
	 * Each round comes back to this method. 
	 * 
	 * @param None
	 * @return Nothing
	 */
	private void startRound() {
		// Create a temporary list to store players that need to be removed from the main playerList		
		List<Player> playersToRemove = new ArrayList<Player>();
		
		// Loop through the players to see if the game should start
		for (Player currentPlayer : this.playerList) {
			switch (currentPlayer.getType()) {
				case COMPUTER:
					// Computer player has no money, remove them from table
					if (currentPlayer.getPlayerMoney() <= 0) {
						System.out.println(currentPlayer.getPlayerName() + " is out of money!  They have left the table.");
						playersToRemove.add(currentPlayer);
					}
					break;
				case HUMAN: 
					// Player has no money.  Quit
					if (currentPlayer.getPlayerMoney() <= 0) {
						System.out.println("Sorry " + currentPlayer.getPlayerName() + " !  You are Broke!  Come again when you have more money!");
						playersToRemove.add(currentPlayer);
						this.numHumanPlayers--;
					} else {
						// Let player play or quit
						this.getStartOption(currentPlayer);
					}
					
					if (this.isQuit() == true) {
						System.out.println("Thanks for Playing!");
						System.out.println(currentPlayer.getPlayerName() + " has left the table.");
						playersToRemove.add(currentPlayer);
						this.numHumanPlayers--;
					}
					break;
				case DEALER:
					break;
				default:
					System.out.println("Error! Can't find player!");
					System.exit(0);						
			}
		}
		
		// Remove players if needed
		if (playersToRemove.size() > 0) {
			this.playerList.removeAll(playersToRemove);
		}
		
		// Check if there are no more human players left in the game
		if (this.numHumanPlayers <= 0) {
			System.out.println("All human players left or are out of money.  Ending Game");
			System.exit(0);
		} else {		
			playGame();
		}
	}
	
	/**
	 * Method to begin the rounds
	 * 
	 * This is the main game flow logic engine.  This makes all the major calls to other methods
	 * to control how the game works
	 */
	private void playGame() {	
		// Get the player bets
		for (Player currentPlayer : this.playerList) {
			this.dealer.getBets(currentPlayer);
		}
		
		// Message to give indication of what's happening
		System.out.println("\nDealing Cards...");
		
		// Deal the cards to the players
		this.dealCards();
		System.out.println("Cards Delt\n");
		
		// Check if any player has blackjack.  They get paid first
		for (Player currentPlayer : this.playerList) {
			switch (currentPlayer.getType()) {
				case HUMAN:
				case COMPUTER:
					if (this.isBlackJack(currentPlayer)) {
						System.out.println("**********************************************************");
						currentPlayer.getPlayerHands().get(0).displayPlayerHand(currentPlayer.getPlayerHands().get(0));
						System.out.println("Player: " + currentPlayer.getPlayerName() + " has BlackJack! Pays out at 3 to 2!" + 
										   "\nYou Won $" + (currentPlayer.getPlayerBet() * GameEngine.BLACKJACK_PAYOUT));
						this.dealer.payPlayer(currentPlayer);
						System.out.println("**********************************************************");
						currentPlayer.setTurnOver(true);
					}
					break;
				case DEALER:
					// Check if the Dealer has blackjack
					if (this.isBlackJack(currentPlayer)) {
						System.out.println("\n*****************************************\n" + 
										   "Dealer has BlackJack! Everyone loses!\n" + 
									   	   "*****************************************\n");
						this.setRoundOver(true);
						this.roundOver();
					}
					break;				
			}
		}
		

		// Loop through each player and give their options
		for (Player currentPlayer : this.playerList) {
			switch (currentPlayer.getType()) {
				case HUMAN:
				case COMPUTER:
					while (!currentPlayer.isTurnOver()) {
						System.out.println();
				
						// Display dealer's Up card
						this.dealer.displayDealersUpCard(THEDEALER);
						System.out.println();
				
						currentPlayer.getPlayerHands().get(0).displayPlayerHand(currentPlayer.getPlayerHands().get(0));
				
						// Check if they have busted
						if (this.checkIfBusted(currentPlayer)) {
								System.out.println("\nOH NO " + currentPlayer.getPlayerName() + "! You've busted!\nYour Total is: " + currentPlayer.getHandTotal());
								currentPlayer.setTurnOver(true);
								break;
						} else {
							System.out.println("\n" + currentPlayer.getPlayerName() + "'s Total is: " + currentPlayer.getHandTotal());
						}
				
						// Ask the player what they want to do.  If a computer player or dealer it will auto decide
						int option = this.getPlayOption(currentPlayer);

						// Perform actions player wanted
						this.processPlayOption(option, currentPlayer);
				
					}
				case DEALER:
					while (!currentPlayer.isTurnOver()) {
						// Display all of the Dealer's cards
						currentPlayer.getPlayerHands().get(0).displayPlayerHand(currentPlayer.getPlayerHands().get(0));
						
						// Check if they have busted
						if (this.checkIfBusted(currentPlayer)) {
							System.out.println("The Dealer Busted!!!\nDealer's Total is: " + currentPlayer.getHandTotal());
							currentPlayer.setTurnOver(true);
							continue;
						} else {
							System.out.println("Dealer's Total is: " + currentPlayer.getHandTotal());
						}
						
						// Ask the player what they want to do.  If a computer player or dealer it will auto decide
						int option = this.getPlayOption(currentPlayer);

						// Perform actions player wanted
						this.processPlayOption(option, currentPlayer);
				}
			}
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
		int cardsPerPlayer = 0;
			
		// Keep going until every has 2 cards
		while (cardsPerPlayer < 2) {
			
			// Loop through the player list, starting with the human player (element 1) and deal a card
			for (Player currentPlayer : this.playerList) {
				// Get the top cards from the deck and add the card to the player's hand
				currentPlayer.getPlayerHands().get(0).addCard(this.dealer.dealCard(this.deckShoe));
				
				// the second card has just been dealt.  Update the players hand total
				if (cardsPerPlayer > 0) {
					this.getSumOfCards(currentPlayer);
				}
			}
			cardsPerPlayer++;
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
	public void stand(Player currentPlayer) {
		currentPlayer.setTurnOver(true);		
	}
	
	/**
	 * Method to allow a player to double down.  A player is only allowed a single card
	 * in exchange for being able to double their bet.
	 * 
	 * @param player
	 */
	public void doubleDown(Player currentPlayer) {
		/*
		 * Remove the money from the players total first.  Doubling down is the original bet * 2
		 * the original bet is already removed from the users total so just remove it again.
		 */
		this.dealer.takeBet(currentPlayer);
		
		// Update the bet amount to reflect the new total
		currentPlayer.setPlayerBet(currentPlayer.getPlayerBet() * 2);
		

		// Give the user one more card
		currentPlayer.getPlayerHands().get(0).addCard(this.hit());					// Add a new card to players Hand
		this.getSumOfCards(currentPlayer);											// Update the players hand total	
		
		// Display the updated cards
		currentPlayer.getPlayerHands().get(0).displayPlayerHand(currentPlayer.getPlayerHands().get(0));
		
		// Check if they have busted
		if (this.checkIfBusted(currentPlayer)) {
			System.out.println("\nOH NO " + currentPlayer.getPlayerName() + "! You've busted!\nYour Total is: " + currentPlayer.getHandTotal());
		} else {
			System.out.println("\n" + currentPlayer.getPlayerName() + "'s Total is: " + currentPlayer.getHandTotal());
		}
		
		// Turn is over
		currentPlayer.setTurnOver(true);		
	}
	
	/**
	 * Method to allow a player to split their hand.  This creates a new playerCard list,
	 * copies the 2nd card from the original list to the new one, then removes the 2nd card from
	 * the original list.  This leaves both lists with an index 0.
	 * 
	 * The expectation would be that the function calling this increments a counter for how many hands
	 * the players has so they can be iterate through later.
	 * 
	 * TODO: Splitting is going to take some thought.  It will take quite an overhaul to support splitting hands
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
		for (Player currentPlayer : this.playerList) {
			this.checkForWinners(currentPlayer);

			// Reset the players for the next round
			currentPlayer.resetPlayer(currentPlayer);
		}
		
		// Reset the roundOver flag
		this.setRoundOver(false); 
		
		// Start a new round
		this.startRound();
	}
	
	/**
	 * Method to determine if a player won the hand
	 * 
	 * @param player Card list and dealer Card list
	 * @return true if winner
	 */
	public void checkForWinners(Player currentPlayer) {
		switch (currentPlayer.getType()) {
			case HUMAN:
			case COMPUTER:
				// exclude them if they got blackjack  We will pay out blackjack another way.  Blackjack always pays out
				if (currentPlayer.getHasBlackJack()) {
					return;
				}
		
				// Dealer Busted, everyone wins that didn't bust
				else if (currentPlayer.getHandTotal() <= MAXTOTAL && THEDEALER.getHandTotal() > MAXTOTAL) {
					// Pay the player
					this.dealer.payPlayer(currentPlayer);
					System.out.println(currentPlayer.getPlayerName() + " WON! You got $" + currentPlayer.getPlayerBet() + 
					   "\nYour total money is $" + currentPlayer.getPlayerMoney());
				}		
		
				// Player beat Dealer 
				else if (currentPlayer.getHandTotal() <= MAXTOTAL && currentPlayer.getHandTotal() > THEDEALER.getHandTotal()) {
					// Pay the player
					this.dealer.payPlayer(currentPlayer);
					System.out.println(currentPlayer.getPlayerName() + " WON! You got $" + currentPlayer.getPlayerBet() + 
							   "\nYour total money is $" + currentPlayer.getPlayerMoney());
				} 
		
				// Player pushed
				else if(currentPlayer.getHandTotal() <= MAXTOTAL && currentPlayer.getHandTotal() == THEDEALER.getHandTotal()) {
					this.dealer.payPlayer(currentPlayer, true);
					System.out.println(currentPlayer.getPlayerName() + " Pushed!" + "\nYour total money is $" + currentPlayer.getPlayerMoney());
				}
		
				// Player Lost.  Don't need to collect the money.  We already removed it from their total during the bet stage.
				else {
					System.out.println(currentPlayer.getPlayerName() + " Lost! You lost $" + currentPlayer.getPlayerBet() + 
							   "\nYour total money is $" + currentPlayer.getPlayerMoney());
				}
				break;
			case DEALER:
				// Dealer can't get paid, just break the loop
				break;
		}
	}
	
	/**
	 * Method to determine if the player has blackjack.  The player must only have two cards in their hand
	 * and the sum of those cards must be MAXTOTAL
	 * 
	 * @param Card list
	 * @return true if blackjack has been made
	 */
	public boolean isBlackJack(Player currentPlayer) {
		if (currentPlayer.getPlayerHands().get(0).getPlayerHand().size() == 2 && currentPlayer.getHandTotal() == MAXTOTAL) {
			currentPlayer.setHasBlackJack(true);
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
	public void getSumOfCards(Player currentPlayer) {
		
		int sumOfCards = 0;									// Accumulator to add sum
		boolean hasAce = false;								// Flag that the player has Aces in their hand
		int numberOfAces = 0;								// Accumulator to track how many aces they have in their hand
		
		// Loop through the players cards
		for (int card = 0; card < currentPlayer.getPlayerHands().get(0).getPlayerHand().size(); card++) {
			
			// Store each card in a temporary Card instance
			Card playerCard = currentPlayer.getPlayerHands().get(0).getPlayerHand().get(card);
			
			// Give a real value to each rank
			switch (playerCard.getRank()) {
				case TWO:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case THREE:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case FOUR:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case FIVE:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case SIX:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case SEVEN:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case EIGHT:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case NINE:
					sumOfCards += playerCard.getCardRankValue();
					break;
				case TEN:
				case JACK:
				case QUEEN:
				case KING:
					sumOfCards += playerCard.getCardRankValue();
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
		
		currentPlayer.setHandTotal(sumOfCards);
	}
	
	/**
	 * Method to check if the player has busted.
	 * 
	 * @param player
	 * @return true if their hand is over MAXTOTAL
	 */
	public boolean checkIfBusted(Player currentPlayer) {
		if (currentPlayer.getHandTotal() > MAXTOTAL) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to get the starting option for the human players.  This gives the player
	 * the option to bet or quit the game.  Other starting options could be added.
	 */
	public void getStartOption(Player currentPlayer) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// Initialize option variable for use outside of loops
		int option = 0;
		
		do {
			// variable to store the chosen option
			System.out.print("\n" + currentPlayer.getPlayerName() + ", what would you like to do?" + 
							"\nAvailable options:" +
							"\n\t1: " + StartingOption.BET + 
							"\n\t2: " + StartingOption.QUIT +
							"\nEnter Choice: ");
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
	private int getPlayOption(Player currentPlayer) {
		// Create scanner to get keyboard input
		Scanner input = new Scanner(System.in);
		
		// variable to store the chosen option
		int option = 0;
		
		switch (currentPlayer.getType()) {
			case HUMAN:		
				// Input validation loop
				do {
					System.out.print("\n" + currentPlayer.getPlayerName() + ", what would you like to do? " + 
									"Available options:" +
									"\n\t1: " + PlayOption.HIT + 
									"\n\t2: " + PlayOption.STAND + 
									"\n\t3: " + PlayOption.DOUBLE_DOWN +
									"\n\t4: " + PlayOption.SPLIT + 
									"\nEnter Choice: ");
					option = input.nextInt();
					input.nextLine();											// Dump Buffer
					
					// Check if the player has enough funds to double down.
					if (option == 3 && !currentPlayer.checkFunds(currentPlayer, PlayOption.DOUBLE_DOWN)) {
						System.out.println("Sorry, You do not have enough money to double down.");
						// Reset option to zero so we stay in the loop
						option = 0;	
					}

					// Check if the player can split
					if (option == 4 && !currentPlayer.getPlayerHands().get(0).canSplit(currentPlayer.getPlayerHands().get(0))) {
						System.out.println("Sorry, You cannot split this hand.  Cards must be a pair.");
						// Reset option to zero so we stay in the loop
						option = 0;												
					}
					
				} while (option < 1 || option > 4);
				
				// Input validated.  Exit loop
				break;
			case COMPUTER:
				option = this.computerDecision(currentPlayer);
				break;
			case DEALER:
				option = this.dealerDecision(currentPlayer);
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
	private void processPlayOption(int option, Player currentPlayer) {
		/*
		 *  The following statement will determine enum value that is represented by the int
		 *  that the customer chose in the previous step.  enum.values() returns an array of the 
		 *  items declared in the enum in the order they were declared.  Since an array is zero indexed,
		 *  We want the n - 1 option.
		 */
		PlayOption playerOption = PlayOption.values()[option - 1];
		System.out.println("Player: " + currentPlayer.getPlayerName() + " Chose to " + playerOption);
		// Switch on the enum options.
		switch (playerOption) {
			case HIT:
				currentPlayer.getPlayerHands().get(0).addCard(this.hit());					// Add a new card to players Hand
				this.getSumOfCards(currentPlayer);												// Update the players hand total		
				break;
			case STAND:
				this.stand(currentPlayer);
				break;
			case DOUBLE_DOWN:
				this.doubleDown(currentPlayer);
				break;
			case SPLIT:
				// TODO: Call some function to process the split.
				
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
	private int computerDecision(Player currentPlayer) {
		// Get the dealer's upCard
		Card dealerUpCard = dealer.getDealersUpCard(THEDEALER);
				
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (int card = 0; card < currentPlayer.getPlayerHands().get(0).getPlayerHand().size(); card ++) {
			if (currentPlayer.getPlayerHands().get(0).getPlayerHand().get(card).getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		// TODO: Code for when to split.
		
		// Always split aces
		if (numberOfAces == 2 && currentPlayer.getPlayerHands().get(0).getPlayerHand().size() == 2) {
			return PlayOption.SPLIT.getPlayOptionValue();
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		else if (numberOfAces > 0) {
			
			// If Computer has 19 or better, Stand
			if (currentPlayer.getHandTotal() >= 19) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// If Computer has 17 and Dealer is showing 9 or better, hit, else Stand
			else if (dealerUpCard.getRank().getRankValue() <= Rank.EIGHT.getRankValue() && currentPlayer.getHandTotal() == 17) {
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
			if (currentPlayer.getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Stand on any total >= 13 if the dealer is showing a six or below
			else if (currentPlayer.getHandTotal() >= 13 && dealerUpCard.getRank().getRankValue() <= Rank.SIX.getRankValue()) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Hit on any total >= 12 and < 17 if the dealer is showing a 7 or above
			else if (currentPlayer.getHandTotal() >= 12 && currentPlayer.getHandTotal() < 17 &&
					 dealerUpCard.getRank().getRankValue() > Rank.SIX.getRankValue()) {
				return PlayOption.HIT.getPlayOptionValue();
			}
			
			// if hand total == 12 and dealer is showing a 4, 5, or 6 then stand
			else if (currentPlayer.getHandTotal() == 12 && dealerUpCard.getRank().getRankValue() >= Rank.FOUR.getRankValue() &&
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
	private int dealerDecision(Player currentPlayer) {
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (int card = 0; card < currentPlayer.getPlayerHands().get(0).getPlayerHand().size(); card ++) {
			if (currentPlayer.getPlayerHands().get(0).getPlayerHand().get(card).getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		if (numberOfAces > 0) {
			
			// If Dealer has 18 or better, Stand
			if (currentPlayer.getHandTotal() >= 18) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		}
		
		/*
		 * Determine Hard total options.  No Ace in hand
		 */
		else if (numberOfAces == 0) {
			
			// Stand on everything greater than or equal to 17
			if (currentPlayer.getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		} 
			
		// Hit anything else
		return PlayOption.HIT.getPlayOptionValue();
	}
}
