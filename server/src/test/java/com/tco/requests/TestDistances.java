package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDistances {
    
    @Test
    @DisplayName("kyle-kirts: Validate sum of one long value")
    public void testTotalOneValue() {
        Distances distances = new Distances();
        distances.add(5L);

        assertEquals(5L, distances.total());
    }

    @Test
    @DisplayName("kyle-kirts: Validating an empty Distances returns 0L")
    public void testEmptyDistances() {
        assertTrue(new Distances().total() == 0L);
    }

    @Test 
    @DisplayName("kyle-kirts: Validate sum of multiple long values")
    public void testTotalMultipleValues() {
        Distances distances = new Distances();
        distances.add(5L);
        distances.add(3L);
        distances.add(2L);

        assertEquals(10L, distances.total());
    }
}
