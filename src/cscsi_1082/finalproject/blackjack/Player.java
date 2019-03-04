package cscsi_1082.finalproject.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	public enum playerType {
		HUMAN,
		COMPUTER;
	}
	
	// Instance Variables
	private String playerName;
	private List<Card> playerCards = new ArrayList<Card>();		// List to hold cards in players hand
	private int playerMoney;
	private playerType type;
	private int playerBet;
		
	// Getters and setters
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Card getPlayerCards(int index) {
		return this.playerCards.get(index);
	}
	public void setPlayerCards(Card card) {
		this.playerCards.add(card);
	}
	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
		
	public playerType getType() {
		return type;
	}
	public void setType(playerType type) {
		this.type = type;
	}
	public int getPlayerBet() {
		return playerBet;
	}
	public void setPlayerBet(int playerBet) {
		this.playerBet = playerBet;
	}
	// Constructor
	public Player(String playerName, playerType type) {
		this.playerName = playerName;
		this.type = type;
		this.playerCards.removeAll(playerCards);
		this.playerMoney = 500;		
	}
	
	
	
	
}
