package com.tco.misc;

import java.util.List;
import java.util.Arrays;

public class CalculatorFactory {

   public static List<String> getSupportedFormulae() {
        
        return Arrays.asList("vincenty");
    }

    public static DistanceCalculator getCalculator(String formula) {
        // Temporary stub: return null or throw until calculators are implemented
        throw new UnsupportedOperationException("getCalculator not implemented yet");
    }
    
}