/**
 * Blackjack class is the starting point for the Blackjack game.
 * 
 * This class is responsible for getting players and initializing an
 * instance of the game engine.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package csci_1082.finalproject.blackjack;
import csci_1082.finalproject.blackjackGUI.*;

public class Blackjack {
	
	/**
	 * Constructor
	 */
	public Blackjack () {
		BlackjackGUI newGame = new BlackjackGUI(); 
		//GameEngine.newTable();
	}

	
	/**
	 * Main method.  This starts and instance of the BlackJack class
	 * @param args
	 */
	public static void main(String[] args) {
		Blackjack gameOfBlackjack = new Blackjack();
		}
	}
