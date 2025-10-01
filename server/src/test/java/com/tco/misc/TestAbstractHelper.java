package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAbstractHelper {
    
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

