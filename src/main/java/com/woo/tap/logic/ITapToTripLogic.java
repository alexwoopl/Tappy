package com.woo.tap.logic;

import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.util.List;

public interface ITapToTripLogic {
    List<List<String>> process(Iterable<CSVRecord> taps) throws ParseException;
}
