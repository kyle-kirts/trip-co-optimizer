package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTourOptimizer {

    @Test
    @DisplayName("jsibold: TourOptimizer subclass can call construct() and return Places")
    public void testConstructReturnsPlaces() {
        TourOptimizer optimizer = new TourOptimizer() {};

        Places inputPlaces = new Places();
        inputPlaces.add(new Place("40.0", "-105.0"));

        Places result = optimizer.construct(inputPlaces, 6371.0, "vincenty", 1.0);
        assertSame(inputPlaces, result);
    }

}
