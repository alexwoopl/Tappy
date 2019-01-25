package com.woo.tap.io.output;


import java.util.List;

public interface IOutputHandler {
    void handle(List<List<String>> trips);
}
