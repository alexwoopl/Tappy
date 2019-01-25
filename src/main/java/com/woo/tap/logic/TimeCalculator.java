package com.woo.tap.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCalculator implements ITimeCalculator {

    private static final String TIMESTAMP_FORMAT = "dd-MM-YYYY HH:mm:ss";

    /**
     * Get's the second difference between two DateTime Strings of format:
     * dd-MM-YYYY HH:mm:ss
     * @param startTimeStamp
     * @param endTimeStamp
     * @return The time difference in seconds.
     * @throws ParseException
     */
    @Override
    public Integer getSecondsBetween(String startTimeStamp, String endTimeStamp) throws ParseException {

        //22-01-2018 13:00:00
        DateFormat stringToDateConverter = new SimpleDateFormat(TIMESTAMP_FORMAT);
        Date start;
        Date end;

        start = stringToDateConverter.parse(startTimeStamp);
        end = stringToDateConverter.parse(endTimeStamp);

        return Math.toIntExact((end.getTime() - start.getTime())/1000);


    }
}
