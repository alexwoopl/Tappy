package com.woo.tap.logic;

import org.apache.commons.csv.CSVRecord;

public interface ITapToTripLogic {
    Iterable<CSVRecord> process(Iterable<CSVRecord> taps);
}
