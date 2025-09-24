package com.tco.requests;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String,String> implements GeographicCoordinate {

    public Place(){}

    public Place(String lat, String lon){
        Double latitude = Double.parseDouble(lat);
        Double longitude = Double.parseDouble(lon);
        this.put(lat,lon);
    }

    @Override
    public double lonRadians(){
        // QUESTION - What is the defualt return if this value is not set??
        return 0.0; // TEMP
    }

    @Override
    public double latRadians(){
        // TODO
        return 0.0; // TEMP
    }

}