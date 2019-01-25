package com.woo.tap.logic;

public class TripStatusCalculator implements IStatusCalculator {

    private final static String CANCELLED_STATUS = "CANCELLED";
    private final static String COMPLETED_STATUS = "COMPLETED";
    private final static String INCOMPLETE_STATUS = "INCOMPLETE";

    @Override
    public String calcStatus(String fromStopId, String toStopId) {

        if(toStopId == null){
            return INCOMPLETE_STATUS;
        } else if(fromStopId.equals(toStopId)) {
            return CANCELLED_STATUS;
        } else {
            return COMPLETED_STATUS;
        }
    }
}
