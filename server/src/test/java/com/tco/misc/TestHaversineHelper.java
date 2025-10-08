package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestHaversineHelper {
    private static HaversineHelper helper;

    public GeographicCoordinate coordinateMaker(double latitude, double longitude) {
        return new GeographicCoordinate() {
            public double latRadians() { return latitude; }
            public double lonRadians() { return longitude; }
        };
    }
    @BeforeEach
    @DisplayName("Jsibold: set up HaversineHelper for each test")
    public void createHelper() {
        helper = new HaversineHelper();
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a double")
    public void testComputeAngleReturn(){

        HaversineHelper hh = new HaversineHelper();
        GeographicCoordinate from = coordinateMaker(0.0, 0.0);
        GeographicCoordinate to = coordinateMaker(1.0, 1.0);

        assertDoesNotThrow(() -> {
            String theta = Double.toString(hh.computeCentralAngle(to, from));
        });
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a correct angle for (0,0) to (1,1)")
    public void testComputeAngleSmall(){

        HaversineHelper hh = new HaversineHelper();
        GeographicCoordinate from = coordinateMaker(0.0, 0.0);
        GeographicCoordinate to = coordinateMaker(Math.toRadians(1.0), Math.toRadians(1.0));

        assertEquals(0.024682056391766437, hh.computeCentralAngle(to, from));
    }

    @Test
    @DisplayName("vercauteren: computeCentralAngle returns a correct angle for (0,0) to (89.0,89.9)")
    public void testComputeAngleLarge(){

        HaversineHelper hh = new HaversineHelper();
        GeographicCoordinate from = coordinateMaker(Math.toRadians(0), Math.toRadians(0));
        GeographicCoordinate to = coordinateMaker(Math.toRadians(89.9), Math.toRadians(89.9));

        assertEquals(1.57079328062379208752, hh.computeCentralAngle(to, from));
    }
}
