package com.tco.misc;

public class OptimizerFactory{
    
    public static TourOptimizer get(Integer N, Double response) {
        TourOptimizer optimizer;
        
        if ((N == null || N < 4) || response == 0) {
            optimizer = new NoOptimizer();
        } else {
            optimizer = new OneOptimizer();
        }

        return optimizer;
    }
}