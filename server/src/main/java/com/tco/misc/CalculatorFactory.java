package com.tco.misc;

import java.util.List;
import java.util.Arrays;

public class CalculatorFactory {

    public static List<String> getSupportedFormulae() {
        
        return Arrays.asList("vincenty", "haversine", "cosines");
    }

    public static DistanceCalculator getCalculator(String formula) {

        DistanceCalculator calculator = null;

        if (formula == null || formula.equals("vincenty")) {

            calculator = new VincentyCalculator();
        } else if (formula.equals("haversine")) {

            calculator = new HaversineCalculator();
        } else if (formula.equals("cosines")) {

            calculator = new CosinesCalculator();
        }

        return calculator;
    }
}
