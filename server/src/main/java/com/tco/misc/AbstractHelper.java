package com.tco.misc;

public abstract class AbstractHelper {
    
    public double calculateDistance(double earthRadius, double centralAngle) {
        return earthRadius*centralAngle;
    }
}
