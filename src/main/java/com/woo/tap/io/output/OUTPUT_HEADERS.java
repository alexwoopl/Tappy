package com.woo.tap.io.output;

public enum OUTPUT_HEADERS {

    STARTED("Started"),
    FINISHED("Finished"),
    DURATION_SECS("DurationSecs"),
    FROM_STOP_ID("FromStopId"),
    TO_STOP_ID("ToStopId"),
    CHARGE_AMOUNT("ChargeAmount"),
    COMPANY_ID("CompanyId"),
    BUS_ID("BusID"),
    PAN("PAN"),
    STATUS("Status");


    private final String text;

    OUTPUT_HEADERS(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
