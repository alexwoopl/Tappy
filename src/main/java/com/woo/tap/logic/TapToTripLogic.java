package com.woo.tap.logic;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class TapToTripLogic implements ITapToTripLogic {
    public List<List<String>> process(Iterable<CSVRecord> taps) {

        if (taps == null) {
            return null;
        }

        //For each list of strings (records)
        //IF OFF TAP => Check bag for matching ON TAP, make record, store and delete from bag
        //IF ON TAP => Add to bag
        //AFTER FOR EACH; any remaining ON TAPS become incomplete, make their record and store
        //Return records


        return null;
    }
}
