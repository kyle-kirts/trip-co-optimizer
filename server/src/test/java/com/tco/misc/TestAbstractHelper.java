package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAbstractHelper {
    
    @Test
    @DisplayName("kyle-kirts: Validate actual vs expected return value")
    public void testCalculateDistance() {
        AbstractHelper calculatorhelper = new AbstractHelper() {};
        double earthRadius = 0;

        assertEquals(calculatorhelper.computeDistance(null, null, earthRadius), 0);

    }

}

