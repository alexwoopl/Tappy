package com.woo.tap.io.input;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Simple wrapper for apache commons csv parsing.
 */
public class CSVInputHandler implements IInputHandler {


    public List<CSVRecord> handle(String filename) throws IOException {

        if(filename == null){
            return null;
        }

        Reader in = new FileReader(filename);

        CSVParser csv = CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().parse(in);
        List<CSVRecord> records = csv.getRecords();
        in.close();

        return records;
    }
}
