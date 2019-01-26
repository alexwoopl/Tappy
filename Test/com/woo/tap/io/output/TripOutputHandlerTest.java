package com.woo.tap.io.output;

import com.woo.tap.errors.ERROR_MESSAGE;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TripOutputHandlerTest {

    private IOutputHandler outputHandler = new TripOutputHandler();

    private String testOutputFile = "Trips.csv";
    private String baseExpectedOutputFilePath = "Test/testresources/expectedoutput/";

    //STDOUT redirecting properties ( Changing STDOUT to outContent so we can verify System.out.print's )
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);

        File file = new File(testOutputFile);
        file.delete();
    }






    @Test
    public void givenNull__makeNoFileAndPrintError() throws IOException {
        //execute
        outputHandler.handle(null);
        //Confirm
        assertEquals(ERROR_MESSAGE.OUTPUT_NULL.toString(), outContent.toString().trim());
        File output = new File(testOutputFile);
        assertTrue(!output.exists());
    }

    @Test
    public void givenOneTrip__makeFileWithOneTripAndHeaders() throws IOException {
        //Setup
        String expectedOutputFilename = baseExpectedOutputFilePath + "OutputSingleTrip.csv";

        List<List<String>> testData = new ArrayList<>();
        List<String> testTrip = new ArrayList<>();
        testTrip.add("22-01-2018 13:00:00");
        testTrip.add("22-01-2018 13:05:00");
        testTrip.add("300");
        testTrip.add("Stop1");
        testTrip.add("Stop2");
        testTrip.add("$3.25");
        testTrip.add("Company1");
        testTrip.add("B37");
        testTrip.add("5500005555555559");
        testTrip.add("COMPLETED");
        testData.add(testTrip);

        //Execute
        outputHandler.handle(testData);

        //Confirm
        File output = new File(testOutputFile);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue(output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));

    }

    @Test
    public void givenMultiTrips__makeFileWithMultiTripsAndHeaders() throws IOException {
        //Setup
        String expectedOutputFilename = baseExpectedOutputFilePath + "OneLineTrips.csv";
        List<List<String>> testData = new ArrayList<>();
        List<String> testTrip = new ArrayList<>();
        testTrip.add("22-01-2018 13:00:00");
        testTrip.add(null);
        testTrip.add(null);
        testTrip.add("Stop1");
        testTrip.add(null);
        testTrip.add("$7.30");
        testTrip.add("Company1");
        testTrip.add("Bus37");
        testTrip.add("5500005555555559");
        testTrip.add("INCOMPLETE");
        testData.add(testTrip);

        List<String> testTrip2 = new ArrayList<>();
        testTrip2.add("22-01-2018 13:01:00");
        testTrip2.add(null);
        testTrip2.add(null);
        testTrip2.add("Stop2");
        testTrip2.add(null);
        testTrip2.add("$5.50");
        testTrip2.add("Company1");
        testTrip2.add("Bus37");
        testTrip2.add("5500005555555559");
        testTrip2.add("INCOMPLETE");
        testData.add(testTrip2);

        List<String> testTrip3 = new ArrayList<>();
        testTrip3.add("22-01-2018 13:02:00");
        testTrip3.add(null);
        testTrip3.add(null);
        testTrip3.add("Stop3");
        testTrip3.add(null);
        testTrip3.add("$7.30");
        testTrip3.add("Company1");
        testTrip3.add("Bus37");
        testTrip3.add("5500005555555559");
        testTrip3.add("INCOMPLETE");
        testData.add(testTrip3);

        //Execute
        outputHandler.handle(testData);

        //Confirm
        File output = new File(testOutputFile);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue(output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));
    }

    @Test
    public void givenEmptyTrips__makeFileWithOnlyHeaders() throws IOException {

        //Setup
        String expectedOutputFilename = baseExpectedOutputFilePath + "EmptyTrips.csv";

        List<List<String>> testData = new ArrayList<>();

        //Execute
        outputHandler.handle(testData);

        //Confirm
        File output = new File(testOutputFile);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue(output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));
    }

}