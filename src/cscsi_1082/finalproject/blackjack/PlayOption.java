/**
 * Option Enum.  This enum give friendly names to what options a player chose.
 * 
 * @author Mike Novotny
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack; 

public enum PlayOption {
	HIT				(1),
	STAND			(2),
	DOUBLE_DOWN		(3),
	SPLIT			(4);
	
	private final int playOptionValue;				// Instance variable that stores the integer value
	
	/**
	 * Constructor for the enum
	 * 
	 * @param optionValue
	 */
	PlayOption(int optionValue) {
		this.playOptionValue = optionValue;
	}
	
	/**
	 * Method to get the integer value associated with the enum
	 * 
	 * @return integer value
	 */
	public final int getPlayOptionValue() {
		return playOptionValue;
	}
}
