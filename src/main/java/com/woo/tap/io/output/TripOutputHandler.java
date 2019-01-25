package com.woo.tap.io.output;

import com.woo.tap.errors.ERROR_MESSAGE;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TripOutputHandler implements IOutputHandler {

    private static final String outputFilename = "Trips.csv";

    @Override
    public void handle(List<List<String>> trips) throws IOException {

        //If a null is given then skip creating a csv and print a message indicating this.
        //In reality this would be a logged with more details or even an exception depending on use cases.
        if(trips == null){
            System.out.println(ERROR_MESSAGE.OUTPUT_NULL);
            return;
        }

        //Create the CSV printer
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename));
        CSVPrinter csvPrinter = new CSVPrinter(writer,
                CSVFormat.DEFAULT.withHeader(
                        OUTPUT_HEADERS.STARTED.toString(),
                        OUTPUT_HEADERS.FINISHED.toString(),
                        OUTPUT_HEADERS.DURATION_SECS.toString(),
                        OUTPUT_HEADERS.FROM_STOP_ID.toString(),
                        OUTPUT_HEADERS.TO_STOP_ID.toString(),
                        OUTPUT_HEADERS.CHARGE_AMOUNT.toString(),
                        OUTPUT_HEADERS.COMPANY_ID.toString(),
                        OUTPUT_HEADERS.BUS_ID.toString(),
                        OUTPUT_HEADERS.PAN.toString(),
                        OUTPUT_HEADERS.STATUS.toString()
                ).withTrim()
        );

        //Print each trip into the output file.
        for(List<String> trip : trips){
            csvPrinter.printRecord(trip);
        }

        //Clean up
        csvPrinter.flush();
        csvPrinter.close();


    }
}
