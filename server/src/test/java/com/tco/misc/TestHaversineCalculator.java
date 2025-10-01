package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHaversineCalculator {
    

    @Test
    @DisplayName("kyle-kirts: Test between() equates to 0L")
    public void testBetween() {
        HaversineCalculator haversine = new HaversineCalculator();
        GeographicCoordinate from = null;
        GeographicCoordinate to = null;
        double earthRadius = 0;
        assertEquals(0L , haversine.between(from, to, earthRadius));
    }
}
