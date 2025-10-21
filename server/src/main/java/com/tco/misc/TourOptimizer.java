package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {
    protected boolean[] visited;
    protected int[] order;
    protected long[][] distances;

    public TourOptimizer() {
    }

    public Places construct(Places places, double radius, String formula, Double response) {
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
