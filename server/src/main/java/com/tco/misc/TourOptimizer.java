package com.tco.misc;

import java.util.Arrays;
import java.util.Random;

public abstract class TourOptimizer {
    protected boolean[] visited; 
    protected int[] order;
    protected long[][] distances;
    protected long currentTotalDistance;
    protected double response;
    protected int[] defaultOrder;
    private double RETURN_TIME_CUTOFF = 0.25; // 0.05

    public TourOptimizer() {}

    public Places construct(Places places, double radius, String formula, Double response) {
        if (response < RETURN_TIME_CUTOFF) return places;
        initialize(places, radius, formula, response);
        int[] tour = findBestNearestNeighborTour(places);
        Places nearestNeighbor = new Places();

        for (int i = 0; i < tour.length; i++) {
            nearestNeighbor.add(places.get(tour[i]));
        }
        
        return nearestNeighbor;
    }

    public void improve() {};

    public void initialize(Places places, double radius, String formula, Double response) {
        visited = new boolean[places.size()];
        Arrays.fill(visited, false);
        order = new int[places.size()];
        currentTotalDistance = 0;
        this.response = response.doubleValue();
        initializeDistances(places, radius, formula);
        for (int i = 0; i < places.size(); i++) {
            defaultOrder[i] = i;
        }
    }

    public int[] findBestNearestNeighborTour(Places places) {
        long currentBest = Long.MAX_VALUE;
        int[] bestOrder = new int[places.size()];
        // double previousTime = getSeconds();
        for (int i = 0; i < 1 /*places.size()*/; i++) {
            int[] currentOrder = createRoute(places, places.get(i));
            if (currentTotalDistance < currentBest) {
                bestOrder = currentOrder;
                currentBest = currentTotalDistance;
            }

            // response -= getSeconds() - previousTime;
            // previousTime = getSeconds();
            if (response <= RETURN_TIME_CUTOFF) break;
        }
        
        return bestOrder;
    }

    public void initializeDistances(Places places, double radius, String formula) {
        int numberOfPlaces = places.size();
        distances = new long[numberOfPlaces][numberOfPlaces];
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        
        for (int i = 0; i < numberOfPlaces; i++) {
            GeographicCoordinate currentPlace = places.get(i);
            for (int j = i + 1; j < numberOfPlaces; j++) {
                GeographicCoordinate otherPlace = places.get(j);
                long distance = calculator.between(currentPlace, otherPlace, radius);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    public int[] createRoute(Places places, Place start) {
        Arrays.fill(order, 0);
        Arrays.fill(visited, false);
        
        int index = 0;
        int nextPlace = places.getPlace(start);
        this.order[index] = nextPlace;
        this.visited[nextPlace] = true;
        index++;
        
        int visitedCount = 1;
        double previousTime = getSeconds();

        while (visitedCount < visited.length) {
            nextPlace = closest(nextPlace);
            this.order[index] = nextPlace;
            this.visited[nextPlace] = true;
            index++;
            visitedCount++;

            response -= getSeconds() - previousTime;
            previousTime = getSeconds();
            if (response <= RETURN_TIME_CUTOFF) return defaultOrder;
        }        
        
        return order;
    }


    public int closest(int next) {
        int best = -1;
        Long minDistance = Long.MAX_VALUE;
        
        for (int i = 0; i < visited.length; i++) {
            Long distance = distances[next][i];
            boolean isValidPlace = (!visited[i]) && (i != next);
            boolean isLessThan = distances[next][i] < minDistance;
            boolean isEqualTo = distances[next][i] == minDistance;

            if (isValidPlace && isLessThan) {
                best = i;
                minDistance = distance;
            }
            
            if (isValidPlace && isEqualTo) best = pickRandom(i, best);
        }
        
        return best;
    }

    public int pickRandom(int i, int best) {
        Random random = new Random();
        int nonse = random.nextInt();
        if (nonse % 2 == 0) best = i;
        return best;
    }

    protected double getSeconds() {
        return (double) (System.currentTimeMillis() / 1000);
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

    public boolean[] getVisited() {
        return visited;
    }
    public long getCurrentTotal() {
        return currentTotalDistance;
    }
    public int[] getOrder() {
        return order;
    }
}
