package com.defisExpertus.server.controller;

public class WeatherNotFoundException  extends RuntimeException {

    WeatherNotFoundException(Long id) {
        super("Could not find weather " + id);
    }
}