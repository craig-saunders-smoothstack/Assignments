/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.weekend.assignment.four.NoX;

/**
 * @author Craig Saunders
 *
 */
public class NoXTest extends NoX{
    public List<String> test_list;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        test_list = Arrays.asList(new String[] {
                "ax","bb", "cx", "xxax", "xbxbx", "xxcx", "x"
        });
    }

    @Test
    public void test()
    {
        assertEquals(Arrays.asList(new String[] { "a","bb","c","a","bb","c","" }), noX.apply(test_list));
    }

}
