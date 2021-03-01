/**
 * 
 */
package com.ss.craig.testing.week.one.friday;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ss.craig.week.one.friday.assignments.DateTimeAPIExamples;

/**
 * @author Craig Saunders
 *
 */
public class DateTimeAPIExamplesTest {
    public DateTimeAPIExamples dtae = new DateTimeAPIExamples();
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
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
        assertEquals(dtae.BIRTHDAY, dtae.getBirthdayInNanoSeconds());
        assertEquals(1970, dtae.getRandomDate(0).getYear());
        assertEquals(DayOfWeek.THURSDAY, dtae.getPreviousThursday(dtae.getRandomDate(8000)).getDayOfWeek());
        assertEquals(Instant.ofEpochSecond(10000000), dtae.getZonedDateTime(Instant.ofEpochSecond(10000000), ZoneId.systemDefault()).toInstant());
        assertEquals(Instant.ofEpochSecond(100600000), dtae.getInstant(dtae.getZonedDateTime(Instant.ofEpochSecond(100600000), ZoneId.systemDefault())));
        assertEquals(ZoneId.systemDefault(), dtae.getZonedDateTime(Instant.ofEpochSecond(100600000), ZoneId.systemDefault()).getZone());
        assertEquals(false, dtae.isFridayThirteenth(LocalDate.of(2021, 2, 28)));
        assertEquals(true, dtae.isFridayThirteenth(LocalDate.of(2021, 8, 13)));
        assertTrue(dtae.printLengthOfAllMonthsInYear(2021));
        assertTrue(dtae.printMondaysInMonthYear(Month.NOVEMBER, 2021));
    }

}
