package com.tco.misc;

public abstract class AbstractHelper {
    
    public long computeDistance(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        double centralAngle = computeCentralAngle(from, to);
        double distance = computeDistance(earthRadius, centralAngle);
        return Math.round(distance);
    }
    protected double computeDistance(double earthRadius, double centralAngle) {
        return earthRadius * centralAngle;
    }

    protected abstract double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to);

}
