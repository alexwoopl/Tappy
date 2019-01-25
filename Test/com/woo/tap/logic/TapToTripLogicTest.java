package com.woo.tap.logic;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class TapToTripLogicTest {

    TapToTripLogic tapToTripLogic = new TapToTripLogic();

    //Test resource properties
    private String baseInputFilePath = "Test/testresources/input/";

    /**
     * Usually I would use a mock and create the data but as CSVRecords are difficult are to construct, I Will go with this method.
     * The reason I don't use the CSVInputHandler despite being similar is that this isnt for integration testing and should be independent.
     *
     * @param filename - The file to get the test data from
     * @return - Test data as CSVRecords
     * @throws IOException
     */
    private List<CSVRecord> getTestData(String filename) throws IOException {
        Reader in = new FileReader(filename);
        CSVParser csv = CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().parse(in);
        List<CSVRecord> records = csv.getRecords();
        in.close();

        return records;
    }

    @Test
    public void givenNull_returnNull() throws ParseException {

        List<List<String>> result = tapToTripLogic.process(null);

        assertNull(result);
    }

    //Given an empty list => gives empty trips
    @Test
    public void givenAnEmptyTapsList__ReturnAnEmptyListOfTrips() throws IOException, ParseException {
        //setup
        String filename = "EmptyTap.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertTrue("result was populated when it should be empty.", result.isEmpty());

    }

    @Test
    public void givenCompletedTrips__ReturnAListOfTrips() throws IOException, ParseException {
        //setup
        String filename = "Stop1To2.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),2);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(0), "22-01-2018 13:00:00");
        assertEquals(recordToTest.get(1), "22-01-2018 13:05:00");
        assertEquals(recordToTest.get(2), "300");
        assertEquals(recordToTest.get(3), "Stop1");
        assertEquals(recordToTest.get(4), "Stop2");
        assertEquals(recordToTest.get(5), "$3.25");
        assertEquals(recordToTest.get(6), "Company1");
        assertEquals(recordToTest.get(7), "Bus37");
        assertEquals(recordToTest.get(8), "5500005555555559");
        assertEquals(recordToTest.get(9), "COMPLETED");

        recordToTest = result.get(1);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(0), "22-01-2018 13:08:00");
        assertEquals(recordToTest.get(1), "22-01-2018 13:18:00");
        assertEquals(recordToTest.get(2), "600");
        assertEquals(recordToTest.get(3), "Stop2");
        assertEquals(recordToTest.get(4), "Stop1");
        assertEquals(recordToTest.get(5), "$3.25");
        assertEquals(recordToTest.get(6), "Company1");
        assertEquals(recordToTest.get(7), "Bus37");
        assertEquals(recordToTest.get(8), "5500005555555559");
        assertEquals(recordToTest.get(9), "COMPLETED");


    }

    // Given list with only a seconds time difference
    @Test
    public void givenATripDurationOfSeconds__ReturnTripsWithCorrectDuration() throws IOException, ParseException {
        //setup
        String filename = "TripWithSecondsDuration.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),1);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "24");
    }

    // Given a list with only minutes difference
    @Test
    public void givenATripDurationOfMinutes__ReturnTripsWithCorrectDuration() throws IOException, ParseException {
        //setup
        String filename = "Stop1To2.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),2);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "300");

        recordToTest = result.get(1);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "600");

    }

    // Given a list with only hours difference
    @Test
    public void givenATripDurationOfHours__ReturnTripsWithCorrectDuration() throws IOException, ParseException {
        //setup
        String filename = "TripWithHoursDuration.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),1);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "18000");


    }

    // Given a list with hours, minutes and seconds difference
    @Test
    public void givenATripDurationThatIsComplex__ReturnTripsWithCorrectDuration() throws IOException, ParseException {
        //setup
        String filename = "TripWithMultiDuration.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),1);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "18191");
    }

    //Given a list with no time difference
    @Test
    public void givenATripDurationOfNothing__ReturnTripsWithCorrectDuration() throws IOException, ParseException {
        //setup
        String filename = "TripWithNoDuration.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),1);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(2), "0");
    }

    //Given a list with an incomplete trip
    @Test
    public void givenIncompleteTrips__ReturnTripsWithCorrectPricesAndInformation() throws IOException, ParseException {

        //setup
        String filename = "OneLineTap.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),3);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(1), null);
        assertEquals(recordToTest.get(2), null);
        assertEquals(recordToTest.get(4), null);
        assertEquals(recordToTest.get(5), "$7.30");
        assertEquals(recordToTest.get(9), "INCOMPLETE");

        recordToTest = result.get(1);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(1), null);
        assertEquals(recordToTest.get(2), null);
        assertEquals(recordToTest.get(4), null);
        assertEquals(recordToTest.get(5), "$5.50");
        assertEquals(recordToTest.get(9), "INCOMPLETE");

        recordToTest = result.get(2);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(1), null);
        assertEquals(recordToTest.get(2), null);
        assertEquals(recordToTest.get(4), null);
        assertEquals(recordToTest.get(5), "$7.30");
        assertEquals(recordToTest.get(9), "INCOMPLETE");
    }

    //Given a list with a cancelled trip
    @Test
    public void givenACancelledTrip__ReturnTripsWithCorrectPricesAndInformation() throws IOException, ParseException {

        //setup
        String filename = "CancelledTrip.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),2);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(9), "COMPLETED");

        recordToTest = result.get(1);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(9), "CANCELLED");

    }

    @Test
    public void givenCompletedTrips__ReturnCorrectPrices() throws IOException, ParseException {
        //setup
        String filename = "AllStops.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertNotNull(result);
        assertEquals(result.size(),6);

        //The order of these lists should correspond to the TRIP_OUTPUT_HEADERS order
        List<String> recordToTest = result.get(0);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$3.25");
        recordToTest = result.get(1);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$3.25");

        recordToTest = result.get(2);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$7.30");
        recordToTest = result.get(3);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$7.30");

        recordToTest = result.get(4);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$5.50");
        recordToTest = result.get(5);
        assertEquals(recordToTest.size(), 10);
        assertEquals(recordToTest.get(5), "$5.50");

    }

}