package com.woo;

import com.woo.tap.ITapToTripCoordinator;
import com.woo.tap.TapToTripCoordinator;
import com.woo.tap.errors.ERROR_MESSAGE;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    private static ITapToTripCoordinator tapToTrip;

    public static void main(String[] args) throws IOException, ParseException {

        //Only initialize if there is an arg supplied
        if(args.length == 1){
            init();

            String filename = args[0];
            tapToTrip.createTripCSV(filename);
        } else if (args.length > 1) {
            //Only one arg is accepted
            //For errors we simply printing currently so no complex error handler is required.
            System.out.println(ERROR_MESSAGE.MULTIPLE_ARGUMENTS);
        } else {
            //If no args are supplied print an error
            System.out.println(ERROR_MESSAGE.NO_ARGUMENTS);
        }



    }

    /**
     * Initializes the class and its properties.
     */
    private static void init() {
        tapToTrip = new TapToTripCoordinator();
    }
}
