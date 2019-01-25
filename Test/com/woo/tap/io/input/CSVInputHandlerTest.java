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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * As this is a simple wrapper, we don't need to test the libraries functionality but rather our implementation.
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
    public void givenACSV__shouldReturnACSVList() throws IOException {
        String inputFilename = baseInputFilePath + "Stop1To2.csv";

        List<CSVRecord> result = csvInputHandler.handle(inputFilename);

        assertTrue("Null was returned.",result != null);
    }

}