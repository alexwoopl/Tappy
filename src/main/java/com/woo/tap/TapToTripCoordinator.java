package com.woo.tap;

import com.woo.tap.io.input.CSVInputHandler;
import com.woo.tap.io.input.IInputHandler;
import com.woo.tap.io.output.CSVOutputHandler;
import com.woo.tap.io.output.IOutputHandler;
import com.woo.tap.logic.ITapToTripLogic;
import com.woo.tap.logic.TapToTripLogic;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TapToTripCoordinator implements ITapToTripCoordinator {

    private IInputHandler inputHandler;
    private IOutputHandler outputHandler;
    private ITapToTripLogic tapToTripLogic;

    public TapToTripCoordinator(){
        inputHandler = new CSVInputHandler();
        tapToTripLogic = new TapToTripLogic();
        outputHandler = new CSVOutputHandler();
    }

    /**
     * Takes in a well formatted csv describing taps
     * Translate the input into trips and outputs to a file named "Trips.csv"
     * @param filename - The location of the input tap csv
     */
    public void createTripCSV(String filename) throws IOException, ParseException {

        //Parse input CSV into dictionary(or list of models).
        List<CSVRecord> taps = inputHandler.handle(filename);
        //Gain output info from input.
        List<List<String>> trips = tapToTripLogic.process(taps);
        //Format output into csv & create the file.
        outputHandler.handle(trips);

    }



}
