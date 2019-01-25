package com.woo.tap;

import java.io.IOException;
import java.text.ParseException;

public interface ITapToTripCoordinator {
    void createTripCSV(String filename) throws IOException, ParseException;
}
