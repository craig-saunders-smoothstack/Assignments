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

	private int last_count = 0;
	/**
	 * @param loops
	 * Constructor prints out the assignment
	 */
	public PatternAssignment(int loops)
	{
		// counting the periods manually is important here as each are different per pattern
		for (int i = 1; i <= loops; i++)
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
	 * @param pattern
	 * Prints the specified stars pattern
	 */
	private void printStars(int count)
	{
		int start = ((count-1)/2*5);
		start = start < 0 ? 0 : start;
		if (count % 2 == 0) {
			start = last_count;
		}
			
		List<Integer> backward = Arrays.asList(0,0,0,0); // start with zero list 
		if (count > 2) { // first backward list counts down from 0
			backward = Arrays.asList(start, start-1, start-2, start-3);
		}
		List<Integer> forward = Arrays.asList(start+1, start+2, start+3, start+4);
		if (count % 2 == 0) {
			Collections.reverse(backward);
			Collections.reverse(forward);
		}
		for(int i = 0; i < backward.size(); i++) {				
			printLine(backward.get(i),forward.get(i),'*');
		}
		if (count != 1 && count % 2 != 0) {
			last_count = start;
		}
	}
	
	/**
	 * @param start
	 * @param length
	 * @param character
	 * Builds then prints a line with specified character
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
