/**
 * Day one, assignment one: Print the pattern
 */
package com.ss.craig.week.one.monday.a;

/**
 * @author Craig Saunders
 *
 */
public class Main {
	private static int num_loops = 4;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		num_loops = 4;
		try {
			num_loops = Integer.parseInt(args[0]);
		}
		catch (Exception e) {
			num_loops = 4;
		}
		if (num_loops < 0) {
			num_loops = 0;
		}
		new PatternAssignment(num_loops);
	}
}
