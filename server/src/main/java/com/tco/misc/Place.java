package com.tco.misc;

import java.util.HashMap;
import java.lang.Math;
import com.tco.misc.GeographicCoordinate;

public class Place extends HashMap<String,String> implements GeographicCoordinate {

    public Place(){}

    public Place(String lat, String lon){
        this.put("latitude", lat);
        this.put("longitude", lon);
    }

    @Override
    public double latRadians(){
        double latRad = 0.0;
        if(this.get("latitude") != null){
            Double latDegrees = Double.parseDouble(this.get("latitude"));
            latRad = latDegrees * Math.PI;
            latRad = latRad / 180;
        }
        return latRad;
    }

    @Override
    public double lonRadians(){
        // QUESTION - What is the defualt return if this value is not set??
        double lonRad = 0.0;
        if(this.get("longitude") != null){
            Double lonDegrees = Double.parseDouble(this.get("longitude"));
            lonRad = lonDegrees * Math.PI;
            lonRad = lonRad / 180;
        }
        return lonRad;
    }

    public boolean equals(Place against)
    {
        if(this.latRadians() == against.latRadians())
        {
            if(this.lonRadians() == against.lonRadians())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        return this.get("latitude") + ", " + this.get("longitude");
    }
}
