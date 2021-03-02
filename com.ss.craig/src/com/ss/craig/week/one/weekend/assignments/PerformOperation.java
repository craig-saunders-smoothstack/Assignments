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
        return noX().apply(list);
    }

    default List<Integer> rightDigit(List<Integer> list)
    {
        return rightDigit().apply(list);
    }

    default List<Integer> doubling(List<Integer> list)
    {
        return doubling().apply(list);
    }

    default String isOdd(Integer num)
    {
        return isOdd().apply(num);
    }

    default String isPrime(Integer num)
    {
        return isPrime().apply(num);
    }

    default String isPalindrome(Integer num)
    {
        return isPalindrome().apply(num);
    }

    private Function<Integer, String> isOdd()
    {
        return (n) -> n % 2 == 0 ? "EVEN" : "ODD";
    }

    private Function<Integer, String> isPrime()
    {
        return (num) -> {
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
    }

    private Function<Integer, String> isPalindrome()
    {
        return (num) -> {
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

    private Function<List<String>, List<String>> noX()
    {
        return (list) -> list.stream().map(s -> s.replace("x", "")).collect(Collectors.toList());
    }

    private Function<List<Integer>, List<Integer>> rightDigit()
    {
        return (list) -> list.stream().map(i -> i % 10).collect(Collectors.toList());
    }

    private Function<List<Integer>, List<Integer>> doubling()
    {
        return (list) -> list.stream().map(i -> i * 2).collect(Collectors.toList());
    }
}