package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tco.requests.Place;
import com.tco.requests.Places;

public class TestNoOptimizer {
    
    @Test
    @DisplayName("kyle-kirts: Default Test")
    public void testNoOpt() {
        NoOptimizer noOpt = new NoOptimizer();
        assertNotNull(noOpt);
    }

    @Test
    @DisplayName("jsibold: NoOptimizer construct() returns same Places reference")
    public void testConstructReturnsSamePlaces() {
        NoOptimizer noOpt = new NoOptimizer();
        Places places = new Places();
        places.add(new Place("39.0", "-104.0")); 

        Places result = noOpt.construct(places, 6371.0, "cosines", 1.0);
        assertSame(places, result);
    }
}
