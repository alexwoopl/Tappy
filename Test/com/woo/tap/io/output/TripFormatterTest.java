package com.woo.tap.io.output;

import com.woo.tap.models.Trip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TripFormatterTest {

    private TripFormatter tripFormatter = new TripFormatter();

    @Test
    public void givenEmptyList__ReturnEmptyList(){
        List<List<String>> result = tripFormatter.format(new ArrayList<>());

        assertTrue("result should be empty but is size: " + result.size(),result.isEmpty());

    }

    @Test(expected = NullPointerException.class)
    public void givenNull__ReturnNull(){
        List<List<String>> result = tripFormatter.format(null);

    }

    @Test(expected = NullPointerException.class)
    public void givenListofNull__ThrowNullException(){
        List<Trip> input = new ArrayList<>();
        input.add(null);

        List<List<String>> result = tripFormatter.format(input);

    }

    @Test
    public void givenASingleTrip__ReturnStringConverted(){
        List<Trip> input = new ArrayList<>();
        Trip trip = new Trip();
        trip.setStarted("A");
        trip.setFinished("B");
        trip.setDurationSecs(1);
        trip.setFromStopId("C");
        trip.setToStopId("D");
        trip.setChargeAmount("E");
        trip.setCompanyId("F");
        trip.setBusId("G");
        trip.setPAN("H");
        trip.setStatus("I");
        input.add(trip);

        List<List<String>> result = tripFormatter.format(input);

        assertEquals("result should only have 1 element. Has: " + result.size(), 1, result.size());
        List<String> testResult = result.get(0);
        assertEquals("trip should only have 10 elements. Has: " + testResult.size(),10,testResult.size());

        assertEquals("A",testResult.get(0));
        assertEquals("B",testResult.get(1));
        assertEquals("1",testResult.get(2));
        assertEquals("C",testResult.get(3));
        assertEquals("D",testResult.get(4));
        assertEquals("E",testResult.get(5));
        assertEquals("F",testResult.get(6));
        assertEquals("G",testResult.get(7));
        assertEquals("H",testResult.get(8));
        assertEquals("I",testResult.get(9));


    }
    @Test
    public void givenASingleTripWithNullFields__ReturnStringConverted(){
        List<Trip> input = new ArrayList<>();
        Trip trip = new Trip();
        trip.setStarted("A");
        trip.setFinished("B");
        trip.setFromStopId("C");
        trip.setToStopId("D");
        trip.setCompanyId("F");
        trip.setBusId("G");
        trip.setPAN("H");
        trip.setStatus("I");
        input.add(trip);

        List<List<String>> result = tripFormatter.format(input);

        assertEquals("result should only have 1 element. Has: " + result.size(), 1, result.size());
        List<String> testResult = result.get(0);
        assertEquals("trip should only have 10 elements. Has: " + testResult.size(),10,testResult.size());

        assertEquals("A",testResult.get(0));
        assertEquals("B",testResult.get(1));
        assertEquals(null,testResult.get(2));
        assertEquals("C",testResult.get(3));
        assertEquals("D",testResult.get(4));
        assertEquals(null,testResult.get(5));
        assertEquals("F",testResult.get(6));
        assertEquals("G",testResult.get(7));
        assertEquals("H",testResult.get(8));
        assertEquals("I",testResult.get(9));
    }

    @Test
    public void givenMultipleTrips__ReturnStringConverted(){
        List<Trip> input = new ArrayList<>();
        Trip trip = new Trip();
        trip.setStarted("A");
        trip.setFinished("B");
        trip.setDurationSecs(1);
        trip.setFromStopId("C");
        trip.setToStopId("D");
        trip.setChargeAmount("E");
        trip.setCompanyId("F");
        trip.setBusId("G");
        trip.setPAN("H");
        trip.setStatus("I");
        input.add(trip);

        Trip trip1 = new Trip();
        trip1.setStarted("A");
        trip1.setFinished("B");
        trip1.setFromStopId("C");
        trip1.setChargeAmount("E");
        trip1.setCompanyId("F");
        trip1.setBusId("G");
        trip1.setPAN("H");
        trip1.setStatus("I");
        input.add(trip1);

        List<List<String>> result = tripFormatter.format(input);

        assertEquals("result should have 2 element. Has: " + result.size(), 2, result.size());

        List<String> testResult = result.get(0);
        assertEquals("trip should only have 10 elements. Has: " + testResult.size(),10,testResult.size());

        assertEquals("A",testResult.get(0));
        assertEquals("B",testResult.get(1));
        assertEquals("1",testResult.get(2));
        assertEquals("C",testResult.get(3));
        assertEquals("D",testResult.get(4));
        assertEquals("E",testResult.get(5));
        assertEquals("F",testResult.get(6));
        assertEquals("G",testResult.get(7));
        assertEquals("H",testResult.get(8));
        assertEquals("I",testResult.get(9));

        List<String> testResult2 = result.get(1);
        assertEquals("trip should only have 10 elements. Has: " + testResult.size(),10,testResult.size());

        assertEquals("A",testResult2.get(0));
        assertEquals("B",testResult2.get(1));
        assertEquals(null,testResult2.get(2));
        assertEquals("C",testResult2.get(3));
        assertEquals(null,testResult2.get(4));
        assertEquals("E",testResult2.get(5));
        assertEquals("F",testResult2.get(6));
        assertEquals("G",testResult2.get(7));
        assertEquals("H",testResult2.get(8));
        assertEquals("I",testResult2.get(9));


    }
}