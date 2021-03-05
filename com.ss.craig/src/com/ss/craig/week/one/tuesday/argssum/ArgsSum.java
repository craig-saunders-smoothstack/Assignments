/**
 * Tuesday Assignment: Sum and display the args from main
 */
package com.ss.craig.week.one.tuesday.argssum;

import java.util.Scanner;

/**
 * @author Craig Saunders
 *
 */
public class ArgsSum {

    /**
     * Sums all double casted args with number values and displays the sum
     * 
     * @param args : array of numbers as strings to add together and display
     */
    public static void main(String[] args)
    {
        Double sum = 0d; // using double as it holds the largest negative and positive decimal numbers
        Scanner sc;
        for (String arg : args)
        {
            sc = new Scanner(arg);
            try
            {
                if (sc.hasNextDouble())
                {
                    sum += sc.nextDouble();
                }
            }
            catch (Exception e)
            {
            }
            finally
            {
                sc.close();
            }
        }
        System.out.println(Double.toString(sum));
    }

}
