package com.tco.misc;

import java.util.Arrays;
import java.util.Random;

public abstract class TourOptimizer {
    protected boolean[] visited; 
    protected int[] order;
    protected long[][] distances;
    protected long currentTotalDistance; 

    public TourOptimizer() {
    }

    private void initialize(Places places, double radius, String formula) 
    {
        visited = new boolean[places.size()];
        Arrays.fill(visited,false);
        order = new int[places.size()];
        currentTotalDistance = 0;
        initializeDistances(places, radius, formula);
    }

    public Places construct(Places places, double radius, String formula, Double response) {
        initialize(places, radius, formula);
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

    public int closest(int next) {
        int best = Integer.MAX_VALUE;
        Long minDistance = null;
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false && i != next) {
                Long distance = distances[next][i];
                if (minDistance == null || distance < minDistance) {
                    minDistance = distance;
                    best = i;
                } 
                if (distance == minDistance) {
                    Random random = new Random();
                    int nonse = random.nextInt();
                    if (nonse % 2 == 0) { best = i;}
                }
                
            }
        }
        
        return best;
    }

    // Methods used for testing
    public long[][] getDistances() {
        return distances;
    }

    public void setDistances(long[][] testDistances) {
        this.distances = testDistances;
    }

    public void setVisited(boolean[] testVisited) {
        this.visited = testVisited;
    }
}
