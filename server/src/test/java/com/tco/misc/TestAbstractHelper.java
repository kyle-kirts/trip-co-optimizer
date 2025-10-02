package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAbstractHelper {
    
    @Test
    @DisplayName("kyle-kirts: Validate default constructor exists after creation")
    public void testConstructor() {
        HaversineCalculator haversine = new HaversineCalculator();

        assertNotNull(haversine);
    }

    @Test
    @DisplayName("kyle-kirts: Validate actual vs expected return value")
    public void testCalculateDistance() {
        AbstractHelper calculatorhelper = new AbstractHelper() {

            @Override
            public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to) {
                return 0.0;
            }};

        double earthRadius = 0;

        assertEquals(0L, calculatorhelper.computeDistance(null, null, earthRadius));
    }
}

