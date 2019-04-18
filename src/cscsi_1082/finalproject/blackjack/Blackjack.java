/**
 * Blackjack class is the starting point for the Blackjack game.
 * 
 * This class is responsible for getting players and initializing an
 * instance of the game engine.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

public class Blackjack {
	
	/**
	 * Constructor
	 */
	public Blackjack () {
		GameEngine.newTable();
	}
	
	
	/**
	 * Main method.  This starts and instance of the BlackJack class
	 * @param args
	 */
	public static void main(String[] args) {
		Blackjack gameOfBlackjack = new Blackjack();
		}
	}
