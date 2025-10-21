package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {
    protected boolean[] visited;
    protected int[] order;
    protected double[][] distances;

    public TourOptimizer() {
    }

    public Places construct(Places places, double radius, String formula, Double response) {
        return places;
    }

    public void improve() {
    };
}
