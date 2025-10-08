package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestCosinesCalculator {

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
}
