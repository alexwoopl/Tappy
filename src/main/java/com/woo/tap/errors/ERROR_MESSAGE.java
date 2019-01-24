package com.woo.tap.errors;

/**
 * Our error message enum
 *
 * So that the error messages stay consistent.
 * A config file might also suit (giving us the ability to change error messages with deploying the app again but I'm okay with an enum in this case).
 */
public enum ERROR_MESSAGE {
    NO_ARGUMENTS("No input file given, please provide one."),
    MULTIPLE_ARGUMENTS("Multiple arguments were provided, please provide only one.");



    private final String text;

    ERROR_MESSAGE(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
