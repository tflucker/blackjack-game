package com.tim.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

	private static List<Card> cards = new ArrayList<>();

	public List<Card> getCards() {
		if (cards.isEmpty()) {
			cards = createCardDeck();
			shuffle();
		}
		return cards;
	}

	/**
	 * Create a 52 card deck. 2-10, jack, queen, king, and ace for all suites
	 * (clubs, spades, hearts, diamonds)
	 * 
	 * @return
	 */
	private List<Card> createCardDeck() {

		for (PlayingCardSuit suit : PlayingCardSuit.values()) {
			// number cards
			cards.add(new Card(suit, "2", 2));
			cards.add(new Card(suit, "3", 3));
			cards.add(new Card(suit, "4", 4));
			cards.add(new Card(suit, "5", 5));
			cards.add(new Card(suit, "6", 6));
			cards.add(new Card(suit, "7", 7));
			cards.add(new Card(suit, "8", 8));
			cards.add(new Card(suit, "9", 9));
			cards.add(new Card(suit, "10", 10));

			// face cards (jack, queen, king)
			cards.add(new Card(suit, "Jack", 10));
			cards.add(new Card(suit, "Queen", 10));
			cards.add(new Card(suit, "King", 10));

			// ace (value 1 or 11)
			cards.add(new Card(suit, "Ace", 11));
		}

		return cards;
	}

	public void setCards(List<Card> cards) {
		CardDeck.cards = cards;
	}

	// rearranges the order of all the cards
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Draw a card for the dealer or player. Card is removed from the top and placed
	 * at the bottom of the deck.
	 * 
	 * @return
	 */
	public Card drawCard() {
		Card card = cards.get(0);

		// remove from top so card is not accidentally drawn again
		// add card to the bottom of the deck in case user keeps playing
		cards.remove(0);
		cards.add(card);

		return card;
	}

}
