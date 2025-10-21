package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourOptimizer {

    @Override
    public Places construct(Places places, double radius, String formula, Double response) {
        return places;
    }
}
