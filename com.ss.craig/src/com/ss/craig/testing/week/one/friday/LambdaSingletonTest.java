/**
 * 
 */
package com.ss.craig.testing.week.one.friday;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ss.craig.week.one.friday.assignments.LambdaSingleton;

/**
 * @author Craig Saunders
 *
 */
public class LambdaSingletonTest {
    LambdaSingleton instance;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        instance = LambdaSingleton.getInstance();
    }

    @Test
    public void test() throws Exception
    {
        assertFalse(instance.chooseAssignment(-1));
        assertEquals(0, instance.runAssignments());
    }

}
