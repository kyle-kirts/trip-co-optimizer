package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {
    protected boolean[] visited;
    protected int[] order;
    protected double[][] distances;

    public TourOptimizer() {
    }

    public abstract Places construct(Places places, double radius, String formula, Places response);

    public void improve() {
    };
}
