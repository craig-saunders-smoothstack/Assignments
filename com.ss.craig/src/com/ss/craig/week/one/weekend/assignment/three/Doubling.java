/**
 * 
 */
package com.ss.craig.week.one.weekend.assignment.three;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Craig Saunders
 *
 */
public abstract class Doubling {
    public static Function<List<Integer>, List<Integer>> doubling = (list) -> {
        List<Integer> remainder_list = new ArrayList<Integer>();
        list.stream().forEach(i-> remainder_list.add(i*2));
        return remainder_list;
    };
}
