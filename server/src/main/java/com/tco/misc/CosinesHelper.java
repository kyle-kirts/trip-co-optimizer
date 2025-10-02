package com.tco.misc;

import java.lang.Math;

public class CosinesHelper {
    
    public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to) {

        double longitudeDelta = to.lonRadians() - from.lonRadians();

        return Math.acos(
            (Math.sin(from.latRadians()) * Math.sin(to.latRadians())) +
            (Math.cos(from.latRadians()) * Math.cos(to.latRadians()) * Math.cos(longitudeDelta))
        );
    }
}
