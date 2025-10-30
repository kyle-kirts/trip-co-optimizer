package com.tco.misc;

public abstract class DataSource {

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) {
        return new Places();
    }
    public Distances distances(Place place, Places places) {
        return new Distances();
    }
}