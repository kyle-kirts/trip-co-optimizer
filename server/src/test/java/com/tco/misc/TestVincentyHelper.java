package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVincentyHelper {

    @Test
    @DisplayName("jsibold: Validate actual vs expected value")
    public void testComputeDenominator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return 39.7392;}
            public double lonRadians(){return -104.9903;}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return 40.01499;}
            public double lonRadians(){return -105.2705;}};

        double actual = helper.computeDenominator(from, to);
        assertEquals(0.9502513357399416, actual); 
    }
    @Test
    @DisplayName("jsibold: Validate actual vs expected value")
    public void testComputeNumerator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return 39.7392;}
            public double lonRadians(){return -104.9903;}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return 40.01499;}
            public double lonRadians(){return -105.2705;}};

        double actual = helper.computeNumerator(from, to);
        assertEquals(0.3114841872783539, actual); 
    }

    @Test
    @DisplayName("carter64: Central angle is zero when points are identical")
    public void testCentralAngleZero() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate same = new GeographicCoordinate(){
            public double latRadians(){return Math.toRadians(40.0);}
            public double lonRadians(){return Math.toRadians(-105.0);}};

        double angle = helper.computeCentralAngle(same, same);
        assertEquals(0.0, angle, 1e-12);
    }

    @Test
    @DisplayName("carter64: North Pole to Equator central angle ≈ 90° (π/2 radians)")
    public void testCentralAnglePoleToEquator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate northPole = new GeographicCoordinate(){
            public double latRadians(){return Math.toRadians(90.0);}
            public double lonRadians(){return 0.0;}};

        GeographicCoordinate equator = new GeographicCoordinate(){
            public double latRadians(){return 0.0;}
            public double lonRadians(){return 0.0;}};

        double angle = helper.computeCentralAngle(northPole, equator);
        assertEquals(Math.PI/2, angle, 1e-6);
    }
}
