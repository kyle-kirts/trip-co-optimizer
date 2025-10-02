package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import com.tco.misc.GeographicCoordinate;

public class TestHaversineHelper {

    private double toRadians(double deg)
    {
        return (deg * Math.PI) / 180;
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a double")
    public void testComputeAngleReturn(){

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return 0;}
            public double lonRadians(){return 0;}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return 1;}
            public double lonRadians(){return 1;}};

        assertDoesNotThrow(() -> {
            String theta = Double.toString(HaversineHelper.computeCentralAngle(to, from));
        });
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a correct angle for (0,0) to (1,1)")
    public void testComputeAngleSmall(){

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return toRadians(0);}
            public double lonRadians(){return toRadians(0);}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return toRadians(1);}
            public double lonRadians(){return toRadians(1);}};

        assertEquals(0.024682056391766437, HaversineHelper.computeCentralAngle(to, from));
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a correct angle for (0,0) to (89.0,89.9)")
    public void testComputeAngleLarge(){

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return toRadians(0);}
            public double lonRadians(){return toRadians(0);}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return toRadians(89.9);}
            public double lonRadians(){return toRadians(89.9);}};

        assertEquals(1.57079328062379208752, HaversineHelper.computeCentralAngle(to, from));
    }
}
