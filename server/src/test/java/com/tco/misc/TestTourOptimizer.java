package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class TestTourOptimizer {

    @Test
    @DisplayName("jsibold: TourOptimizer subclass can call construct() and return Places")
    public void testConstructReturnsPlaces() {
        TourOptimizer optimizer = new TourOptimizer() {};

        Places inputPlaces = new Places();
        inputPlaces.add(new Place("40.0", "-105.0"));

        Places result = optimizer.construct(inputPlaces, 6371.0, "vincenty", 1.0);
        //assertSame(inputPlaces, result);
    }

    @Test
    @DisplayName("vercauteren: Checking initialized values")
    public void testInitialize(){
        TourOptimizer optimizer = new TourOptimizer() {};
        Places places = new Places();
        places.add(new Place("0.0", "0.0"));
        places.add(new Place("1.0", "1.0"));

        optimizer.initialize(places, 111, "vincenty", 1.0);
        assertTrue(optimizer.getVisited().length == 2);
        assertTrue(optimizer.getOrder().length == 2);
        assertTrue(optimizer.getCurrentTotal() == 0);
    }

    @Test
    @DisplayName("vercauteren: Checking createRoute")
    public void testCreateRoute(){
        TourOptimizer optimizer = new TourOptimizer() {};

        Place start = new Place("0.0", "0.0");
        
        Places places = new Places();
        places.add(new Place("2.0", "2.0"));
        places.add(new Place("1.0", "1.0"));
        places.add(new Place("3.0", "3.0"));
        places.add(start);

        optimizer.initialize(places, 1111111, "vincenty", 10000.0);

        int[] expected = {3, 1, 0, 2}; 
        int[] calculated = optimizer.createRoute(places, start);
        assertArrayEquals(expected, calculated);
    }

    @Test
    @DisplayName("vercauteren: checking nearestNeighbor")
    public void testNearestNeighbor() {
        TourOptimizer optimizer = new TourOptimizer() {};

        Place start = new Place("0.0", "0.0");
        
        Places places = new Places();
        places.add(new Place("2.0", "2.0"));
        places.add(new Place("1.0", "1.0"));
        places.add(new Place("3.0", "3.0"));
        places.add(start);

        optimizer.initialize(places, 1111111, "vincenty", 10000.0);

        int[] expected = {3, 1, 0, 2}; 
        int[] calculated = optimizer.findBestNearestNeighborTour(places);
        assertArrayEquals(expected, calculated);
    }

    @Test
    @DisplayName("kyle-kirts: Check closest returns expected from 2 unvisited")
    public void testClosestTwoOptions() {
        TourOptimizer optimizer = new TourOptimizer() {};
        boolean[] visited = {true, false, false};
        long[][] distances = {{0L, 1L, 100L}, 
                              {1L, 0L, 99L}, 
                              {100L, 99L, 0L}};
        
        optimizer.setVisited(visited);
        optimizer.setDistances(distances);

        assertEquals(1, optimizer.closest(0));
    }

    @Test
    @DisplayName("kyle-kirts: Check closest returns expected from 5 unvisited")
    public void testClosestFiveOptions() {
        TourOptimizer optimizer = new TourOptimizer() {};
        boolean[] visited = {true, false, false, true, false, false, false};
        long[][] distances = {{0, 10, 20, 1, 30, 40, 50},
                              {10, 0, 10, 9, 20, 30, 40},
                              {20, 10, 0, 19, 10, 20, 30},
                              {1, 9, 19, 0, 29, 39, 49},
                              {30, 20, 10, 29, 0, 10, 20},
                              {40, 30, 20, 39, 10, 0, 10},
                              {50, 40, 30, 49, 20, 10, 0}};

        optimizer.setVisited(visited);
        optimizer.setDistances(distances);
        assertEquals(1, optimizer.closest(3));
    }

    @Test
    @DisplayName("kyle-kirts: Check last two unvisited ")
    public void testClosestLastTwoUnvisited() {
        TourOptimizer optimizer = new TourOptimizer() {};
        boolean[] visited = {true, true, true, true, true, false, false};
        long[][] distances = {{0, 10, 20, 1, 30, 40, 50},
                              {10, 0, 10, 9, 20, 30, 40},
                              {20, 10, 0, 19, 10, 20, 30},
                              {1, 9, 19, 0, 29, 39, 49},
                              {30, 20, 10, 29, 0, 10, 20},
                              {40, 30, 20, 39, 10, 0, 10},
                              {50, 40, 30, 49, 20, 10, 0}};

        optimizer.setVisited(visited);
        optimizer.setDistances(distances);
        assertEquals(5, optimizer.closest(4));
    }

    @Test
    @DisplayName("kyle-kirts: Check last unvisited")
    public void testClosestLastUnvisited() {
        TourOptimizer optimizer = new TourOptimizer() {};
        boolean[] visited = {true, true, true, true, true, true, false};
        long[][] distances = {{0, 10, 20, 1, 30, 40, 50},
                              {10, 0, 10, 9, 20, 30, 40},
                              {20, 10, 0, 19, 10, 20, 30},
                              {1, 9, 19, 0, 29, 39, 49},
                              {30, 20, 10, 29, 0, 10, 20},
                              {40, 30, 20, 39, 10, 0, 10},
                              {50, 40, 30, 49, 20, 10, 0}};

        optimizer.setVisited(visited);
        optimizer.setDistances(distances);
        assertEquals(6, optimizer.closest(5));
    }

    @Test
    @DisplayName("luzovich: getSeconds() is accurate")
    public void testAccuracyOfGetSeconds() {
        TourOptimizer optimizer = new TourOptimizer() {};
        double before = System.currentTimeMillis() / 1000;
        double during = optimizer.getSeconds();
        double after = System.currentTimeMillis() / 1000;
        assertTrue(before <= during && during <= after);
    }
    
    @Test
    @DisplayName("luzovich: Verify default 0 given for distances array, original from kyle-kirts")
    public void testInitializeDistancesZeros() {
        TourOptimizer optimizer = new TourOptimizer() {};

        Place p1 = new Place("0.0", "0.0");
        Place p2 = new Place("0.0", "0.0");
        
        Places places = new Places();
        places.add(p1);
        places.add(p2);

        optimizer.initialize(places, 0.0, "vincenty", 1.0);
        optimizer.initializeDistancesForPlace(places, p1);
        
        long[][] expected = {{0L, 0L}, {0L, 0L}};
        assertArrayEquals(expected, optimizer.getDistances());
    }

    @Test
    @DisplayName("luzovich: Check two different places give correct matrix, original from kyle-kirts")
    public void testInitializeDistancesTwoPlaces() {
        TourOptimizer optimizer = new TourOptimizer() {};
    
        Place p1 = new Place("0.0", "0.0");
        Place p2 = new Place("0.0001", "-0.0001");

        Places places = new Places();
        places.add(p1);
        places.add(p2);

        optimizer.initialize(places, 7777777.0, "vincenty", 1.0);
        optimizer.initializeDistancesForPlace(places, p1);

        long[][] expected = {{0L,19L}, {19L, 0L}};
        assertArrayEquals(expected, optimizer.getDistances());
    }

    @Test
    @DisplayName("luzovich: Pick random on best & random index")
    public void testPickRandomBothCases() {
        TourOptimizer optimizer = new TourOptimizer() {};

        boolean keepTryingType1 = true;
        boolean keepTryingType2 = true;
        while (keepTryingType1 || keepTryingType2) {
            if (optimizer.pickRandom(0, 1) == 0) keepTryingType1 = false;
            if (optimizer.pickRandom(0, 1) == 1) keepTryingType2 = false;
        }
        
        return;
    }

    @Test
    @DisplayName("luzovich: Calling improve() doesn't fail")
    public void testCallImproveDoesntThrowException() throws Exception {
        TourOptimizer optimizer = new TourOptimizer() {};

        assertDoesNotThrow(() -> optimizer.improve());
    }
}
