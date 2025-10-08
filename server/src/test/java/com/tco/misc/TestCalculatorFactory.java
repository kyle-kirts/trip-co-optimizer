package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestCalculatorFactory {

    @Test
    public void testSupportedFormulaeContainsVincenty() {
        List<String> formulas = CalculatorFactory.getSupportedFormulae();
        assertTrue(formulas.contains("vincenty"));
    }

    @Test
    @DisplayName("carter64: Factory returns a VincentyCalculator for 'vincenty'")
    public void testVincentyCalculator() {
        DistanceCalculator calc = CalculatorFactory.getCalculator("vincenty");
        assertNotNull(calc, "Expected a non-null calculator for vincenty");
        assertTrue(calc instanceof VincentyCalculator, "Expected a VincentyCalculator instance");
    }

    @Test
    @DisplayName("carter64: Factory returns null for unknown formula")
    public void testUnknownFormulaReturnsNull() {
        DistanceCalculator calc = CalculatorFactory.getCalculator("unknown");
        assertNull(calc, "Expected null for an unsupported formula");
    }
}
