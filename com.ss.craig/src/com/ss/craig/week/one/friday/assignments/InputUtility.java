/**
 * Input Utility designed to remove a lot of hassle every time I want to get user input
 */
package com.ss.craig.week.one.friday.assignments;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Craig Saunders
 *
 */
public abstract class InputUtility {
    public static Scanner scnr = new Scanner(System.in);

    /**
     * @param options : String list of options shown to user noted by number
     * @return Returns the manual selection of the user's choice as an integer
     */
    public static Integer getUserSelectionInteger(List<String> options)
    {
        int selection = 0;
        options.stream().forEach(str -> System.out.println(str));
        if (options.size() > 0 && scnr.hasNext())
        {
            if (scnr.hasNextInt())
            {
                selection = scnr.nextInt();
            }
            else
            {
                System.out.println(scnr.next() + " is not the integer response expected.");
            }
        }
        scnr.reset();
        return selection;
    }

    /**
     * @param options : String array of options shown to user noted by number
     * @return Returns the manual selection of the user's choice as an integer
     */
    public static Integer getUserSelectionInteger(String[] options)
    {
        return getUserSelectionInteger(Arrays.asList(options));
    }
}
