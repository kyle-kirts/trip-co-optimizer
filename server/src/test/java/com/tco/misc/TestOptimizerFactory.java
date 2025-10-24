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
        OptimizerFactory optimizer = new OptimizerFactory();
        Double response = 0.99;
        Integer N = null;
        TourOptimizer opt = optimizer.get(N, response);

        assertTrue(opt instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 0 Response Time returns NoOptimizer")
    public void testZeroResponse() {
        OptimizerFactory optimizer = new OptimizerFactory();
        Double response = 0.0;
        Integer N = 100;
        TourOptimizer opt = optimizer.get(N, response);

        assertTrue(opt instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 1 Places gives NoOptimizer")
    public void testOnePlace() {
        OptimizerFactory optimizer = new OptimizerFactory();
        
        Double response = .99;
        Integer N = 1;
        NoOptimizer noOptimizer = new NoOptimizer();

        Object actualOptimizer = optimizer.get(N, response);

        assertTrue(actualOptimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 2 Places gives NoOptimizer")
    public void testTwoPlaces() {
        OptimizerFactory optimizer = new OptimizerFactory();
        
        Double response = .99;
        Integer N = 2;
        NoOptimizer noOptimizer = new NoOptimizer();

        Object actualOptimizer = optimizer.get(N, response);

        assertTrue(actualOptimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 3 Places gives NoOptimizer")
    public void testThreePlaces() {
        OptimizerFactory optimizer = new OptimizerFactory();
        
        Double response = .99;
        Integer N = 3;
        NoOptimizer noOptimizer = new NoOptimizer();

        Object actualOptimizer = optimizer.get(N, response);

        assertTrue(actualOptimizer instanceof NoOptimizer);
    }

    @Test
    @DisplayName("kyle-kirts: 4 Places gives OneOptimizer")
    public void testFourPlaces() {
        OptimizerFactory optimizer = new OptimizerFactory();
        
        Double response = .99;
        Integer N = 4;
        OneOptimizer oneOptimizer = new OneOptimizer();

        Object actualOptimizer = optimizer.get(N, response);

        assertTrue(actualOptimizer instanceof OneOptimizer);
    }
}