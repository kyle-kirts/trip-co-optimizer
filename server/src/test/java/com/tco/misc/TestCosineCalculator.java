package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestCosineCalculator {

    @Test
    @DisplayName("Constructor creates a non-null CosineCalculator")
    public void testConstructorCreatesObject() {
        CosineCalculator calc = new CosineCalculator();
        assertNotNull(calc, "CosineCalculator instance should not be null");
    }

    @Test
    @DisplayName("Constructor initializes helper field")
    public void testHelperInitialized() throws Exception {
        CosineCalculator calc = new CosineCalculator();

        Field helperField = CosineCalculator.class.getDeclaredField("helper");
        helperField.setAccessible(true);
        Object helperValue = helperField.get(calc);

        assertNotNull(helperValue, "CosineCalculator.helper should be initialized");
        assertTrue(helperValue instanceof CosineHelper, "CosineCalculator.helper should be a CosineHelper");
    }
}
