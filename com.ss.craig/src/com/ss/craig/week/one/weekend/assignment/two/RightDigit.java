/**
 * 
 */
package com.ss.craig.week.one.weekend.assignment.two;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Craig Saunders
 *
 */
public abstract class RightDigit {
    public static Function<List<Integer>, List<Integer>> rightDigit = (list) -> {
        List<Integer> remainder_list = new ArrayList<Integer>();
        list.stream().forEach(i-> remainder_list.add(i%10));
        return remainder_list;
    };
}
