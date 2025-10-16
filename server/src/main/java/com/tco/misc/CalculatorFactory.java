package com.tco.misc;

import java.util.List;
import java.util.Arrays;

public class CalculatorFactory {

    public static List<String> getSupportedFormulae() {
        
        return Arrays.asList("vincenty", "haversine", "cosines");
    }

    public static DistanceCalculator getCalculator(String formula) {

        if (formula == null) return new VincentyCalculator();
        
        switch (formula.toLowerCase()) {
            case "vincenty":
                return new VincentyCalculator();
            case "haversine":
                return new HaversineCalculator();
            case "cosines":
                return new CosinesCalculator();
            default:
                return new VincentyCalculator();
        }
    }
}
