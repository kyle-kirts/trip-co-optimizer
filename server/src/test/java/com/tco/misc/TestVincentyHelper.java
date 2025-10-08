package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVincentyHelper {

    private static VincentyHelper helper;

    public GeographicCoordinate coordinateMaker(double latitude, double longitude) {
        return new GeographicCoordinate() {
            public double latRadians() { return latitude; }
            public double lonRadians() { return longitude; }
        };
    }

    @BeforeEach
    @DisplayName("jsibold: set up VincentyHelper for each test")
    public void createHelper() {
        helper = new VincentyHelper();
    }

    @Test
    @DisplayName("jsibold: Validate actual vs expected value")
    public void testComputeDenominator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate from = coordinateMaker(39.7392, -104.9903);
        GeographicCoordinate to = coordinateMaker(40.01499, -105.2705);

        double actual = helper.computeDenominator(from, to);
        assertEquals(0.9502513357399416, actual); 
    }
    @Test
    @DisplayName("jsibold: Validate actual vs expected value")
    public void testComputeNumerator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate from = coordinateMaker(39.7392, -104.9903);
        GeographicCoordinate to = coordinateMaker(40.01499, -105.2705);

        double actual = helper.computeNumerator(from, to);
        assertEquals(0.3114841872783539, actual); 
    }

    @Test
    @DisplayName("carter64: Central angle is zero when points are identical")
    public void testCentralAngleZero() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate same = coordinateMaker(Math.toRadians(40.0), Math.toRadians(-105.0));

        double angle = helper.computeCentralAngle(same, same);
        assertEquals(0.0, angle, 1e-12);
    }

    @Test
    @DisplayName("carter64: North Pole to Equator central angle ≈ 90° (π/2 radians)")
    public void testCentralAnglePoleToEquator() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate northPole = coordinateMaker(Math.toRadians(90.0), 0.0);
        GeographicCoordinate equator = coordinateMaker(0.0, 0.0);

        double angle = helper.computeCentralAngle(northPole, equator);
        assertEquals(Math.PI/2, angle, 1e-6);
    }
}
