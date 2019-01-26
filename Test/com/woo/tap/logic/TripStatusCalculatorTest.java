package com.woo.tap.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TripStatusCalculatorTest {

    TripStatusCalculator tripStatusCalculator = new TripStatusCalculator();

    @Test
    public void calcStatusForSameStops__ReturnCancelledTrip(){

        String result = tripStatusCalculator.calcStatus("Stop1","Stop1");

        assertEquals("","CANCELLED",result);

    }

    @Test
    public void calcStatusForDifferentStops__ReturnCompletedTrip(){

        String result = tripStatusCalculator.calcStatus("Stop1","Stop2");

        assertEquals("","COMPLETED",result);

    }

    @Test
    public void calcStatusWithNullToStop__ReturnIncompleteTrip(){

        String result = tripStatusCalculator.calcStatus("Stop1",null);

        assertEquals("","INCOMPLETE",result);

    }

    @Test(expected = NullPointerException.class)
    public void calcStatusWithNullFromStop__ThrowNullException(){

        String result = tripStatusCalculator.calcStatus(null,"Stop1");

    }

    @Test(expected = NullPointerException.class)
    public void calcStatusWithNullToStop__ThrowNullException(){

        String result = tripStatusCalculator.calcStatus(null,null);

    }

}