package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestCalculatorFactory {

    @Test
    @DisplayName("luzovich: CalculatorFactory constructs without issue")
    public void testProperConstructOfCalculatorFactory() {
        assertNotNull(new CalculatorFactory());
    }
    
    @Test
    @DisplayName("luzovich: Supported formulae contains vincenty")
    public void testSupportedFormulaeContainsVincenty() {
        List<String> formulas = CalculatorFactory.getSupportedFormulae();
        assertTrue(formulas.contains("vincenty"));
    }

    @Test
    @DisplayName("luzovich: Supported formulae contains haversine")
    public void testSupportedFormulaeContainsHaversine() {
        List<String> formulas = CalculatorFactory.getSupportedFormulae();
        assertTrue(formulas.contains("haversine"));
    }
    
    @Test
    @DisplayName("luzovich: Supported formulae contains cosines")
    public void testSupportedFormulaeContainsCosines() {
        List<String> formulas = CalculatorFactory.getSupportedFormulae();
        assertTrue(formulas.contains("cosines"));
    }
    
    @Test
    @DisplayName("carter64: Factory returns a VincentyCalculator for 'vincenty'")
    public void testVincentyCalculator() {
        DistanceCalculator calc = CalculatorFactory.getCalculator("vincenty");
        assertNotNull(calc, "Expected a non-null calculator for vincenty");
        assertTrue(calc instanceof VincentyCalculator, "Expected a VincentyCalculator instance");
    }

    @Test
    @DisplayName("luzovich: Factory returns a HaversineCalculator for 'haversine'")
    public void testHaversineCalculator() {
        DistanceCalculator calc = CalculatorFactory.getCalculator("haversine");
        assertNotNull(calc, "Expected a non-null calculator for haversine");
        assertTrue(calc instanceof HaversineCalculator, "Expected a HaversineCalculator instance");
    }
    
    @Test
    @DisplayName("luzovich: Factory returns a CosinesCalculator for 'cosines'")
    public void testCosinesCalculator() {
        DistanceCalculator calc = CalculatorFactory.getCalculator("cosines");
        assertNotNull(calc, "Expected a non-null calculator for cosines");
        assertTrue(calc instanceof CosinesCalculator, "Expected a CosinesCalculator instance");
    }
    
    @Test
    @DisplayName("luzovich: Factory returns vincenty for empty (default) formula")
    public void testBlankFormulaYieldsVincenty() {
        DistanceCalculator calc = CalculatorFactory.getCalculator(null);
        assertTrue(calc instanceof VincentyCalculator, "Expected default formula to be of type VincentyCalculator");
    }
}
