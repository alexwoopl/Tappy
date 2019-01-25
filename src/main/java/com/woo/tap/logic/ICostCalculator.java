package com.woo.tap.logic;

public interface ICostCalculator {
    String findCost(String fromStopId, String toStopId);

    String getMaxCost(String fromStopId);
}
