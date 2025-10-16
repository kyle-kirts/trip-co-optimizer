package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestOptimizerFactory {
    
    @Test
    @DisplayName("vercauteren: base test for existence")
    public void testExist(){
        OptimizerFactory optFact = new OptimizerFactory();
        assertNotNull(optFact, "an object should exist.");
    }
}