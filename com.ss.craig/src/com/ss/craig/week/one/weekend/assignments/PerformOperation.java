/**
 * Weekend Assignment 1: Lambdas
 */
package com.ss.craig.week.one.weekend.assignments;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Craig Saunders
 *
 */

public interface PerformOperation {

    default List<String> noX(List<String> list)
    {
        return noX.apply(list);
    }
    final Function<List<String>, List<String>> noX = l -> l.stream().map(s -> s.replace("x", "")).collect(Collectors.toList());
    
    default List<Integer> rightDigit(List<Integer> list)
    {        
        return rightDigit.apply(list);        
    }
    final Function<List<Integer>, List<Integer>> rightDigit = l -> l.stream().map(i -> i % 10).collect(Collectors.toList());

    default List<Integer> doubling(List<Integer> list)
    {
        return doubling.apply(list);
    }
    
    final Function<List<Integer>, List<Integer>> doubling = l -> l.stream().map(i -> i * 2).collect(Collectors.toList());
    
    default String isOdd(Integer num)
    {
        Function<Integer, String> isOdd = n -> n % 2 == 0 ? "EVEN" : "ODD";
        return isOdd.apply(num);
    }

    default String isPrime(Integer num)
    {
        Function<Integer, String> isPrime = n -> {
            if (n <= 1)
            {
                return "COMPOSITE";
            }
            for (int i = 2; i < n; i++)
            {
                if (n % i == 0)
                {
                    return "COMPOSITE";
                }
            }
            return "PRIME";
        };
        return isPrime.apply(num);
    }

    default String isPalindrome(Integer num)
    {
        Function<Integer, String> isPalindrome = n -> {
            int remainder = 0;
            int sum = 0;
            int temp = n;
            while (n > 0)
            {
                remainder = n % 10;
                sum = (sum * 10) + remainder;
                n = n / 10;
            }
            return sum == temp ? "PALINDROME" : "NOT PALINDROME";
        };
        return isPalindrome.apply(num);
    }
}