package com.woo.tap.io.input;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * As this is a simple wrapper, we don't need to test the libraries functionality but rather our implementation.
 *
 * Since we assume the CSV is well formatted there isnt much to test here.
 *
 * Ideally I would like to test the output but it seems a little hard in this case due to the object that apache commons has opted to use.
 *
 */
public class CSVInputHandlerTest {

     private CSVInputHandler csvInputHandler = new CSVInputHandler();

    //Test resource properties
    private String baseInputFilePath = "Test/testresources/input/";

    @Test
    public void givenNull__returnsNull() throws IOException {

        List<CSVRecord> result = csvInputHandler.handle(null);

        assertNull(result);

    }

    @Test
    public void givenEmptyInputFile__returnsEmptyList() throws IOException {
        //SetUp
        String inputFilename = baseInputFilePath + "EmptyTap.csv";
        int expectedSize = 0;

        //Execute
        List<CSVRecord> result = csvInputHandler.handle(inputFilename);

        //Confirm
        assertNotNull("Null was returned.", result);
        assertEquals("Size found: " + result.size() + ". Size expected: " + expectedSize, result.size(), expectedSize);

    }

    @Test
    public void givenACSV__shouldReturnACSVList() throws IOException {
        //SetUp
        String inputFilename = baseInputFilePath + "Stop1To2.csv";
        int expectedSize = 4;
        int expectedValueSize = 7;

        //Execute
        List<CSVRecord> result = csvInputHandler.handle(inputFilename);

        //Confirm
        assertNotNull("Null was returned.", result);
        assertEquals("Size found: " + result.size() + ". Size expected: " + expectedSize, result.size(), expectedSize);

        CSVRecord recordToTest;
        recordToTest = result.get(0);
        assertEquals("Incorrect number of values. Is: " + recordToTest.size() + ". Should be: " + expectedValueSize, recordToTest.size(), expectedValueSize);
        assertEquals("", "1", recordToTest.get(0));
        assertEquals("", "22-01-2018 13:00:00", recordToTest.get(1));
        assertEquals("", "ON", recordToTest.get(2));
        assertEquals("", "Stop1", recordToTest.get(3));
        assertEquals("", "Company1", recordToTest.get(4));
        assertEquals("", "Bus37", recordToTest.get(5));
        assertEquals("", "5500005555555559", recordToTest.get(6));

        recordToTest = result.get(1);
        assertEquals("Incorrect number of values. Is: " + recordToTest.size() + ". Should be: " + expectedValueSize, recordToTest.size(), expectedValueSize);
        assertEquals("", "2", recordToTest.get(0));
        assertEquals("", "22-01-2018 13:05:00", recordToTest.get(1));
        assertEquals("", "OFF", recordToTest.get(2));
        assertEquals("", "Stop2", recordToTest.get(3));
        assertEquals("", "Company1", recordToTest.get(4));
        assertEquals("", "Bus37", recordToTest.get(5));
        assertEquals("", "5500005555555559", recordToTest.get(6));

        recordToTest = result.get(2);
        assertEquals("Incorrect number of values. Is: " + recordToTest.size() + ". Should be: " + expectedValueSize, recordToTest.size(), expectedValueSize);
        assertEquals("", "3", recordToTest.get(0));
        assertEquals("", "22-01-2018 13:08:00", recordToTest.get(1));
        assertEquals("", "ON", recordToTest.get(2));
        assertEquals("", "Stop2", recordToTest.get(3));
        assertEquals("", "Company1", recordToTest.get(4));
        assertEquals("", "Bus37", recordToTest.get(5));
        assertEquals("", "5500005555555559", recordToTest.get(6));

        recordToTest = result.get(3);
        assertEquals("Incorrect number of values. Is: " + recordToTest.size() + ". Should be: " + expectedValueSize, recordToTest.size(), expectedValueSize);
        assertEquals("", "4", recordToTest.get(0));
        assertEquals("", "22-01-2018 13:18:00", recordToTest.get(1));
        assertEquals("", "OFF", recordToTest.get(2));
        assertEquals("", "Stop1", recordToTest.get(3));
        assertEquals("", "Company1", recordToTest.get(4));
        assertEquals("", "Bus37", recordToTest.get(5));
        assertEquals("", "5500005555555559", recordToTest.get(6));
    }

}