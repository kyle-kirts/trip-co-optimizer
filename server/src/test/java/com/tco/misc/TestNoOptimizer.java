package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestNoOptimizer {
    
    @Test
    @DisplayName("kyle-kirts: Default Test")
    public void testNoOpt() {
        NoOptimizer noOpt = new NoOptimizer();
        assertNotNull(noOpt);
    }
}
