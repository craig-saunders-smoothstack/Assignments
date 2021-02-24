/**
 * Day one, assignment one: Print the pattern
 */
package com.ss.craig.week.one.monday.pattern;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Craig Saunders Prints the specified patterns in the assignment
 */
public class PatternAssignment {
    /**
     * Constructor prints out the assignment
     * 
     * @param loops : Amount of times to repeat the pattern
     */
    public PatternAssignment(int loops) {
        // counting the periods manually is important here as each are different per
        // pattern
        for (int i = 1; i <= loops; i++) {
            System.out.println(Integer.toString(i) + ")"); // print line number
            if (i % 2 == 0) {
                printLine(0, 9 + i - 1, '.'); // print first if even
            }
            try {
                printStars(i); // print... stars
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            if (i % 2 != 0) {
                printLine(0, 9 + i - 1, '.'); // print last if odd
            }
            printLine(0, 0, ' '); // print empty line
        }
    }

    /**
     * Prints the specified stars pattern
     * 
     * @param count : loop counter
     */
    private void printStars(int count)
            throws NullPointerException, UnsupportedOperationException, IndexOutOfBoundsException {
        int start = ((count - 1) / 2 * 5);
        start = start < 0 ? 0 : start;
        List<Integer> backward;
        if (count > 2) {
            backward = Arrays.asList(start, start - 1, start - 2, start - 3); // backward list counts spaces down from
                                                                              // start
        } else {
            backward = Arrays.asList(0, 0, 0, 0); // start with zero list
        }
        List<Integer> forward = Arrays.asList(start + 1, start + 2, start + 3, start + 4); // forward list counts input
                                                                                           // char after start
        if (count % 2 == 0) { // reverse the pattern for even numbers
            Collections.reverse(backward);
            Collections.reverse(forward);
        }
        for (int i = 0; i < backward.size(); i++) {
            printLine(backward.get(i), forward.get(i), '*');
        }
    }

    /**
     * Builds then prints a line with specified character
     * 
     * @param start     : When the character provided should begin adding to the
     *                  string
     * @param length    : length of the string
     * @param character : provided character to fill for the pattern
     */
    private void printLine(int start, int length, char character) {
        if (length >= 0) {
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                if (i >= start) {
                    sb.append(character);
                } else {
                    sb.append(' ');
                }
            }
            System.out.println(sb.toString());
        }
    }
}
