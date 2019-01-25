package com.woo.tap.logic;

import com.woo.tap.io.input.INPUT_HEADERS;
import com.woo.tap.models.Trip;
import org.apache.commons.csv.CSVRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TapToTripLogic implements ITapToTripLogic {

    private ICostCalculator costCalculator = new TripCostCalculator();
    private IStatusCalculator statusCalculator = new TripStatusCalculator();

    public List<List<String>> process(Iterable<CSVRecord> taps) throws ParseException {

        if (taps == null) {
            return null;
        }

        List<Trip> unformattedTrips = new ArrayList<>();
        List<CSVRecord> tapsBeingProcessed = new ArrayList<>();


        for (CSVRecord currentTap: taps) {
            String tapType = currentTap.get(INPUT_HEADERS.TAP_TYPE);

            if(tapType.equals("ON")){
                //Always add to the front of the list as the latest tap should be considered first.
                tapsBeingProcessed.add(0, currentTap);
            } else if (tapType.equals("OFF")) {

                CSVRecord matchedTap = null;
                //Find this taps matching "ON" tap based on company, bus and PAN.
                for(CSVRecord unpairedTap : tapsBeingProcessed){
                    if(
                            unpairedTap.get(INPUT_HEADERS.COMPANY_ID).equals(currentTap.get(INPUT_HEADERS.COMPANY_ID)) &&
                            unpairedTap.get(INPUT_HEADERS.BUS_ID).equals(currentTap.get(INPUT_HEADERS.BUS_ID)) &&
                            unpairedTap.get(INPUT_HEADERS.PAN).equals(currentTap.get(INPUT_HEADERS.PAN))
                    ){

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
                                getSecondsBetween(
                                        unpairedTap.get(INPUT_HEADERS.DATE_TIME),
                                        currentTap.get(INPUT_HEADERS.DATE_TIME)
                                )
                        );
                        //Calc Cost
                        trip.setChargeAmount(costCalculator.findCost(trip.getFromStopId(), trip.getToStopId()));
                        //Calc Status
                        trip.setStatus(statusCalculator.calcStatus(trip.getFromStopId(), trip.getToStopId()));


                        unformattedTrips.add(trip);

                        matchedTap = unpairedTap;
                        break;
                    }
                }

                tapsBeingProcessed.remove(matchedTap);
            }
        }

        //AFTER FOR EACH; any remaining ON TAPS become incomplete, make record and store
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

        //Format and output
        return format(unformattedTrips);
    }

    private List<List<String>> format(List<Trip> unformattedTrips) {

        List<List<String>> formattedTrips = new ArrayList<>();

        for(Trip trip : unformattedTrips){
            List<String> currentTrip = new ArrayList<>();

            currentTrip.add(trip.getStarted());
            currentTrip.add(trip.getFinished());
            if(trip.getDurationSecs() != null) {
                currentTrip.add(String.valueOf(trip.getDurationSecs()));
            } else {
                currentTrip.add(null);
            }
            currentTrip.add(trip.getFromStopId());
            currentTrip.add(trip.getToStopId());
            currentTrip.add(trip.getChargeAmount());
            currentTrip.add(trip.getCompanyId());
            currentTrip.add(trip.getBusId());
            currentTrip.add(trip.getPAN());
            currentTrip.add(trip.getStatus());

            formattedTrips.add(currentTrip);
        }

        return formattedTrips;
    }

    /**
     * Get's the second difference between two DateTime Strings of format:
     * DD-MM-YYYY HH:MM:SS
     *
     * @param startDateTime - Starting DateTime
     * @param endDateTime - End DateTime
     * @return - The difference in seconds
     */
    private Integer getSecondsBetween(String startDateTime, String endDateTime) throws ParseException {

        //22-01-2018 13:00:00
        DateFormat stringToDateConverter = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        Date start;
        Date end;

        start = stringToDateConverter.parse(startDateTime);
        end = stringToDateConverter.parse(endDateTime);

        return Math.toIntExact((end.getTime() - start.getTime())/1000);

    }
}
