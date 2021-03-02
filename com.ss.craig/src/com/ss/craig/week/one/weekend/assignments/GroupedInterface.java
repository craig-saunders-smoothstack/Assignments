/**
 * 
 */
package com.ss.craig.week.one.weekend.assignments;

import java.util.List;

/**
 * @author Craig Saunders
 *
 */
public interface GroupedInterface extends PerformOperation {
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
        return isOdd().apply(num);
    }

    default String isPalindrome(Integer num)
    {
        return isOdd().apply(num);
    }
}
