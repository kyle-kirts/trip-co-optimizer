package com.tco.misc;

import java.util.List;
import java.util.Arrays;

public class CalculatorFactory {

   public static List<String> getSupportedFormulae() {
        
        return Arrays.asList("vincenty");
    }

    public static DistanceCalculator getCalculator(String formula) {
        // Temporary stub: return null or throw until calculators are implemented
        if (formula == null) {
            return null;
        }

        switch (formula.toLowerCase()) {
            case "vincenty":
                return new VincentyCalculator();
            default:
                return null;
        }
    }
    
}