/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignment.five.GroupSumClump;

/**
 * @author Craig Saunders
 *
 */
public class GroupSumClumpTest extends GroupSumClump{
    public Integer[] test_ints_true_one;
    public Integer[] test_ints_true_two;
    public Integer[] test_ints_false_one;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        test_ints_true_one = new Integer[] {2,4,8};
        test_ints_true_two = new Integer[] {1,2,4,8,1};
        test_ints_false_one = new Integer[] {2,4,4,8};
    }

    @Test
    public void test()
    {
        assertTrue(groupSumClump(test_ints_true_one, 10));
        assertTrue(groupSumClump(test_ints_true_two, 14));
        assertFalse(groupSumClump(test_ints_false_one, 14));
    }

}
