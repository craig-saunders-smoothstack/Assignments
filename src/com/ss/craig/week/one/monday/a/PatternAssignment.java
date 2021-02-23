/**
 * Day one, assignment one: Print the pattern
 */
package com.ss.craig.week.one.monday.a;

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
			System.out.println("1)");
			printLine(0,1,'*');
			printLine(0,2,'*');
			printLine(0,3,'*');
			printLine(0,4,'*');
			printLine(0,9,'.');
			printLine(0,0,' ');
			System.out.println("2)");
			printLine(0,10,'.');
			printLine(0,4,'*');
			printLine(0,3,'*');
			printLine(0,2,'*');
			printLine(0,1,'*');
			printLine(0,0,' ');
			System.out.println("3)");
			printLine(5,6,'*');
			printLine(4,7,'*');
			printLine(3,8,'*');
			printLine(2,9,'*');
			printLine(0,11,'.');
			printLine(0,0,' ');
			System.out.println("4)");
			printLine(0,12,'.');
			printLine(2,9,'*');
			printLine(3,8,'*');
			printLine(4,7,'*');
			printLine(5,6,'*');
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
