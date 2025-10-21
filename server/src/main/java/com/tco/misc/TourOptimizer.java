package com.tco.misc;

import com.tco.requests.Places;
import java.util.Arrays;

public abstract class TourOptimizer {
    protected Places places;
    protected boolean[] unvisited; 
    protected int[] order;
    protected long[][] distances;
    protected long duration; 

    public TourOptimizer() {
    }

    private void initialize(Places locations) 
    {
        places = locations;
        unvisited = new boolean[locations.size()];
        Arrays.fill(unvisited,true);
        order = new int[locations.size()];
        duration = 0;
        distances = new long[locations.size()][locations.size()]; //This line could either be here, or in the following method. shouldnt matter either way. 
        // populateDistances() or something.
    }

    public Places construct(Places places, double radius, String formula, Double response) {
        initialize(places);
        return places;
    }

    public void improve() {
    };

    public void initializeDistances(Places places, double radius, String formula) {
        int numberOfPlaces = places.size();
        distances = new long[numberOfPlaces][numberOfPlaces];
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        
        for (int i = 0; i < numberOfPlaces; i++) {
            GeographicCoordinate currentPlace = places.get(i);
            for (int j = i + 1; j < numberOfPlaces; j++){
                GeographicCoordinate otherPlace = places.get(j);
                long distance = calculator.between(currentPlace, otherPlace, radius);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    // Methods used for testing
    public long[][] getDistances() {
        return distances;
    }
}
