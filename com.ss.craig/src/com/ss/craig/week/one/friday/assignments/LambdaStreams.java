/**
 * Friday Assignment: Sorting an array via lambdas
 */
package com.ss.craig.week.one.friday.assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Craig Saunders
 *
 */
public class LambdaStreams extends InputUtility {
    private final String[] UNSORTED_ARRAY;

    private final String[] ARRAY_OF_STRINGS;

    private final Integer[] ARRAY_OF_INTEGERS;

    private final String[] OPTIONS;

    /**
     * @return Returns a predefined final list of integers
     */
    public Integer[] getIntegerArray()
    {
        return ARRAY_OF_INTEGERS;
    }

    /**
     * @return Returns a predefined final list of strings
     */
    public String[] getStringArray()
    {
        return ARRAY_OF_STRINGS;
    }

    /**
     * Initialize the final values in the constructor
     */
    public LambdaStreams()
    {
        UNSORTED_ARRAY = new String[] { 
                "Trogdor!", 
                "Trogdor!", "", 
                "Trogdor was a man,",
                "I mean, he was a dragon man,", 
                "Or maybe he was just a dragon.", 
                "But he was still Trogdor!",
                "Trogdor!", "", 
                "Burninating the countryside.", 
                "Burninating the peasants.",
                "Burninating all the peoples.", 
                "And their thatched-roof cottages!", 
                "Thatched-roof cottages!" };
        ARRAY_OF_INTEGERS = new Integer[] { 3, 215, 731, 712, 88354, 14164, 4, 7, 1, 4456, 77, 33, 209, 890, -0, 6,
                -234, 213, 0, -6, -7 };
        List<String> unsorted_words = new ArrayList<String>();
        Arrays.asList(UNSORTED_ARRAY).stream().forEach(s -> {
            Arrays.asList(s.split(" ")).stream().forEach(str -> unsorted_words.add(str));
        });
        ARRAY_OF_STRINGS = unsorted_words.toArray(new String[unsorted_words.size()]);
        OPTIONS = new String[] { "Choose your output:", 
                "0: None - Go back to main menu", 
                "1: Unsorted list",
                "2: Sorted list by length", 
                "3: Sorted list by reverse length", 
                "4: Alphabetically sorted list",
                "5: Sorted list sorting strings starting with 'e' first",
                "6: Sorted list same as 6 but with helper method" };
    }

    /**
     * Default starting character limiter and length set for assignment
     * 
     * @param string_list : The string list to pull from
     * @return Returns the list of strings filtered through
     */
    public List<String> getLimitListFromList(List<String> string_list)
    {
        return getLimitListFromList(string_list, 'a');
    }

    /**
     * Default starting character limiter and length set for assignment
     * 
     * @param string_list : The string list to pull from
     * @return Returns the list of strings filtered through
     */
    public List<String> getLimitListFromList(String[] string_array)
    {
        return getLimitListFromList(Arrays.asList(string_array));
    }

    /**
     * Default length set for assignment
     * 
     * @param string_list : The string list to pull from
     * @param starts_with : The character to compare against
     * @return Returns the list of strings filtered through
     */
    public List<String> getLimitListFromList(List<String> string_list, char starts_with)
    {
        return getLimitListFromList(string_list, starts_with, 3);
    }

    /**
     * Dynamic function capable of allowing more than just the assignment input of
     * string list
     * 
     * @param string_list  : The string list to pull from
     * @param starts_with  : The character to compare against
     * @param length_limit : The length of the string in the list that is allowed
     * @return Returns the list of strings filtered through
     */
    public List<String> getLimitListFromList(List<String> string_list, char starts_with, int length_limit)
    {

        return string_list.stream().filter(p -> p.length() == length_limit && p.charAt(0) == starts_with)
                .collect(Collectors.toList());
    }

    /**
     * @param int_list : Given list of integers
     * @return
     */
    public String getCommaSeparated(List<Integer> int_list)
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            int_list.stream().forEach(i -> sb.append((i % 2 == 0 ? "e" + i : "o" + i) + ","));
            return sb.delete(sb.length() - 1, sb.length()).toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * private singleton constructor
     */
    public void startBasicSorting()
    {
        int selection = 0;
        boolean continue_toggle = true;
        while (continue_toggle)
        {
            selection = getUserSelectionInteger(OPTIONS);
            if (selection > 0 && selection <= OPTIONS.length)
            {
                sortAndPrintArray(selection);
            }
            else
            {
                System.out.println("Back to main...");
                continue_toggle = false;
                break;
            }
        }
    }

    /**
     * Sort using lambdas with the user's selection
     * 
     * @param selection : User's sort selection
     */
    public void sortAndPrintArray(int selection)
    {
        List<String> trogdor_unsorted = Arrays.asList(UNSORTED_ARRAY);
        switch (selection)
        {
        case 2:
            /* Sort by length */
            trogdor_unsorted.stream().sorted(Comparator.comparing(String::length))
                    .forEach(line -> System.out.println(line));
            break;
        case 3:
            /* Sort by length reversed */
            trogdor_unsorted.stream().sorted(Comparator.comparing(String::length).reversed())
                    .forEach(line -> System.out.println(line));
            break;
        case 4:
            /* Sort alphabetically by the first character only */
            trogdor_unsorted.stream().filter(p -> p.toString().length() == 0).forEach(line -> System.out.println(line));
            trogdor_unsorted.stream().filter(p -> p.toString().length() > 0)
                    .sorted(Comparator.comparingInt(str -> ((String) str).charAt(0)))
                    .forEach(line -> System.out.println(line));
            break;
        case 5:
            /*
             * Sort by strings that contain “e” first, everything else second. For now, put
             * the code directly in the lambda.
             */
            trogdor_unsorted.stream().filter(p -> p.contains("e")).sorted().forEach(line -> System.out.println(line));
            trogdor_unsorted.stream().filter(p -> !p.contains("e")).sorted().forEach(line -> System.out.println(line));
            break;
        case 6:
            /* Redo the previous problem, but use a static helper method */
            trogdor_unsorted.stream().filter(getFilterPredicate("e", true)).sorted()
                    .forEach(line -> System.out.println(line));
            trogdor_unsorted.stream().filter(getFilterPredicate("e", false)).sorted()
                    .forEach(line -> System.out.println(line));
            break;
        default:
            trogdor_unsorted.stream().forEach(line -> System.out.println(line));
        }
    }

    /**
     * Static helper function
     * 
     * @param s       : Search string to filter strings containing
     * @param isEqual : Decides to include or exclude via String s parameter
     * @return Returns the filter predicate includive or exculsive to those
     *         containing String s
     */
    public static Predicate<String> getFilterPredicate(String s, boolean isEqual)
    {
        if (isEqual)
        {
            return p -> p.contains(s);
        }
        return p -> !p.contains(s);
    }
}
