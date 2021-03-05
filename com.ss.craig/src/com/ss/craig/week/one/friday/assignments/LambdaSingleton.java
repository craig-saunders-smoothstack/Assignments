/**
 * Friday Assignment: Sorting an array via lambdas
 */
package com.ss.craig.week.one.friday.assignments;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author Craig Saunders
 *
 */
public class LambdaSingleton extends InputUtility {
    private volatile static LambdaSingleton instance = null;
    private final String[] OPTIONS;

    private LambdaSingleton()
    {
        OPTIONS = new String[] { "Choose an Assignment:", 
                "0: None - Close program", 
                "1: Lambda Sorting",
                "2: Lambda Comma Separated String", 
                "3: Lambda Get all strings with 3 letters containing 'a'",
                "4: Date-time Birthday", 
                "5: Date-time Previous Thursday", 
                "6: Date-time ZoneId ZoneOffset difference",
                "7: Date-time convert to and from Instant and ZonedDateTime",
                "8: Date-time Month lengths for given year",
                "9: Date-time Get all Mondays for a given month of the current year",
                "10: Date-time Is given date Friday the 13th?" };
    }

    public static LambdaSingleton getInstance()
    {
        if (instance == null)
        {
            synchronized (LambdaSingleton.class)
            {
                if (instance == null)
                {
                    instance = new LambdaSingleton();
                }
            }
        }
        return instance;
    }

    public int runAssignments()
    {
        Integer continue_toggle = -1;
        while (continue_toggle != 0)
        {
            continue_toggle = getUserSelectionInteger(OPTIONS);
            if (continue_toggle > 0 && continue_toggle <= OPTIONS.length)
            {
                try
                {
                    chooseAssignment(continue_toggle);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    continue_toggle = 0;
                }
            }
            else
            {
                System.out.println("Thank you for your time! Exiting...");
                continue_toggle = 0;
            }
        }
        scnr.close(); // The scanner object is static, so this should close it in all exteded classes
        return continue_toggle;
    }

    public boolean chooseAssignment(Integer n) throws Exception
    {
        LambdaStreams ls = new LambdaStreams();
        DateTimeAPIExamples dt_examples = new DateTimeAPIExamples();
        switch (n)
        {
        case 1:
            ls.startBasicSorting();
            break;
        case 2:
            System.out.println(ls.getCommaSeparated(Arrays.asList(ls.getIntegerArray())));
            break;
        case 3:
            System.out.println(ls.getLimitListFromList(ls.getStringArray()));
            break;
        case 4:
            System.out.println("Using OffsetDateTime.class\n" + dt_examples.getBirthdayInNanoSeconds());
            break;
        case 5:
            System.out.println(
                    "Use random_date.minus(1, ChronoUnit.WEEKS) to get the day one week before the random date\n"
                            + "then .datesUntil(random_date) to stream all the days between\n"
                            + "and filter thursdays with .filter(d->d.getDayOfWeek() == DayOfWeek.THURSDAY)\n"
                            + "it should always provide one thursday, which is the previous Thrusday");
            LocalDate random_date = dt_examples.getRandomDate(8000);
            System.out.println(
                    "Random Date: " + DateTimeFormatter.ISO_LOCAL_DATE.format(random_date) + "\nPrevious Thursday: "
                            + DateTimeFormatter.ISO_LOCAL_DATE.format(dt_examples.getPreviousThursday(random_date)));
            break;
        case 6:
            System.out.println("ZoneId object contains the time-zone identifier\n"
                    + "   and rules to convert between Instant and LocalDateTime\n"
                    + "Where a ZoneOffset object contains the amount of time difference\n"
                    + "   from UTC/Greenwich ZoneId");
            break;
        case 7:
            Instant now = Instant.now();
            ZonedDateTime ztd = dt_examples.getZonedDateTime(now, ZoneId.systemDefault());
            System.out.println("From Insant to ZonedDateTime: instant.atZone(zone_id)\n"
                    + "From ZonedDateTime to Instant: ztd.toInstant()\n" + "Example instant from now: " + now
                    + "\nExample with system default ZoneId from that instant: " + ztd + "\nChange the ZoneId to UTC: "
                    + ztd.withZoneSameInstant(ZoneOffset.UTC) + "\nand back to an instant: "
                    + dt_examples.getInstant(ztd));
            break;
        case 8:
            dt_examples.printLengthOfAllMonthsInYear(2021);
            break;
        case 9:
            dt_examples.printMondaysInMonthYear(Year.of(2021).atMonth(11).getMonth(), 2021);
            break;
        case 10:
            System.out.println("Is today Friday the Thirteenth? " + dt_examples.isFridayThirteenth(LocalDate.now())
                    + "\nIs Friday, August 13, 2021 Friday the Thirteenth? "
                    + dt_examples.isFridayThirteenth(LocalDate.of(2021, 8, 13)));
            break;
        default:
            System.out.println("Invalid Assignmnet!");
            return false;
        }
        return true;
    }
}