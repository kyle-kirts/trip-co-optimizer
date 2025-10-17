package com.tco.misc;

import com.tco.requests.Places;

public abstract class TourOptimizer {

    public int TourOptimizer() {
        construct();
        improve();
        return 0;
    }

    public int construct() {
        Places places = new Places();
        return 0;
    }

    public void improve() {
    };
}