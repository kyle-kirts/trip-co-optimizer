package com.tco.misc;

public class HaversineCalculator implements DistanceCalculator {
    private final HaversineHelper helper;

    public HaversineCalculator() {
        this.helper = new HaversineHelper();
    }
    
    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        return helper.computeDistance(from, to, earthRadius);
    }

}