package com.tco.misc;

public class OptimizerFactory{
    
    public static TourOptimizer get(Integer N, Double response) {

        return new NoOptimizer();
    }
}
