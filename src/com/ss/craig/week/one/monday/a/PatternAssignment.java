/**
 * Day one, assignment one: Print the pattern
 */
package com.ss.craig.week.one.monday.a;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Craig Saunders
 * Prints the specified patterns in the assignment
 */
public class PatternAssignment {

	/**
	 * Constructor prints out the assignment
	 */
	public PatternAssignment()
	{
		// counting the periods manually is important here as each are different per pattern
		for (int i = 1; i <= 4; i++)
		{
			System.out.println(Integer.toString(i)+")"); // print line number
			if (i % 2 == 0) {
				printLine(0,9+i-1,'.'); // print first if even
			}
			printStars(i); // print... stars
			if (i % 2 != 0) {
				printLine(0,9+i-1,'.'); // print last if odd
			}
			printLine(0,0,' '); // print empty line
		}
	}
	
	/**
	 * Prints the specified stars pattern
	 * @param pattern
	 */
	private void printStars(int pattern)
	{
		List<Integer> one_two_length = Arrays.asList(1,2,3,4);
		List<Integer> three_four_start = Arrays.asList(5,4,3,2);
		List<Integer> three_four_length = Arrays.asList(6,7,8,9);
		if (pattern == 1 || pattern == 2)
		{
			if (pattern == 2) {
				Collections.reverse(one_two_length);
			}
			for(int length: one_two_length) {
				printLine(0,length,'*');
			}
		}
		else if (pattern == 3 || pattern == 4)
		{			
			if (pattern == 4) {
				Collections.reverse(three_four_start);
				Collections.reverse(three_four_length);
			}
			for(int i = 0; i < three_four_start.size(); i++) {				
				printLine(three_four_start.get(i),three_four_length.get(i),'*');
			}			
		}
	}
	
	/**
	 * Builds then prints a line with specified character
	 * @param start
	 * @param length
	 * @param character
	 */
	private void printLine(int start, int length, char character) {
		String line = "";
		for (int i = 0; i < length; i++) {	
			if (i >= start) {
				line += character;
			}
			else {
				line += ' ';
			}
		}	
		System.out.println(line);
	}
}
