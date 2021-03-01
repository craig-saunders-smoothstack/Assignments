/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignment.two.RightDigit;

/**
 * @author Craig Saunders
 *
 */
public class RightDigitTest extends RightDigit{
    List<Integer> non_negative_test;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        non_negative_test = Arrays.asList(new Integer[] { 1, 22, 93, 16, 8, 886, 8, 1, 10, 0, 2347 });
    }

    @Test
    public void test()
    {
        assertEquals(Arrays.asList(new Integer[] {1, 2, 3, 6, 8, 6, 8, 1, 0, 0, 7}), rightDigit.apply(non_negative_test));
    }

}
