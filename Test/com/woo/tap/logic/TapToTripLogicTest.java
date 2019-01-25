package com.woo.tap.logic;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
    public void givenNull_returnNull(){

        List<List<String>> result = tapToTripLogic.process(null);

        assertNull(result);
    }

    //Given an empty list => gives empty trips
    @Test
    public void givenAnEmptyTapsList__ReturnAnEmptyListOfTrips() throws IOException {
        //setup
        String filename = "EmptyTap.csv";
        List<CSVRecord> testData = getTestData(baseInputFilePath + filename);

        //execute
        List<List<String>> result = tapToTripLogic.process(testData);

        //Confirm
        assertTrue("result was populated when it should be empty.", result.isEmpty());

    }

    // Given multi in list => gives trips
    @Test
    public void givenATapsList__ReturnAListOfTrips(){



    }

    // Given list with only a seconds time difference
    @Test
    public void givenATripDurationOfSeconds__ReturnTripsWithCorrectDuration(){



    }

    // Given a list with only minutes difference
    @Test
    public void givenATripDurationOfMinutes__ReturnTripsWithCorrectDuration(){



    }

    // Given a list with only hours difference
    @Test
    public void givenATripDurationOfHours__ReturnTripsWithCorrectDuration(){



    }

    // Given a list with hours, minutes and seconds difference
    @Test
    public void givenATripDurationThatIsComplex__ReturnTripsWithCorrectDuration(){



    }

    //Given a list with no time difference
    @Test
    public void givenATripDurationOfNothing__ReturnTripsWithCorrectDuration(){



    }

    //Given a list with an incomplete trip
    @Test
    public void givenIncompleteTrips__ReturnTripsWithCorrectPricesAndInformation(){



    }

    //Given a list with a cancelled trip
    @Test
    public void givenACancelledTrip__ReturnTripsWithCorrectPricesAndInformation(){



    }



}