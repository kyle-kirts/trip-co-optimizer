package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String,String> implements GeographicCoordinate {

    @Override
    public double lonRadians(){
        // TODO
        return 0.0; // TEMP
    }

    @Override
    public double latRadians(){
        // TODO
        return 0.0; // TEMP
    }

}