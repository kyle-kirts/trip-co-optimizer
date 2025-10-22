package com.tco.misc;

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

      @Test
    @DisplayName("kyle-kirts: Verify default 0 given for distances array")
    public void testInitializeDistancesZeros() {
        TourOptimizer optimizer = new TourOptimizer() {};
        Places places = new Places();
        
        places.add(new Place("0.0", "0.0"));
        places.add(new Place("0.0", "0.0"));

        long[][] expected = {{0L, 0L}, {0L, 0L}};
        optimizer.initializeDistances(places, 0.0, "vincenty");
        assertArrayEquals(expected, optimizer.getDistances());
    }

    @Test
    @DisplayName("kyle-kirts: Check two different places give correct matrix")
    public void testInitializeDistancesTwoPlaces() {
        TourOptimizer optimizer = new TourOptimizer() {};
        Places places = new Places();

        places.add(new Place("0.0", "0.0"));
        places.add(new Place("0.0001","-0.0001"));

        long[][] expected = {{0L,19L}, {19L,0L}};
        optimizer.initializeDistances(places, 7777777.0, "vincenty");
        assertArrayEquals(expected, optimizer.getDistances());
    }
}
