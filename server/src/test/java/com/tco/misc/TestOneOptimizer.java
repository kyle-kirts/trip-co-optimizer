package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestOneOptimizer {
    
    @Test
    @DisplayName("kyle-kirts: Default Test")
    public void testNoOpt() {
        OneOptimizer oneOpt = new OneOptimizer();
        assertNotNull(oneOpt);
    }
}
