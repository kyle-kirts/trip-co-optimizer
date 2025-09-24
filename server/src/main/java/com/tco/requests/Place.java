package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String,String> implements GeographicCoordinate {

    public Place(){}

    public Place(String lat, String lon){
        // TODO convert from string to double and put in hash map
    }

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