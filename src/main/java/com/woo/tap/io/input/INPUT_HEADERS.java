package com.woo.tap.io.input;

public enum INPUT_HEADERS {
    DATE_TIME("DateTimeUTC"),
    TAP_TYPE("TapType"),
    STOP_ID("StopId"),
    COMPANY_ID("CompanyId"),
    BUS_ID("BusID"),
    PAN("PAN");

    private final String text;

    INPUT_HEADERS(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
