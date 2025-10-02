package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVincentyCalculator {

    @Test
    @DisplayName("jsibold: Validate VincentyCalculator.between() against known good distance")
    public void testBetweenWithKnownData() {
        VincentyCalculator calculator = new VincentyCalculator();

        double earthRadius = 10451000;

        GeographicCoordinate placeOne = new GeographicCoordinate() {
            public double latRadians() { return Math.toRadians(16.282); }
            public double lonRadians() { return Math.toRadians(-91.48675); }
        };

        GeographicCoordinate placeTwo = new GeographicCoordinate() {
            public double latRadians() { return Math.toRadians(-34.996496); }
            public double lonRadians() { return Math.toRadians(-64.967282); }
        };

        long expected = 10419952;

        long actual = calculator.between(placeOne, placeTwo, earthRadius);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("schlicting: VincentyCalculator.between() test")
    public void testBetweenWithAnotherKnownData() {
        VincentyCalculator calculator = new VincentyCalculator();

        calculator.between(new GeographicCoordinate() {
            public double latRadians() { return Math.toRadians(34.052235); }
            public double lonRadians() { return Math.toRadians(-118.243683); }
        }, new GeographicCoordinate() {
            public double latRadians() { return Math.toRadians(40.712776); }
            public double lonRadians() { return Math.toRadians(-74.005974); }
        }, 6371000);
    }
}
