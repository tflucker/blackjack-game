package com.tim.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Container object for a game of blackjack.
 * 
 * create and shuffle deck of cards
 * 
 * deal hands to player and dealer
 * 
 * player's turn
 * 
 * dealers turn
 * 
 * determine outcome
 */
public class Game {

	List<Card> playerHand = new ArrayList<>();

	List<Card> dealerHand = new ArrayList<>();

	CardDeck deck = new CardDeck();

	public Game(Scanner in) {
		// create the deck of cards and shuffle!
		deck.getCards();

		// player hand
		playerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());

		// dealer hand
		dealerHand.add(deck.drawCard());
		dealerHand.add(deck.drawCard());

		printPlayerHand();
		printDealerHand("Start");

//		GameOutcome playerOutcome = playerTurn(in);
//		if(playerOutcome.equals(GameOutcome.KEEP_PLAYING)) {
//			GameOutcome dealerOutcome = dealerTurn();
//		}
		

	}

	// logic used to simulate a Blackjack dealer's behavior
	private GameOutcome dealerTurn() {
		boolean playing = true;
		GameOutcome result = null;
		while (playing) { 
		
			int dealerTotalValue = 0;
			int softValue = 0;
			for(Card card : dealerHand) {
				dealerTotalValue += card.getValue();
				softValue += card.getValue();
				// if ace found, de-increment soft value by 10 to account for ace's 1 or 11
				// value
				if (card.getShownValue().equals("Ace")) {
					softValue -= 10;
				}
			}
		
		}
		return null;
	}

	private GameOutcome playerTurn(Scanner in) {
		boolean playing = true;
		GameOutcome result = null;
		while (playing) {
			System.out.println("Choose your action: Hit / Stay");
			String userInput = in.nextLine();

			if (userInput.equalsIgnoreCase("Hit")) {
				playerHand.add(deck.drawCard());
				printPlayerHand();
				result = determineOutcome("Player", playerHand);
			} else if (userInput.equalsIgnoreCase("Stay")) {
				playing = false;
			} else {
				System.out.println("Unrecognized input, not action taken.  Please try again!");
			}
		}
		
		if(result == null) {
			result = determineOutcome("Player", playerHand);
		}
		
		return result;
	}

	private GameOutcome determineOutcome(String participant, List<Card> hand) {
		
		GameOutcome result = null;
		
		int playerTotalValue = 0;
		int softValue = 0;
		boolean hasAce = hasAce(hand);

		// calculate playerHand
		for (Card card : hand) {
			playerTotalValue += card.getValue();
			softValue += card.getValue();
			// if ace found, de-increment soft value by 10 to account for ace's 1 or 11
			// value
			if (card.getShownValue().equals("Ace")) {
				softValue -= 10;
			}
		}

		// if player has ace and total value with ace (11) is greater than 21 and the
		// soft value (1) is less than 21, then ignore the high value and use the soft
		// value.
		if (softValue != 0 && (playerTotalValue > 21 && softValue < 21)) {
			playerTotalValue = softValue;
		}

		
		//calculate dealerHand
		
		int dealerTotalValue = 0;
		softValue = 0;
		for(Card card : dealerHand) {
			dealerTotalValue += card.getValue();
			softValue += card.getValue();
			// if ace found, de-increment soft value by 10 to account for ace's 1 or 11
			// value
			if (card.getShownValue().equals("Ace")) {
				softValue -= 10;
			}
		}
		
		// if dealer has ace and total value with ace (11) is greater than 21 and the
		// soft value (1) is less than 21, then ignore the high value and use the soft
		// value.
		if (softValue != 0 && (dealerTotalValue > 21 && softValue < 21)) {
			dealerTotalValue = softValue;
		}
		
		// determine outcome
		if (playerTotalValue > 21) {
			System.out.println("Player Bust! Dealer wins!");
			result = GameOutcome.DEALER_WIN;
		} else if(dealerTotalValue > 21) {
			System.out.println("Dealer Bust! Player wins!");
			result = GameOutcome.PLAYER_WIN;
		} else if(playerTotalValue == dealerTotalValue) {
			System.out.println("Draw! No winner...");
			result = GameOutcome.DRAW;
		} else {
			result = GameOutcome.KEEP_PLAYING;
		}
		
		return result;

	}

	private void printPlayerHand() {
		String str = "";
		int totalValue = 0;

		for (Card card : playerHand) {
			str += "[" + card.getShownValue() + "]";
			totalValue += card.getValue();
		}
		System.out.println("Player's cards: " + str);
		System.out.println("Player's total value: " + totalValue);
		System.out.println("\n-----------\n");

	}

	private void printDealerHand(String stage) {
		String str = "";
		int totalValue = 0;
		if (stage.equals("Start")) {
			str = "[" + dealerHand.get(0).getShownValue() + "][X]";
			System.out.println("Dealer's cards: " + str);
			System.out.println("Dealer's value: " + dealerHand.get(0).getValue());
		} else {

			for (Card card : dealerHand) {
				str += "[" + card.getShownValue() + "]";
				totalValue += card.getValue();
			}
			System.out.println("Dealer's cards: " + str);
			System.out.println("Dealer's total value: " + totalValue);
		}
		System.out.println("\n-----------\n");
	}

	private boolean hasAce(List<Card> cards) {
		return cards.stream().anyMatch(c -> c.getShownValue().equals("Ace"));

	}

}
