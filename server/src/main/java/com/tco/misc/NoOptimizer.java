package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourOptimizer {

    @Override
    public int construct() {
        Places places = new Places();
        return 0;
    }
}
