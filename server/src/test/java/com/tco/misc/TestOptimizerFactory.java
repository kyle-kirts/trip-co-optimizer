package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOptimizerFactory {
    
    @Test
    @DisplayName("vercauteren: base test for existence")
    public void testExist(){
        OptimizerFactory optFact = new OptimizerFactory();
        assertNotNull(optFact, "an object should exist.");
    }

    @Test
    @DisplayName("kyle-kirts: 0 Response Time returns NoOptimizer")
    public void testZeroResponse() {
        OptimizerFactory optimizer = new OptimizerFactory();
        Double response = 0.0;
        Integer N = null;
        TourOptimizer opt = optimizer.get(N, response);

        assertTrue(opt instanceof NoOptimizer);
    }
}