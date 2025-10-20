package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {
    protected boolean[] visited;
    protected int[] order;
    protected double[][] distances;

    public TourOptimizer() {
        construct();
        improve();
    }

    public int construct() {
        Places places = new Places();
        return 0;
    }

    public void improve() {
    };
}
