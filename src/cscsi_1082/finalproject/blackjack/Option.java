/**
 * Option Enum.  This enum give friendly names to what options a player chose.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack;

public enum Option {
	HIT				(1),
	STAND			(2),
	DOUBLE_DOWN		(3),
	SPLIT			(4),
	QUIT			(5);
	
	private final int optionValue;				// Instance variable that stores the integer value
	
	/**
	 * Constructor for the enum
	 * 
	 * @param optionValue
	 */
	Option(int optionValue) {
		this.optionValue = optionValue;
	}
	
	/**
	 * Method to get the integer value associated with the enum
	 * 
	 * @return integer value
	 */
	public final int getOptionValue() {
		return optionValue;
	}
}
