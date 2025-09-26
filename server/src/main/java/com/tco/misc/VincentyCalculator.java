package com.tco.misc;

public class VincentyCalculator implements DistanceCalculator {
    private final VincentyHelper helper;

    public VincentyCalculator() {
        this.helper = new VincentyHelper();
    }

    @Override
    public long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        double centralAngle = helper.computeCentralAngle(from, to);
        double distance = earthRadius * centralAngle;
        return Math.round(distance);
    }}
