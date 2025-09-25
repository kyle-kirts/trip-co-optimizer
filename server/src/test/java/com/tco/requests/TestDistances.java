package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDistances {
    


    @Test
    @DisplayName("kyle-kirts: Validating an empty Distances returns 0L")
    public void testEmptyDistances() {
        assertTrue(new Distances().total() == 0L);
    }
}
