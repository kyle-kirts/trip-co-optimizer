package com.tco.misc;

public abstract class AbstractHelper {
    
    public double computeDistance(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        return earthRadius*computeCentralAngle(from, to);
    }

    public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to) {
        return 0.0;
    }
}
