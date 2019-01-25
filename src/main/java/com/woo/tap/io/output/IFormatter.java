package com.woo.tap.io.output;

import java.util.List;

public interface IFormatter<T> {
    List<List<String>> format(List<T> unformattedTrips);
}
