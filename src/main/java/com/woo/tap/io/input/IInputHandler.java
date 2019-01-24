package com.woo.tap.io.input;

import org.apache.commons.csv.CSVRecord;

public interface IInputHandler {
    Iterable<CSVRecord> handle(String filename);
}
