package com.woo.tap.logic;

import com.woo.tap.io.input.INPUT_HEADERS;
import com.woo.tap.io.output.IFormatter;
import com.woo.tap.io.output.TripFormatter;
import com.woo.tap.models.Trip;
import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TapToTripLogic implements ITapToTripLogic {

    private ICostCalculator costCalculator;
    private IStatusCalculator statusCalculator;
    private ITimeCalculator timeCalculator;
    private IFormatter<Trip> formatter;

    /**
     * Initialize the classes properties
     */
    public TapToTripLogic(){
        costCalculator = new TripCostCalculator();
        statusCalculator = new TripStatusCalculator();
        timeCalculator = new TimeCalculator();
        formatter = new TripFormatter();
    }

    /**
     * Main processing function for turning "Taps" into "Trips"
     *
     * See corresponding unit tests for input format.
     *
     * @param taps - tap records taken directly from input.
     * @return the Trips created from taps, formatted into a list of Strings
     * @throws ParseException
     */
    public List<List<String>> process(Iterable<CSVRecord> taps) throws ParseException {

        if (taps == null) {
            return null;
        }

        List<Trip> unformattedTrips = new ArrayList<>();
        List<CSVRecord> tapsBeingProcessed = new ArrayList<>();


        for (CSVRecord currentTap: taps) {
            String tapType = currentTap.get(INPUT_HEADERS.TAP_TYPE);

            if(tapType.equals(TAP_TYPE.ON.toString())){
                //Always add to the front of the list as the latest tap should be considered first.
                tapsBeingProcessed.add(0, currentTap);
            } else if (tapType.equals(TAP_TYPE.OFF.toString())) {

                CSVRecord matchedTap = null;
                //Find this taps matching "ON" tap based on company, bus and PAN.
                for(CSVRecord unpairedTap : tapsBeingProcessed){
                    if(
                            unpairedTap.get(INPUT_HEADERS.COMPANY_ID).equals(currentTap.get(INPUT_HEADERS.COMPANY_ID)) &&
                            unpairedTap.get(INPUT_HEADERS.BUS_ID).equals(currentTap.get(INPUT_HEADERS.BUS_ID)) &&
                            unpairedTap.get(INPUT_HEADERS.PAN).equals(currentTap.get(INPUT_HEADERS.PAN))
                    ){

                        Trip trip = createTrip(currentTap, unpairedTap);

                        unformattedTrips.add(trip);

                        matchedTap = unpairedTap;
                        break;
                    }
                }

                //Remove the used tap.
                tapsBeingProcessed.remove(matchedTap);
            }
        }

        //AFTER FOR EACH; any remaining TAPS become incomplete, make record and store
        addUnpairedTapsAsIncompleteTrips(unformattedTrips, tapsBeingProcessed);

        //Format and output
        return formatter.format(unformattedTrips);
    }

    /**
     * Creates the Trip given a start tap and end tap.
     *
     *
     * @param currentTap - The OFF tap
     * @param unpairedTap - The ON tap
     * @return - Resulting Trip object.
     * @throws ParseException
     */
    private Trip createTrip(CSVRecord currentTap, CSVRecord unpairedTap) throws ParseException {
        Trip trip = new Trip();
        trip.setStarted(unpairedTap.get(INPUT_HEADERS.DATE_TIME));
        trip.setFinished(currentTap.get(INPUT_HEADERS.DATE_TIME));
        trip.setFromStopId(unpairedTap.get(INPUT_HEADERS.STOP_ID));
        trip.setToStopId(currentTap.get(INPUT_HEADERS.STOP_ID));
        trip.setCompanyId(currentTap.get(INPUT_HEADERS.COMPANY_ID));
        trip.setBusId(currentTap.get(INPUT_HEADERS.BUS_ID));
        trip.setPAN(currentTap.get(INPUT_HEADERS.PAN));

        //Calc Duration
        trip.setDurationSecs(
                timeCalculator.getSecondsBetween(
                        unpairedTap.get(INPUT_HEADERS.DATE_TIME),
                        currentTap.get(INPUT_HEADERS.DATE_TIME)
                )
        );
        //Calc Cost
        trip.setChargeAmount(costCalculator.findCost(trip.getFromStopId(), trip.getToStopId()));
        //Calc Status
        trip.setStatus(statusCalculator.calcStatus(trip.getFromStopId(), trip.getToStopId()));
        return trip;
    }

    /**
     *
     * Adds the remaining elements from tapsBeingProcessed into the unformattedTrips as incomplete trips.
     *
     * @param unformattedTrips - List of Trips to add the incomplete trips to. MUTATED
     * @param tapsBeingProcessed - left over records to be added as incomplete.
     */
    private void addUnpairedTapsAsIncompleteTrips(List<Trip> unformattedTrips, List<CSVRecord> tapsBeingProcessed) {
        if(!tapsBeingProcessed.isEmpty()){
            for(CSVRecord record : tapsBeingProcessed) {
                Trip trip = new Trip();
                trip.setStarted(record.get(INPUT_HEADERS.DATE_TIME));
                trip.setFromStopId(record.get(INPUT_HEADERS.STOP_ID));
                trip.setCompanyId(record.get(INPUT_HEADERS.COMPANY_ID));
                trip.setBusId(record.get(INPUT_HEADERS.BUS_ID));
                trip.setPAN(record.get(INPUT_HEADERS.PAN));

                //Calc Cost
                trip.setChargeAmount(costCalculator.getMaxCost(trip.getFromStopId()));
                //Calc Status
                trip.setStatus(statusCalculator.calcStatus(trip.getFromStopId(), trip.getToStopId()));


                unformattedTrips.add(trip);

            }
        }
    }

}
