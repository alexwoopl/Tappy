package com.woo.tap.io.output;

import com.woo.tap.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripFormatter implements IFormatter<Trip> {


    /**
     * Turns each Trip in the provided list into a well-ordered list of strings.
     *
     * @param unformattedTrips - List of Trips to convert to String Lists
     * @return - List of Strings representing Trips in the order:
     * [started,finished,duration,fromStopId,toStopId,chargeAmount,companyId,busId,PAN,status]
     */
    @Override
    public List<List<String>> format(List<Trip> unformattedTrips) {

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
}
