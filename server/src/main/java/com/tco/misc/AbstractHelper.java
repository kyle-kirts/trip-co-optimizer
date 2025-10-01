package com.tco.misc;

public abstract class AbstractHelper {
    
    public long computeDistance(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        double centralAngle = calculateCentralAngle(from, to);
        double distance = calculateDistance(earthRadius, centralAngle);
        return Math.round(distance);
    }
    protected double calculateDistance(double earthRadius, double centralAngle) {
        return earthRadius * centralAngle;
    }

    protected abstract double calculateCentralAngle(GeographicCoordinate from, GeographicCoordinate to);

}
