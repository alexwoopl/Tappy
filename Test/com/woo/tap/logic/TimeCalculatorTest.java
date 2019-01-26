package com.woo.tap.logic;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * The assumption of the times given are always in the same day still applies.
 */
public class TimeCalculatorTest {

    private TimeCalculator timeCalculator = new TimeCalculator();



    @Test
    public void givenALargerStartTime__returnNegativeNumber() throws ParseException {
        String startTime = "22-01-2018 23:11:13";
        String endTime = "22-01-2018 22:59:13";

        Integer result = timeCalculator.getSecondsBetween(startTime, endTime);

        assertEquals("Time difference should be -720 but is: " + result, -720, (int) result);
    }


    @Test
    public void givenASmallerStartTime__returnPositiveNumber() throws ParseException {
        String startTime = "22-01-2018 22:59:13";
        String endTime = "22-01-2018 23:11:13";

        Integer result = timeCalculator.getSecondsBetween(startTime, endTime);

        assertEquals("Time difference should be 720 but is: " + result, 720, (int) result);
    }

    @Test
    public void givenTheSameStartAndEndTime__returnZero() throws ParseException {
        String time = "22-01-2018 22:59:13";

        Integer result = timeCalculator.getSecondsBetween(time, time);

        assertEquals("Time difference should be 0 but is: " + result, 0, (int) result);
    }


    @Test(expected = ParseException.class)
    public void givenMalformedTimeStamp__returnParseException() throws ParseException {
        String time = "22-01-2018T22:59:13";

        Integer result = timeCalculator.getSecondsBetween(time, time);
    }

    @Test(expected = NullPointerException.class)
    public void givenNull__returnNullException() throws ParseException {
        String time = "22-01-2018 22:59:13";

        Integer result = timeCalculator.getSecondsBetween(time, null);
    }

}