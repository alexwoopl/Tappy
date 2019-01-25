package com.woo.tap.logic;

import java.text.ParseException;

public interface ITimeCalculator {
    Integer getSecondsBetween(String startTimeStamp, String endTimeStamp) throws ParseException;
}
