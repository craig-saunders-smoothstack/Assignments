/**
 * 
 */
package com.ss.craig.week.one.friday.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ss.craig.week.one.friday.assignments.InputUtility;

/**
 * @author Craig Saunders
 *
 */
public class InputUtilityTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void test()
    {
        assertEquals(0, (int) InputUtility.getUserSelectionInteger(new ArrayList<String>()));
        assertEquals(0, (int) InputUtility.getUserSelectionInteger(new String[] {}));
        assertEquals(1, (int) InputUtility.getUserSelectionInteger(new String[] { "Type '1' for test" }));
        assertEquals(0, (int) InputUtility.getUserSelectionInteger(new String[] { "Type 'a' for test" }));
    }

}
