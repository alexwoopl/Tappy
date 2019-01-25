package com.woo.tap.io.output;


import java.io.IOException;
import java.util.List;

public interface IOutputHandler {
    void handle(List<List<String>> trips) throws IOException;
}
