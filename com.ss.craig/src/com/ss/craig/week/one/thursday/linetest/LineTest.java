/**
 * Thursday Assignment: Test Line class given
 */
package com.ss.craig.week.one.thursday.linetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Craig Saunders
 *
 */
public class LineTest {
    Line line_one;
    Line line_two;
    Line line_three;
    Line line_four;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setup()
    {
        line_one = new Line(2d, 2d, 1d, 1d);
        line_two = new Line(3d, 3d, 202.01d, 202d);
        line_three = new Line(3d, 3d, 3d, 3d);
        line_four = new Line(3d, 202d, 202d, 3d);
    }

    @Test
    public void testGetSlope()
    {
        expectedException.expect(ArithmeticException.class);
        assertEquals(line_one.getSlope(), 1d, 0.0001d);
        assertEquals(line_three.getSlope(), 0d, 0.0001d);
    }

    @Test
    public void testGetDistance()
    {
        assertEquals(line_one.getDistance(), Math.sqrt(2d), 0.0001d);
    }

    @Test
    public void testParallelTo()
    {
        assertTrue(line_one.parallelTo(line_two));
        assertFalse(line_one.parallelTo(line_four));
    }
}
