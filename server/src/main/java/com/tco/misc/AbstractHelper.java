package com.tco.misc;

public abstract class AbstractHelper {
    
    public long computeDistance(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        return Math.round(earthRadius*computeCentralAngle(from, to));
    }

    public abstract double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to);
}
