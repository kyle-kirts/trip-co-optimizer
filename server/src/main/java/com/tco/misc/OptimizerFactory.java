package com.tco.misc;

public class OptimizerFactory{
    
    public static TourOptimizer get(Integer N, Double response) {
    if (N == null || N < 3) {
        return new NoOptimizer();
    } else {
        return new OneOptimizer();
    }
}
}
