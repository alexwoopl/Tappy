package com.woo.tap.logic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TripCostCalculatorTest {

    private TripCostCalculator tripCostCalculator = new TripCostCalculator();

    //Find cost stop 1 to 2
    @Test
    public void findCostForStop1ToStop2__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop1", "Stop2");

        assertEquals("Should be: $3.25 but was: " + result,"$3.25",result);
    }
    //Find cost stop 1 to 3
    @Test
    public void findCostForStop1ToStop3__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop1", "Stop3");

        assertEquals("Should be: $7.30 but was: " + result,"$7.30",result);
    }
    //Find cost stop 2 to 3
    @Test
    public void findCostForStop2ToStop3__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop2", "Stop3");

        assertEquals("Should be: $5.50 but was: " + result,"$5.50",result);
    }
    //Find cost stop 2 to 1
    @Test
    public void findCostForStop2ToStop1__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop2", "Stop1");

        assertEquals("Should be: $3.25 but was: " + result,"$3.25",result);
    }
    //Find cost stop 3 to 1
    @Test
    public void findCostForStop3ToStop1__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop3", "Stop1");

        assertEquals("Should be: $7.30 but was: " + result,"$7.30",result);
    }
    //Find cost stop 3 to 2
    @Test
    public void findCostForStop3ToStop2__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop3", "Stop2");

        assertEquals("Should be: $5.50 but was: " + result,"$5.50",result);
    }
    //Find cost null
    @Test(expected = NullPointerException.class)
    public void findCostWithNull__ThrowNullPointerException(){

        String result = tripCostCalculator.findCost("Stop3", null);
    }
    //Find cost non-existing
    @Test(expected = NoSuchElementException.class)
    public void findCostWithNonExistingStop__ThrowNoSuchElementException(){

        String result = tripCostCalculator.findCost("Stop3", "Stop5");
    }
    //Cancelled Trip
    @Test
    public void findCostForSameStop__ReturnCorrectPrice(){

        String result = tripCostCalculator.findCost("Stop3", "Stop3");

        assertEquals("Should be: $0.00 but was: " + result,"$0.00",result);
    }



    //Max cost stop 1
    @Test
    public void getMaxCostForStop1__ReturnCorrectPrice(){

        String result = tripCostCalculator.getMaxCost("Stop1");

        assertEquals("Should be: $7.30 but was: " + result,"$7.30",result);
    }
    //Max cost stop 2
    @Test
    public void getMaxCostForStop2__ReturnCorrectPrice(){

        String result = tripCostCalculator.getMaxCost("Stop2");

        assertEquals("Should be: $5.50 but was: " + result,"$5.50",result);
    }
    //Max cost stop 3
    @Test
    public void getMaxCostForStop3__ReturnCorrectPrice(){

        String result = tripCostCalculator.getMaxCost("Stop3");

        assertEquals("Should be: $7.30 but was: " + result,"$7.30",result);
    }
    //Max cost null
    @Test(expected = NoSuchElementException.class)
    public void getMaxCostForNull__ReturnCorrectPrice(){

        String result = tripCostCalculator.getMaxCost(null);
    }
    //Max cost Non-exisiting
    @Test(expected = NoSuchElementException.class)
    public void getMaxCostForNonExisting__ReturnCorrectPrice(){

        String result = tripCostCalculator.getMaxCost("NotARealStop");
    }

}