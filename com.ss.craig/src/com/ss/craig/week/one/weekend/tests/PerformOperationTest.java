/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignment.GroupedInterface;

/**
 * @author Craig Saunders
 *
 */
public class PerformOperationTest implements GroupedInterface {
    public List<String> sample_input;
    public List<String> sample_output;

    @Before
    public void setup()
    {
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
    }
}
