package com.woo.tap.logic;

import java.util.HashMap;
import java.util.Map;

public class TripCostCalculator implements ICostCalculator {

    private static Map<String, String> costMap = null;
    private static Map<String, String> maxCostMap = null;

    /**
     * Singleton set up of the cost map.
     */
    public TripCostCalculator(){
        if (costMap == null){
            costMap = new HashMap<>();
            costMap.put("Stop1Stop2","$3.25");
            costMap.put("Stop1Stop3","$7.30");
            costMap.put("Stop2Stop3","$5.50");
            costMap.put("cancel","$0.00");
        }

        if (maxCostMap == null){
            maxCostMap = new HashMap<>();
            maxCostMap.put("Stop1","$7.30");
            maxCostMap.put("Stop2","$5.50");
            maxCostMap.put("Stop3","$7.30");
        }
    }

    @Override
    public String findCost(String fromStopId, String toStopId) {

        String costKey;

        if(fromStopId.compareTo(toStopId) < 0){
            costKey = costMap.get(fromStopId + toStopId);
        } else if (fromStopId.compareTo(toStopId) > 0) {
            costKey = costMap.get(toStopId + fromStopId);
        } else {
            costKey = costMap.get("cancel");
        }

        return costKey;
    }

    @Override
    public String getMaxCost(String fromStopId) {

        return maxCostMap.get(fromStopId);

    }
}
