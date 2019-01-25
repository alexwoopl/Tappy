package com.woo.tap.logic;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface ITapToTripLogic {
    List<CSVRecord> process(Iterable<CSVRecord> taps);
}
