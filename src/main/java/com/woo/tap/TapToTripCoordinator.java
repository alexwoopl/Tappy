package com.woo.tap;

import com.woo.tap.io.input.IInputHandler;
import com.woo.tap.io.output.IOutputHandler;
import com.woo.tap.logic.ITapToTripLogic;
import org.apache.commons.csv.CSVRecord;

public class TapToTripCoordinator implements ITapToTripCoordinator {

    private IInputHandler inputHandler;
    private IOutputHandler outputHandler;
    private ITapToTripLogic tapToTripLogic;


    public void createTripCSV(String filename) {

        //Parse input CSV into dictionary(or list of models).
        Iterable<CSVRecord> taps = inputHandler.handle(filename);
        //Gain output info from input.
        Iterable<CSVRecord> trips = tapToTripLogic.process(taps);
        //Format output into csv & return it.
        outputHandler.handle(trips);

    }



}
