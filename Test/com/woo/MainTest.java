package com.woo;

import com.woo.tap.errors.ERROR_MESSAGE;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * This is the end to end/Scenario testing.
 * I realise Junit was not made with this in mind and something like cucumber would be preferable to help explain what the program is expected to do in more 'english terms'
 * however for this exersize being a simple case and not going to be expanded on I believe this will work just as well!
 *
 * There are not many cases here as there is little validation on the CSV which is assumed to be well formatted already.
 *
 * I like to write end to end tests first for the BDD values! Even if I don't write the test code(There may be some uncertainty) It still adds the BDD value as I will design my code to fit the tests! Not vice versa :)
 */
public class MainTest {

    //STDOUT redirecting properties ( Changing STDOUT to outContent so we can verify System.out.print's )
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    //Test resource properties
    private String baseInputFilePath = "Test/testresources/input/";
    private String baseExpectedOutputFilePath = "Test/testresources/expectedoutput/";
    private String outputFilename =  "Trips.csv";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(originalOut);

        File file = new File(outputFilename);
        if(file.delete()){
            System.out.println("Test file Trips.csv was cleaned at " + baseInputFilePath + "Trips.csv");
        }
        else{
            System.out.println("unable to clean test files/No test files to clean");
        }


    }

    @Test
    public void givenAnIncompleteTapCSV__ThenTheTripCSVShouldBeMade() throws IOException, ParseException {
        String inputFilename = baseInputFilePath + "OneLineTap.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "OneLineTrips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));
    }

    @Test
    public void givenAnEmptyTapCSV__ThenTheTripCSVShouldBeMadeAndEmpty() throws IOException, ParseException {
        String inputFilename = baseInputFilePath + "EmptyTap.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "EmptyTrips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));
    }

    @Test
    public void givenATapCSVFromStop1To2__ThenTheTripCSVShouldBeMade() throws IOException, ParseException {

        String inputFilename = baseInputFilePath + "Stop1To2.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "Trips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));

    }

    @Test
    public void givenATapCSVFromStop1To3__ThenTheTripCSVShouldBeMade() throws IOException, ParseException {

        String inputFilename = baseInputFilePath + "Stop1To3.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "Trips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));

    }

    @Test
    public void givenATapCSVFromStop2To3__ThenTheTripCSVShouldBeMade() throws IOException, ParseException {

        String inputFilename = baseInputFilePath + "Stop2To3.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "Trips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));

    }

    @Test
    public void givenALongTapCSV__ThenTheTripCSVShouldBeMade() throws IOException, ParseException {

        String inputFilename = baseInputFilePath + "LongTap.csv";
        String expectedOutputFilename = baseExpectedOutputFilePath + "LongTrips.csv";

        Main.main(new String[]{inputFilename});

        File output = new File(outputFilename);
        File expectedOutput = new File(expectedOutputFilename);
        assertTrue("Output does not exist when it should.", output.exists());
        assertTrue("Output did not match expected output.", FileUtils.contentEquals(output, expectedOutput));

    }

    @Test
    public void givenNoTapCSV__ThenErrorMessageShouldBeDisplayed() throws IOException, ParseException {
        String expectedOutputFilename = baseExpectedOutputFilePath + "LongTrips.csv";

        Main.main(new String[]{});

        File output = new File(outputFilename);
        assertEquals(ERROR_MESSAGE.NO_ARGUMENTS.toString(), outContent.toString().trim());
        assertTrue("Output file should NOT have been created",!output.exists());
    }

    @Test
    public void givenMultipleArgs__ThenErrorMessageShouldBeDisplayed() throws IOException, ParseException {
        String expectedOutputFilename = baseExpectedOutputFilePath + "LongTrips.csv";
        String inputFilename1 = baseInputFilePath + "arg1";
        String inputFilename2 = baseInputFilePath + "arg2";

        Main.main(new String[]{inputFilename1, inputFilename2});

        File output = new File(outputFilename);
        assertEquals(ERROR_MESSAGE.MULTIPLE_ARGUMENTS.toString(), outContent.toString().trim());
        assertTrue("Output file should NOT have been created",!output.exists());
    }


}
