package com.tco.misc;

import com.tco.requests.Places;
import java.util.Arrays;

public abstract class TourOptimizer {
    protected boolean[] unvisited; 
    protected int[] order;
    protected long[][] distances;
    protected long currentTotalDistances; 

    public TourOptimizer() {
    }

    public Places construct(Places places, double radius, String formula, Double response) {
        initialize(places, radius, formula);
        return places;
    }

    public void improve() {
    };

    private void initialize(Places places, double radius, String formula) 
    {
        unvisited = new boolean[places.size()];
        Arrays.fill(unvisited,true);
        order = new int[places.size()];
        currentTotalDistances = 0;
        initializeDistances(places, currentTotalDistances, formula);
    }

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
