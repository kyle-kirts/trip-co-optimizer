package com.tco.misc;

public class OptimizerFactory{
    
    public TourOptimizer get(Integer N, Double response) {

        return new NoOptimizer();
    }

}