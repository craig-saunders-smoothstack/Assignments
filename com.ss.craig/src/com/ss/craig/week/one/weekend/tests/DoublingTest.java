/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignment.GroupedInterface;

/**
 * @author Craig Saunders
 *
 */
public class DoublingTest implements GroupedInterface {
    public List<Integer> test_list;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        test_list = Arrays.asList(new Integer[] { 1, 2, 3, 6, 8, 6, 8, -1 });
    }

    @Test
    public void test()
    {
        assertEquals(Arrays.asList(new Integer[] { 2, 4, 6, 12, 16, 12, 16, -2 }), doubling(test_list));
        assertEquals(0, doubling(new ArrayList<Integer>()).size());
    }

}
