package com.woo.tap.logic;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TapToTripLogicTest {

    TapToTripLogic tapToTripLogic = new TapToTripLogic();

    //Test resource properties
    private String baseInputFilePath = "Test/testresources/input/";

    @Test
    public void givenNull_returnNull(){

        List<List<String>> result = tapToTripLogic.process(null);

        assertNull(result);
    }

    //Given an empty list => gives empty trips
    @Test
    public void givenAnEmptyTapsList__ReturnAnEmptyListOfTrips(){



    }

    // Given multi in list => gives trips

    // Given list with only a seconds time difference

    // Given a list with only minutes difference
    // Given a list with only hours difference

    // Given a list with hours, minutes and seconds difference

    //Given a list with no time difference

    //Given a list with an incomplete trip

    //Given a list with a cancelled trip



}