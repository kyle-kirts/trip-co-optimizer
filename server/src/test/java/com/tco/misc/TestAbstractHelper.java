package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAbstractHelper {
    
    @Test
    @DisplayName("kyle-kirts: Validate actual vs expected return value")
    public void testComputeDistance() {
        AbstractHelper calculatorhelper = new AbstractHelper() {
            @Override
            protected double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to) {
                return 0.0; // dummy implementation
            }
        };

        double radius = 1000;
        double centralAngle = 1.9349003703390644;
        double expected = 1934.9003703390645;

        assertEquals(expected, calculatorhelper.computeDistance(radius, centralAngle));
    }
}
