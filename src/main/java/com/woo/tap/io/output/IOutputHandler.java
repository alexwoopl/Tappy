package com.woo.tap.io.output;

import org.apache.commons.csv.CSVRecord;

public interface IOutputHandler {
    void handle(Iterable<CSVRecord> trips);
}
