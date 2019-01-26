package com.woo.tap.logic;

public class TripStatusCalculator implements IStatusCalculator {

    private final static String CANCELLED_STATUS = "CANCELLED";
    private final static String COMPLETED_STATUS = "COMPLETED";
    private final static String INCOMPLETE_STATUS = "INCOMPLETE";

    /**
     * Given the start and end stop, finds what the status of the trip was:
     * - No toStopId = INCOMPLETE
     * - Id's are the same = CANCELLED
     * - Otherwise = COMPLETED
     * @param fromStopId - Starting stop
     * @param toStopId - Ending stop
     * @return
     */
    @Override
    public String calcStatus(String fromStopId, String toStopId) {

        if(fromStopId.equals(toStopId)){
            return CANCELLED_STATUS;
        } else if(toStopId == null) {
            return INCOMPLETE_STATUS;
        } else {
            return COMPLETED_STATUS;
        }
    }
}
