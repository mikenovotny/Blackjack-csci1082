/**
 * Option Enum.  This enum give friendly names to what options a player chose.
 * 
 * @author Mike Novotny
 * @author Ryan Westling
 * @version 1.0
 */

package cscsi_1082.finalproject.blackjack; 

public enum StartingOption {
		BET				(1),
		QUIT			(2);

		private final int startingOptionValue;				// Instance variable that stores the integer value
		
		/**
		 * Constructor for the enum
		 * 
		 * @param optionValue
		 */
		StartingOption(int optionValue) {
			this.startingOptionValue = optionValue;
		}
		
		/**
		 * Method to get the integer value associated with the enum
		 * 
		 * @return integer value
		 */
		public final int getStartingOptionValue() {
			return startingOptionValue;
		}
	}
