package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAbstractHelper {
    
    @Test
    @DisplayName("kyle-kirts: Validate actual vs expected return value")
    public void testCalculateDistance() {
        AbstractHelper calculatorhelper = new AbstractHelper() {};

        double radius = 1000;
        double centralAngle = 1.9349003703390644;
        double actual = 1934.9003703390645;

        assertEquals(calculatorhelper.calculateDistance(radius, centralAngle), actual);

    }
}
