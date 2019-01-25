package com.woo.tap.io.input;

import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IInputHandler {
    List<CSVRecord> handle(String filename) throws IOException;
}
