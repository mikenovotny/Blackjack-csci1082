/**
 * GameEngine class is the main logic engine for the game.
 * 
 * This class is responsible for carrying out player actions,
 * for determining winners, and creating instances of the dealer
 * and deckShoe.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package csci_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import csci_1082.finalproject.blackjack.PlayOption;
import csci_1082.finalproject.blackjack.PlayerType;
import csci_1082.finalproject.blackjack.Rank;
import csci_1082.finalproject.blackjack.StartingOption;
import csci_1082.finalproject.blackjackGUI.*;

public class GameEngine {
	
	private final Dealer dealer;
	private final Shoe deckShoe;
	private boolean quit;
	private List<Player> playerList;									// Declare array to hold player list
	public static final int MAXTOTAL = 21;
	public static Player DEALERPLAYER = null;
	public static PlayerHands DEALERHAND = null;
	public static final double BLACKJACK_PAYOUT = 2.5;					// Blackjack pays out at 3 to 2.  This is set at 2.5 as we've already removed the original bet from the players money
	public static final int COMPUTER_MAX_BET = 30;
	public static final int COMPUTER_MIN_BET = 10;
	private BlackjackGUI blackjackGUI;
	private Player currentGUIPlayer;
	private int currentPlayerIndex = 0;
	private boolean betsComplete = false;
	private boolean cardsDelt = false;
	
	/**
	 * Constructor for the game Engine.  This will create the dealer and deckShoe
	 * A list of players must be passed in
	 * 
	 * @param playerList
	 */
	public GameEngine() {
		dealer = new Dealer();
		deckShoe = new Shoe();
		this.quit = false;
		this.playerList = new ArrayList<Player>();
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
	
	
	
	public boolean isCardsDelt() {
		return cardsDelt;
	}

	public void setCardsDelt(boolean cardsDelt) {
		this.cardsDelt = cardsDelt;
	}

	/**
	 * Method to set the DEALERPLAYER and DEALERHAND objects
	 */
	public void updateDealerObject() {
		GameEngine.DEALERPLAYER = this.playerList.get(this.playerList.size() - 1);
		GameEngine.DEALERHAND = DEALERPLAYER.getPlayerHands().get(0);
	}
	
	
	public List<Player> getPlayerList() {
		return playerList;
	}


	public boolean isBetsComplete() {
		return betsComplete;
	}

	public void setBetsComplete(boolean betsComplete) {
		this.betsComplete = betsComplete;
	}

	/**
	 * Method to start a new blackjack table.  
	 *  
	 * @return Nothing
	 */
	public static GameEngine newTable() {
		// Create the game engine
		GameEngine gameEngine = new GameEngine();
		return gameEngine;
		
		// Start a new BlackJack table
		//gameEngine.startTable();
	}
	
	/**
	 * Method to populate the new BlackJack table with players
	 */
	public void addPlayer(String name, PlayerType type, int seat) {
		Player newPlayer = new Player(name, type, seat);
		this.playerList.add(newPlayer);
	}

	public void setGUIObject(BlackjackGUI blackjackGUI) {
		this.blackjackGUI = blackjackGUI;
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
	public void startRound() {
		for (Player currentPlayer : this.playerList) {
			switch (currentPlayer.getType()) {
				case COMPUTER:
					// Computer player has no money, remove them from table
					if (currentPlayer.getPlayerMoney() <= 0) {
						blackjackGUI.getActionPanel().getGameHistory().append(currentPlayer.getPlayerName() + " is out of money!  They have left the table.\n");
						this.playerList.remove(currentPlayer);
					}
					break;
				case HUMAN: 
					// Player has no money.  Quit
					if (currentPlayer.getPlayerMoney() <= 0) {
						blackjackGUI.getActionPanel().getGameHistory().append("Sorry " + currentPlayer.getPlayerName() + " !  You are Broke!  Come again when you have more money!\n");
						System.exit(0);
					}
					
					if (this.isQuit() == true) {
						blackjackGUI.getActionPanel().getGameHistory().append("Thanks for Playing!\n");
						System.exit(0);
					}
					break;
				case DEALER:
					// Create a constant pointer to the dealer:
					this.updateDealerObject();
					break;					
			}
		}
		// start with the first user
		currentGUIPlayer = this.playerList.get(currentPlayerIndex);
		blackjackGUI.processUser();
	}
	
	public boolean hasMorePlayers() {
		int numberOfUsers = this.playerList.size();
		System.out.println("Number of users = " + numberOfUsers);
		if (currentPlayerIndex  < numberOfUsers - 1) {
			return true;
		}
		return false;
	}
	
	public void switchToNextPlayer() {
		System.out.println("currentPlayerIndex value = " + this.currentPlayerIndex);
		if (hasMorePlayers()) {
			this.currentPlayerIndex++;
			System.out.println("new currentPlayerIndex value = " + this.currentPlayerIndex);
			this.currentGUIPlayer = this.playerList.get(this.currentPlayerIndex);
			System.out.println(this.currentGUIPlayer.toString());
		} else {
			areBetsDone();
			this.currentPlayerIndex = 0;
			this.currentGUIPlayer = this.playerList.get(this.currentPlayerIndex);
		}
		System.out.println(this.currentGUIPlayer.toString());
		blackjackGUI.processUser();
	}
	
	
	
	private void areBetsDone() {
		// Set this to true, then loop through and if we find a bet with a zero value set to false
		betsComplete = true;
		for (Player player : playerList) {
			for (PlayerHands hand : player.getPlayerHands()) {
				if (hand.getHandBet() == 0 && player.getType() != PlayerType.DEALER) {
					betsComplete = false;
				} 
			}
		}
	}

	public Player getCurrentGUIPlayer() {
		return currentGUIPlayer;
	}


	public void setCurrentGUIPlayer(Player currentGUIPlayer) {
		this.currentGUIPlayer = currentGUIPlayer;
	}
	
	

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	/**
	 * Method to begin the rounds
	 * 
	 * This is the main game flow logic engine.  This makes all the major calls to other methods
	 * to control how the game works
	 */
	public void playGame() {	
				
		// Deal the cards to the players
		this.dealCards();
			
		// Check if any player has blackjack.  They get paid first
		for (Player currentPlayer : this.playerList) {
			
			// Each player only has one hand at this time.  Set the object
			PlayerHands hand = currentPlayer.getPlayerHands().get(0);
			
			switch (currentPlayer.getType()) {
				case HUMAN:
				case COMPUTER:
					
					// Players just have one hand for now so check the first hand
					if (hand.isBlackJack()) {
						System.out.println("**********************************************************");
						hand.displayPlayerHand();
						System.out.println("Player: " + currentPlayer.getPlayerName() + " has BlackJack! Pays out at 3 to 2!" + 
										   "\nYou Won $" + (hand.getHandBet() * GameEngine.BLACKJACK_PAYOUT));
						this.dealer.payPlayer(currentPlayer, hand);
						System.out.println("**********************************************************");
						hand.setHandOver(true);
					}
					break;
				case DEALER:
					// Check if the Dealer has blackjack
					if (DEALERHAND.isBlackJack()) {
						System.out.println("\n*****************************************\n"); 
						DEALERHAND.displayPlayerHand();
						System.out.println("Dealer has BlackJack! Everyone loses!\n" + 
									   	   "*****************************************\n");
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
					// Create the ListIterator
					ListIterator<PlayerHands> hands = currentPlayer.getPlayerHands().listIterator();
					// Loop through each hand the player has
					while (hands.hasNext()) {
						
						// Set the hand object
						PlayerHands hand = hands.next();
						
						// Keep asking what the player wants to do until the hand is over
						while (!hand.isHandOver()) {
							
							// Display dealer's Up card
							this.dealer.displayDealersUpCard(DEALERPLAYER);
							System.out.println();
			
							hand.displayPlayerHand();
			
							// Check if they have busted
							if (this.checkIfBusted(hand)) {
									System.out.println("\nOH NO " + currentPlayer.getPlayerName() + "! You've busted!\nYour Total is: " + hand.getHandTotal());
									hand.setHandOver(true);
									continue;
							} 
							
							// Display Hand Total
							System.out.println("\n" + currentPlayer.getPlayerName() + "'s Total is: " + hand.getHandTotal());
			
							// Ask the player what they want to do.  If a computer player or dealer it will auto decide
							this.getPlayOption(currentPlayer, hand, hands);
							}
						
						/*
						 *  Check for a hand being added via a split.  Since ListIterator.add inserts the element into the 
						 *  spot before the object that is called from next() we need to move backwards one to get to that new
						 *  element.  This sets the hand object to the previous hand in the List.  If that hand has the handOver
						 *  flag set to true, we know that no new hands were added and we should just break the loop. Else, that
						 *  hand is a new hand that needs to be processed and we should continue the loop.  
						 */
						hand = hands.previous();
						if (hand.isHandOver()) {
							break;
						}
					}
					break;

				case DEALER:
					while (!DEALERHAND.isHandOver()) {
						
						// Display all of the Dealer's cards
						DEALERHAND.displayPlayerHand();
						
						// Check if they have busted
						if (this.checkIfBusted(DEALERHAND)) {
							System.out.println("The Dealer Busted!!!\nDealer's Total is: " + DEALERHAND.getHandTotal());
							DEALERHAND.setHandOver(true);
							continue;
						} 
						
						// Display hand total
						System.out.println("Dealer's Total is: " + DEALERHAND.getHandTotal());
					
						// Ask the player what they want to do.  If a computer player or dealer it will auto decide
						this.getPlayOption(currentPlayer, DEALERHAND, null);
					}
					break;
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
	public void dealCards() {
		// Message to give indication of what's happening
		System.out.println("\nDealing Cards...");
		
		// Variable to track how many cards have been dealt
		int cardsPerPlayer = 0;
			
		// Keep going until every has 2 cards
		while (cardsPerPlayer < 2) {
			
			// Loop through the player list, starting with the human player (element 1) and deal a card
			for (Player currentPlayer : this.playerList) {
				
				// Get the top cards from the deck and add the card to the player's hand
				currentPlayer.getPlayerHands().get(0).addCard(this.deckShoe.dealCard());
				
				// the second card has just been dealt.  Update the players hand total
				if (cardsPerPlayer > 0) {
					getSumOfCards(currentPlayer.getPlayerHands().get(0));
				}
			}
			cardsPerPlayer++;
		}
		
		System.out.println("Cards Delt\n");
		cardsDelt = true;
	}
	
	/**
	 * Method for any player or the dealer to obtain a new card after being dealt their initial hand
	 * 
	 * @return another Card
	 */
	public Card hit() {
		return this.deckShoe.dealCard();
	}
	
	/**
	 * Method to allow a player to double down.  A player is only allowed a single card
	 * in exchange for being able to double their bet.
	 * 
	 * @param player
	 */
	public void doubleDown(Player currentPlayer, PlayerHands hand) {
		/*
		 * Remove the money from the players total first.  Doubling down is the original bet * 2
		 * the original bet is already removed from the users total so just remove it again.
		 */
		this.dealer.takeBet(currentPlayer, hand);
		
		// Update the bet amount to reflect the new total
		hand.setHandBet(hand.getHandBet() * 2);
		
		// Give the user one more card
		hand.addCard(this.hit());													// Add a new card to players Hand
		this.getSumOfCards(hand);													// Update the players hand total	
		
		// Display the updated cards
		hand.displayPlayerHand();
		
		// Check if they have busted
		if (this.checkIfBusted(hand)) {
			System.out.println("\nOH NO " + currentPlayer.getPlayerName() + "! You've busted!\nYour Total is: " + hand.getHandTotal());
		} else {
			System.out.println("\n" + currentPlayer.getPlayerName() + "'s Total is: " + hand.getHandTotal());
		}
		
		// Turn is over
		hand.setHandOver(true);		
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
			// Loop through each hand and determine if they win
			for (PlayerHands hand : currentPlayer.getPlayerHands()) {
				this.checkForWinners(currentPlayer, hand);
			}
			
			// Reset the players for the next round
			currentPlayer.resetPlayer();
		}
		
		// Start a new round
		//this.startRound();
	}
	
	/**
	 * Method to determine if a player won the hand
	 * 
	 * @param player Card list and dealer Card list
	 * @return true if winner
	 */
	public void checkForWinners(Player currentPlayer, PlayerHands hand) {
		switch (currentPlayer.getType()) {
			case HUMAN:
			case COMPUTER:
				/*
				 *  exclude them if they got blackjack  We will pay out blackjack another way.  
				 *  This check only excludes paying out a BlackJack hand if it is their only hand.
				 *  A player is not allowed to be paid out blackjack on a split hand but they could
				 *  still have a hand that would meet the isBlackJack() criteria
				 */
					if (hand.isBlackJack() && currentPlayer.getPlayerHands().size() == 1) {
						System.out.println(currentPlayer.getPlayerName() + " Had blackjack and has already been paid");
						return;
					}
		
					// Dealer Busted, everyone wins that didn't bust
					else if (hand.getHandTotal() <= MAXTOTAL && DEALERHAND.getHandTotal() > MAXTOTAL) {
						// Pay the player
						this.dealer.payPlayer(currentPlayer, hand);
						System.out.println(currentPlayer.getPlayerName() + " WON! You got $" + hand.getHandBet() + 
										   "\nYour total money is $" + currentPlayer.getPlayerMoney());
					}		
		
					// Player beat Dealer 
					else if (hand.getHandTotal() <= MAXTOTAL && hand.getHandTotal() > DEALERHAND.getHandTotal()) {
						// Pay the player
						this.dealer.payPlayer(currentPlayer, hand);
						System.out.println(currentPlayer.getPlayerName() + " WON! You got $" + hand.getHandBet() + 
									       "\nYour total money is $" + currentPlayer.getPlayerMoney());
					} 
		
					// Player pushed
					else if(hand.getHandTotal() <= MAXTOTAL && hand.getHandTotal() == DEALERHAND.getHandTotal()) {
						this.dealer.payPlayer(currentPlayer, hand, true);
						System.out.println(currentPlayer.getPlayerName() + " Pushed!" + "\nYour total money is $" + currentPlayer.getPlayerMoney());
					}
		
					// Player Lost.  Don't need to collect the money.  We already removed it from their total during the bet stage.
					else {
						System.out.println(currentPlayer.getPlayerName() + " Lost! You lost $" + hand.getHandBet() + 
										   "\nYour total money is $" + currentPlayer.getPlayerMoney());
					}
					break;
				
			case DEALER:
				// Dealer can't get paid, just break the loop
				break;
		}
	}
	
	/**
	 * Method to get the total of the cards in the players hand
	 * 
	 * @param player
	 * @return sum of the cards in the players hand
	 */
	public void getSumOfCards(PlayerHands hand) {
		
		int sumOfCards = 0;									// Accumulator to add sum
		boolean hasAce = false;								// Flag that the player has Aces in their hand
		int numberOfAces = 0;								// Accumulator to track how many aces they have in their hand
		
		// Loop through the players cards in this hand
		for (Card playerCard : hand.getPlayerHand()) {
			
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
		
		hand.setHandTotal(sumOfCards);
	}
	
	/**
	 * Method to check if the player has busted.
	 * 
	 * @param player
	 * @return true if their hand is over MAXTOTAL
	 */
	public boolean checkIfBusted(PlayerHands hand) {
		if (hand.getHandTotal() > MAXTOTAL) {
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
	private void getPlayOption(Player currentPlayer, PlayerHands hand, ListIterator<PlayerHands> hands) {
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
					
					// Check if the player has enough funds to double down or split.  Both require twice their original bet
					if (option == 3 || option == 4 && !currentPlayer.checkFunds(hand.getHandBet() * 2)) {
						System.out.println("Sorry, You do not have enough money to do this.");
						// Reset option to zero so we stay in the loop
						option = 0;	
					}

					// Check if the player can split
					if (option == 4 && !hand.canSplit()) {
						System.out.println("Sorry, You cannot split this hand.  Cards must be a pair.");
						// Reset option to zero so we stay in the loop
						option = 0;												
					}
					
				} while (option < 1 || option > 4);
				
				// Input validated.  Exit loop
				break;
			case COMPUTER:
				option = this.computerDecision(currentPlayer, hand);
				break;
			case DEALER:
				option = this.dealerDecision();
				break;
			default:
				System.out.println("Something went wrong.  I'm trying to get what actions a player should take in the playGame method");
				System.exit(0);
		}

		// Perform actions player wanted
		this.processPlayOption(option, currentPlayer, hand, hands);;
	}
	
	/**
	 * Method to process the option the player chose to do
	 * 
	 * @param option
	 * @param player
	 */
	private void processPlayOption(int option, Player currentPlayer, PlayerHands hand, ListIterator<PlayerHands> hands) {
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
				hand.addCard(this.hit());													// Add a new card to players Hand
				this.getSumOfCards(hand);													// Update the players hand total		
				break;
			case STAND:
				hand.setHandOver(true);
				break;
			case DOUBLE_DOWN:
				this.doubleDown(currentPlayer, hand);
				break;
			case SPLIT:
				// Take the additional bet from the user
				this.dealer.takeBet(currentPlayer, hand);
				
				// Split the hands
				hand.splitHands(currentPlayer, hand, hands);
								
				// Set the new total for each hand
				for (PlayerHands playerHand : currentPlayer.getPlayerHands()) {
					getSumOfCards(playerHand);
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
	public int computerDecision(Player currentPlayer, PlayerHands hand) {
			
		// Get the dealer's upCard
		Card dealerUpCard = dealer.getDealersUpCard(DEALERPLAYER);
				
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (Card computerCard : hand.getPlayerHand()) {
			if (computerCard.getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		/* 
		 * Determine if the Computer should split.  canSplit() already checks that the cards
		 * have the same value, so we only need to validate the value of one card in the if
		 * statements below.
		 */
		if (hand.canSplit()) {
			// Always split aces and 8's
			if (numberOfAces == 2 || hand.getPlayerHand().get(0).getRank() == Rank.EIGHT) {
					return PlayOption.SPLIT.getPlayOptionValue();
			} 
		
			// Split 2, 3's and 7's if dealer UP card is a 7 or lower
			else if (dealerUpCard.getCardRankValue() <= 7 && 
					hand.getPlayerHand().get(0).getRank() == Rank.TWO ||
					hand.getPlayerHand().get(0).getRank() == Rank.THREE ||
					hand.getPlayerHand().get(0).getRank() == Rank.EIGHT) {
						return PlayOption.SPLIT.getPlayOptionValue();
			}
			
			// Split 6's if the Dealer up card is 6 or less
			else if (dealerUpCard.getCardRankValue() <= 6 && 
					hand.getPlayerHand().get(0).getRank() == Rank.SIX) {
						return PlayOption.SPLIT.getPlayOptionValue();
			}
			
			// Split 9's if the Dealer Up card is 6 or worse, 8 or 9
			else if (dealerUpCard.getCardRankValue() <= 6 ||
					dealerUpCard.getCardRankValue() == 8 ||
					dealerUpCard.getCardRankValue() == 9 &&
					hand.getPlayerHand().get(0).getRank() == Rank.NINE) {
						return PlayOption.SPLIT.getPlayOptionValue();
			}
		}		
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		else if (numberOfAces > 0) {
			
			// If Computer has 19 or better, Stand
			if (hand.getHandTotal() >= 19) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// If Computer has 17 and Dealer is showing 9 or better, hit, else Stand
			else if (dealerUpCard.getCardRankValue() <= Rank.EIGHT.getRankValue() && hand.getHandTotal() == 17) {
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
			if (hand.getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Stand on any total >= 13 if the dealer is showing a six or below
			else if (hand.getHandTotal() >= 13 && dealerUpCard.getCardRankValue() <= Rank.SIX.getRankValue()) {
				return PlayOption.STAND.getPlayOptionValue();
			}
			
			// Hit on any total >= 12 and < 17 if the dealer is showing a 7 or above
			else if (hand.getHandTotal() >= 12 && hand.getHandTotal() < 17 &&
					 dealerUpCard.getCardRankValue() > Rank.SIX.getRankValue()) {
				return PlayOption.HIT.getPlayOptionValue();
			}
			
			// if hand total == 12 and dealer is showing a 4, 5, or 6 then stand
			else if (hand.getHandTotal() == 12 && dealerUpCard.getCardRankValue() >= Rank.FOUR.getRankValue() &&
					 dealerUpCard.getCardRankValue() <= Rank.SIX.getRankValue()) {
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
	public int dealerDecision() {
		// Determine if computer has any aces
		int numberOfAces = 0;
		for (Card dealerCard : DEALERHAND.getPlayerHand()) {
			if (dealerCard.getRank() == Rank.ACE) {
				numberOfAces++;
			}
		}
		
		/*
		 *  Determine Soft total option.  A soft total means there is an ace in the hand.  This only matters when 
		 */
		if (numberOfAces > 0) {
			
			// If Dealer has 18 or better, Stand
			if (DEALERHAND.getHandTotal() >= 18) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		}
		
		/*
		 * Determine Hard total options.  No Ace in hand
		 */
		else if (numberOfAces == 0) {
			
			// Stand on everything greater than or equal to 17
			if (DEALERHAND.getHandTotal() >= 17) {
				return PlayOption.STAND.getPlayOptionValue();
			}
		} 
			
		// Hit anything else
		return PlayOption.HIT.getPlayOptionValue();
	}

	public void addDealerToList() {
		playerList.add(dealer);
		
	}
}
