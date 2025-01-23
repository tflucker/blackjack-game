package com.tim.blackjack;

public class Card {

	// Suite of card (clubs, spades, hearts, diamonds)
	private PlayingCardSuit suit;

	// Value displayed on card (2-10, jack, queen, kind, ace)
	private String shownValue;

	// value of the value displayed on card
	private int value;

	public PlayingCardSuit getSuit() {
		return suit;
	}

	public void setSuit(PlayingCardSuit suite) {
		this.suit = suite;
	}

	public String getShownValue() {
		return shownValue;
	}

	public void setShownValue(String shownValue) {
		this.shownValue = shownValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Card(PlayingCardSuit suit, String shownValue, int value) {
		super();
		this.suit = suit;
		this.shownValue = shownValue;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Card [suit=" + suit + ", shownValue=" + shownValue + ", value=" + value + "]";
	}

}
