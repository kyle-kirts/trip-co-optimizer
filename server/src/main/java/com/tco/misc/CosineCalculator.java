package com.tco.misc;

public class CosineCalculator implements DistanceCalculator {
    private final CosineHelper helper;

    public CosineCalculator() {
        this.helper = new CosineHelper();
    }

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        return 0l;
    }
}
