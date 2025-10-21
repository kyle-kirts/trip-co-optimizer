package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourOptimizer {

    @Override
    public Places construct(Places places, double radius, String formula, Places response) {
        int n = places.size();
        visited = new boolean[n];
        order = new int[n];
        distances = new double[n][n];
        return places;
    }
}
