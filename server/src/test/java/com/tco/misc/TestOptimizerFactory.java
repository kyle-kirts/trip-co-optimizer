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
    @DisplayName("kyle-kirts: Null Places gives NoOptimizer")
    public void testNullPlaces() {
        Double response = 0.5;
        Integer N = null;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 0 Response Time returns NoOptimizer")
    public void testZeroResponse() {
        Double response = 0.0;
        Integer N = 100;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 1 Places gives NoOptimizer")
    public void testOnePlace() {        
        Double response = .99;
        Integer N = 1;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 2 Places gives NoOptimizer")
    public void testTwoPlaces() {
        Double response = .99;
        Integer N = 2;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 3 Places gives NoOptimizer")
    public void testThreePlaces() {
        Double response = .99;
        Integer N = 3;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 4 Places gives OneOptimizer")
    public void testFourPlaces() {
        Double response = .99;
        Integer N = 4;

        TourOptimizer optimizer = OptimizerFactory.get(N, response);

        assertTrue(optimizer instanceof OneOptimizer);
    }
}