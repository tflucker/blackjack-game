package com.tim.blackjack;

import java.util.Scanner;

public class BlackjackApplication {

	public static void main(String[] args) {

		System.out.println("--------------------------------------\nWelcome to Tim's Blackjack game!\n--------------------------------------\n");
		Scanner scanner = new Scanner(System.in);
		boolean playing = true;
		while(playing) {
			new Game(scanner);
			
			System.out.println("\nContinue Playing?(Yes/No)");
			String userInput = scanner.nextLine();
			
			if(userInput.equalsIgnoreCase("No")) {
				playing = false;
			} else if(!userInput.equalsIgnoreCase("Yes") || !userInput.equalsIgnoreCase("No")) {
				System.out.println("Unrecognized input, closing game.");
				playing = false;
			}
		}		
		scanner.close();
		System.out.println("\n--------------------------------------Thank you for playing!--------------------------------------");
	}

}
