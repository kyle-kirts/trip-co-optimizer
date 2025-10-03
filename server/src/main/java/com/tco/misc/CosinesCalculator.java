package com.tco.misc;

public class CosinesCalculator implements DistanceCalculator {
    private final CosinesHelper helper;

    public CosinesCalculator() {
        this.helper = new CosinesHelper();
    }

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        return helper.computeDistance(from, to, earthRadius);
    }
}
