package com.woo.tap.logic;

public enum TAP_TYPE {
    ON("ON"),
    OFF("OFF");

    private final String text;

    TAP_TYPE(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
