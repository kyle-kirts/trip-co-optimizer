package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestCosinesCalculator {

    private GeographicCoordinate coordinateMaker(double latitude, double longitude) {
        return new GeographicCoordinate() {
            public double latRadians() {
                return latitude;
            }
            public double lonRadians() {
                return longitude;
            }
        };
    }

    @Test
    @DisplayName("carter64: Constructor creates a non-null CosinesCalculator")
    public void testConstructorCreatesObject() {
        CosinesCalculator calc = new CosinesCalculator();
        assertNotNull(calc, "CosinesCalculator instance should not be null");
    }

    @Test
    @DisplayName("carter64: Constructor initializes helper field")
    public void testHelperInitialized() throws Exception {
        CosinesCalculator calc = new CosinesCalculator();

        Field helperField = CosinesCalculator.class.getDeclaredField("helper");
        helperField.setAccessible(true);
        Object helperValue = helperField.get(calc);

        assertNotNull(helperValue, "CosinesCalculator.helper should be initialized");
        assertTrue(helperValue instanceof CosinesHelper, "CosinesCalculator.helper should be a CosinesHelper");
    }

    @Test
    @DisplayName("luzovich: between() is correct for some values")
    public void testBetweenForValues() {
        CosinesCalculator calc = new CosinesCalculator();

        GeographicCoordinate pointOne = coordinateMaker(20.7, -30.8);
        GeographicCoordinate pointTwo = coordinateMaker(-80.9, 94.3);

        assertEquals(127668364L, calc.between(pointOne, pointTwo, 123456789.0));
    }
}
