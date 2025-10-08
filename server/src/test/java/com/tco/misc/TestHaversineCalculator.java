package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHaversineCalculator {
    
    @Test
    @DisplayName("kyle-kirts: Test between() equates to 0L")
    public void testBetween() {
        HaversineCalculator haversine = new HaversineCalculator();
        GeographicCoordinate testCord = new GeographicCoordinate() {
            @Override
            public double latRadians() {
                return 0.0;
            }
            @Override
            public double lonRadians() {
                return 0.0;
            }
            
        };
        double earthRadius = 0;
        assertEquals(0L , haversine.between(testCord, testCord, earthRadius));
    }
}
