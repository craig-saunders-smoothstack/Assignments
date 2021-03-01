/**
 * 
 */
package com.ss.craig.week.one.weekend.assignment.four;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Craig Saunders
 *
 */
public abstract class NoX {
    public Function<List<String>, List<String>> noX = (list) -> {
      List<String> cleaned = new ArrayList<String>(); 
      list.stream().forEach(s -> cleaned.add(s.replaceAll("x", "")));
      return cleaned;
    };
}
