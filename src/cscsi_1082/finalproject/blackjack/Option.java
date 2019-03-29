package cscsi_1082.finalproject.blackjack;

public enum Option {
	HIT				(1),
	STAND			(2),
	DOUBLE_DOWN		(3),
	SPLIT			(4),
	QUIT			(9);
	
	private final int optionValue;
	
	Option(int optionValue) {
		this.optionValue = optionValue;
	}

	public final int getOptionValue() {
		return optionValue;
	}
}
