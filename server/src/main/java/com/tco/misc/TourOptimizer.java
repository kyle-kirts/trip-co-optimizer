package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {
    protected Places places;
    protected boolean[] unvisited; 
    protected int[] order;
    protected long[][] distances;
    protected long duration; 

    public TourOptimizer() {
    }

    private initialize(Places locations) 
    {
        places = locations;
        unvisited = new boolean[locations.size()];
        Arrays.fill(univisited,true);
        order = new int[locations.size()];
        duration = 0;
        // populateDistances() or something.
    }

    public Places construct(Places places, double radius, String formula, Double response) {
        initialize(places);
        return places;
    }

    public void improve() {
    };
}
