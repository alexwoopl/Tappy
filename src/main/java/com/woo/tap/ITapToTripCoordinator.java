package com.woo.tap;

import java.io.IOException;

public interface ITapToTripCoordinator {
    void createTripCSV(String filename) throws IOException;
}
