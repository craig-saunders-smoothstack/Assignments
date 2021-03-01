/**
 * Weekend Assignment 1: Lambdas
 */
package com.ss.craig.week.one.weekend.assignment.one;

import java.util.function.Function;

/**
 * @author Craig Saunders
 *
 */
public abstract class PerformOperation {    
    public static Function<Integer, String> isOdd = (num) -> {
        if(num % 2 == 0)
        {
            return "EVEN";
        }
        else
        {
            return "ODD";
        }
    };
    public static Function<Integer, String> isPrime = (num) -> {
        if (num <= 1)
        {
            return "COMPOSITE";
        }
        for (int i = 2; i < num; i++)
        {
            if (num % i == 0)
            {
                return "COMPOSITE";
            }
        }
        return "PRIME";
    };
    public static Function<Integer, String> isPalindrome = (num) -> {
        int remainder = 0;
        int sum = 0;
        int temp = num;
        while (num > 0)
        {
            remainder = num % 10;
            sum = (sum * 10) + remainder;
            num = num / 10;
        }
        return sum == temp ? "PALINDROME" : "NOT PALINDROME";
    };
}