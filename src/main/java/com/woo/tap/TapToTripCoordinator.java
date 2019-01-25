package com.woo.tap;

import com.woo.tap.io.input.CSVInputHandler;
import com.woo.tap.io.input.IInputHandler;
import com.woo.tap.io.output.IOutputHandler;
import com.woo.tap.logic.ITapToTripLogic;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

public class TapToTripCoordinator implements ITapToTripCoordinator {

    private IInputHandler inputHandler;
    private IOutputHandler outputHandler;
    private ITapToTripLogic tapToTripLogic;

    public TapToTripCoordinator(){
        inputHandler = new CSVInputHandler();
    }

    /**
     * Takes in a well formatted csv describing taps
     * Translate the input into trips and outputs to a file named "Trips.csv"
     * @param filename - The location of the input tap csv
     */
    public void createTripCSV(String filename) throws IOException {

        //Parse input CSV into dictionary(or list of models).
        List<CSVRecord> taps = inputHandler.handle(filename);
        //Gain output info from input.
        List<CSVRecord> trips = tapToTripLogic.process(taps);
        //Format output into csv & create the file.
        outputHandler.handle(trips);

    }



}
