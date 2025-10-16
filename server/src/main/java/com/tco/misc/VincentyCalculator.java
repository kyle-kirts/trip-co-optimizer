package com.tco.misc;

public class VincentyCalculator implements DistanceCalculator {
    private final VincentyHelper helper;

    public VincentyCalculator() {
        this.helper = new VincentyHelper();
    }

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        return helper.computeDistance(from, to, earthRadius);
    }
}
