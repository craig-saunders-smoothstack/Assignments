/**
 * Random number guessing assignment
 */
package com.ss.craig.week.one.monday.b;

import java.util.Scanner;

/**
 * @author Craig Saunders
 * Creates a random number and runs the guessing loop
 */
public class RandomNumberGuess {

	private int random_number = 0;
	
	/**
	 * Creates the random number and runs the loop
	 */
	public RandomNumberGuess() {
		random_number = (int)(Math.random() * (100-1) + 1);
		run();
	}
	
	/**
	 * Runs the guessing loop for the user
	 */
	private void run()
	{
		boolean correct_guess = false;
		Scanner sc= new Scanner(System.in); 
		for (int i = 0; i < 5; i++)
		{
			if (i == 0) {
				System.out.println("Could you please guess a number between 1 and 100?");
			}
			else {
				System.out.println("Wrong number, please try again?");
			}
			int input = 0;
			// this try catch wasn't in the directions of the assignment, but still necessary
			try {
				input = sc.nextInt();
			} catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				sc.close();  // don't forget to close the input scanner on exception
				return;
			}
			if (input >= random_number - 10 && input <= random_number + 10) {
				correct_guess = true;
				break;
			}
		}
		sc.close();
		if (correct_guess) {
			System.out.println("Your guess was within 10 numbers of: "+Integer.toString(random_number));
		}
		else {
			System.out.println("Sorry, your guess was not within 10 numbers of: "+Integer.toString(random_number));
		}
	}
}
