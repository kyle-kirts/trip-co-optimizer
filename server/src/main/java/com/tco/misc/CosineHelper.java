package com.tco.misc;

<<<<<<< HEAD
import java.lang.Math;

public class CosineHelper {
    
    public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to) {

        double longitudeDelta = to.lonRadians() - from.lonRadians();

        return Math.acos(
            (Math.sin(from.latRadians()) * Math.sin(to.latRadians())) +
            (Math.cos(from.latRadians()) * Math.cos(to.latRadians()) * Math.cos(longitudeDelta))
        );
=======
public class CosineHelper extends AbstractHelper{
    @Override
    public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to)
    {
        return 0.0;
>>>>>>> main
    }
}
