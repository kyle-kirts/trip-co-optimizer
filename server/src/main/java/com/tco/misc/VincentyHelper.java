package com.tco.misc;

public class VincentyHelper{

    public double computeX(GeographicCoordinate from, GeographicCoordinate to){
        double lat1 = from.latRadians();
        double lat2 = to.latRadians();
        double diffLon = to.lonRadians() - from.lonRadians();
        return Math.sin(lat1) *Math.sin(lat2) +Math.cos(lat1) * Math.cos(lat2) * Math.cos(diffLon);
        
    }
    public double computeY(GeographicCoordinate from, GeographicCoordinate to){
        return 0D;
    }}
