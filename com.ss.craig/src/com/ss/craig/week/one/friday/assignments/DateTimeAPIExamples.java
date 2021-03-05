/**
 * Friday Assignment: Using Date-Time API correctly
 */
package com.ss.craig.week.one.friday.assignments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Craig Saunders
 *
 */
public class DateTimeAPIExamples {
    public final OffsetDateTime BIRTHDAY;

    public DateTimeAPIExamples()
    {
        BIRTHDAY = OffsetDateTime.of(1995, 3, 16, 2, 56, 0, 299999,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }

    /**
     * Which class would you use to store your birthday in years, months, days,
     * seconds, and nanoseconds?
     * 
     * @return Returns birthday in OffsetDateTime
     */
    public OffsetDateTime getBirthdayInNanoSeconds()
    {
        return BIRTHDAY;
    }

    /**
     * @return Returns the local date of the previous thrusday
     */
    public LocalDate getRandomDate(long max_years)
    {

        return LocalDate.ofEpochDay((long) (Math.random() * (LocalDate.EPOCH.plusYears(max_years).toEpochDay())));
    }

    /**
     * Given a random date, how would you find the date of the previous Thursday?
     * 
     * @return
     */
    public LocalDate getPreviousThursday(LocalDate random_date)
    {
        List<LocalDate> thursdays = random_date.minus(1, ChronoUnit.WEEKS).datesUntil(random_date)
                .filter(d -> d.getDayOfWeek() == DayOfWeek.THURSDAY).collect(Collectors.toList());
        return thursdays.size() == 1 ? thursdays.get(0) : null;
    }

    /**
     * @param instant : Specified Instant
     * @param zone_id : ZoneId with rules for the instant to offset from
     * @return Returns the ZonedDateTime from an Instant and ZoneId provided
     */
    public ZonedDateTime getZonedDateTime(Instant instant, ZoneId zone_id)
    {
        return instant.atZone(zone_id);
    }

    /**
     * @param zdt : Specified ZonedDateTime
     * @return Returns the Instant from a ZonedDateTime provided
     */
    public Instant getInstant(ZonedDateTime zdt)
    {
        return zdt.toInstant();
    }

    /**
     * Prints out all the lenghts of the months in given year
     * 
     * @param year : Input year
     */
    public boolean printLengthOfAllMonthsInYear(Integer year)
    {
        System.out.println("Year: " + year);
        Arrays.asList(Month.values()).stream().forEach(m -> {
            System.out.println("Days in " + m + ": " + Year.of(year).atMonth(m).lengthOfMonth());
        });
        return true;
    }

    /**
     * Prints all Mondays in given month of given year
     * 
     * @param m    : Month
     * @param year : Year
     */
    public boolean printMondaysInMonthYear(Month m, Integer year)
    {
        System.out.println(m + " of year " + year + " Mondays:");
        LocalDate date = LocalDate.of(year, m, 1);
        date.datesUntil(LocalDate.of(year, m, Year.of(year).atMonth(m).lengthOfMonth()))
                .filter(d -> d.getDayOfWeek() == DayOfWeek.MONDAY).collect(Collectors.toList()).stream()
                .forEach(d -> System.out.println(d));
        return true;
    }

    /**
     * @param date : Given LocalDate
     * @return Returns true if date given is Friday and the 13th of the month, else
     *         false.
     */
    public boolean isFridayThirteenth(LocalDate date)
    {
        if (date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY)
        {
            return true;
        }
        return false;
    }
}
