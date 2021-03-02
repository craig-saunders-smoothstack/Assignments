/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignments.PerformOperation;

/**
 * @author Craig Saunders
 *
 */
public class PerformOperationTest implements PerformOperation {
    public List<String> sample_input;
    public List<String> sample_output;
    public List<Integer> doubling_list;
    public List<String> noX_list;
    List<Integer> non_negative_test;

    @Before
    public void setup()
    {
        non_negative_test = Arrays.asList(new Integer[] { 1, 22, 93, 16, 8, 886, 8, 1, 10, 0, 2347 });
        noX_list = Arrays.asList(new String[] { "ax", "bb", "cx", "xxax", "xbxbx", "xxcx", "x" });
        doubling_list = Arrays.asList(new Integer[] { 1, 2, 3, 6, 8, 6, 8, -1 });
        sample_input = Arrays.asList(new String[] { "5", "1 4", "2 5", "3 898", "1 3", "2 12" });
        sample_output = Arrays.asList(new String[] { "EVEN", "PRIME", "PALINDROME", "ODD", "COMPOSITE" });
    }

    @Test
    public void test()
    {
        if (sample_input.size() > 0)
        {
            int test_cases = 0;
            try
            {
                test_cases = Integer.parseInt(sample_input.get(0));
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid number format at: " + sample_input.get(0));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            for (int i = 0; i < test_cases; i++)
            {
                String[] split = sample_input.get(i + 1).split(" ");
                String key = "";
                String value = "";
                if (split.length == 2)
                {
                    try
                    {
                        key = split[0];
                        value = split[1];
                        if (key.equals("1"))
                        {
                            assertEquals(sample_output.get(i), isOdd(Integer.parseInt(value)));
                            System.out.println(isOdd().apply(Integer.parseInt(value)));
                        }
                        else if (key.equals("2"))
                        {
                            assertEquals(sample_output.get(i), isPrime(Integer.parseInt(value)));
                            System.out.println(isPrime().apply(Integer.parseInt(value)));
                        }
                        else if (key.equals("3"))
                        {
                            assertEquals(sample_output.get(i), isPalindrome(Integer.parseInt(value)));
                            System.out.println(isPalindrome().apply(Integer.parseInt(value)));
                        }
                        else
                        {
                            System.out.println("Invalid condition input");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        // Doubling test
        assertEquals(Arrays.asList(new Integer[] { 2, 4, 6, 12, 16, 12, 16, -2 }), doubling(doubling_list));
        assertEquals(0, doubling(new ArrayList<Integer>()).size());
        // NoX test
        assertEquals(Arrays.asList(new String[] { "a", "bb", "c", "a", "bb", "c", "" }), noX(noX_list));
        // RightDigit test
        assertEquals(Arrays.asList(new Integer[] { 1, 2, 3, 6, 8, 6, 8, 1, 0, 0, 7 }),
                rightDigit(non_negative_test));
    }
}
