package com.tco.misc;

public class VincentyHelper{

    public double computeDenominator (GeographicCoordinate from, GeographicCoordinate to){
        double lat1 = from.latRadians();
        double lat2 = to.latRadians();
        double diffLon = to.lonRadians() - from.lonRadians();
        return Math.sin(lat1) *Math.sin(lat2) +Math.cos(lat1) * Math.cos(lat2) * Math.cos(diffLon);
        
    }
    public double computeNumerator (GeographicCoordinate from, GeographicCoordinate to){
    double lat1 = from.latRadians();
    double lat2 = to.latRadians();
    double diffLon = to.lonRadians() - from.lonRadians();

    return Math.sqrt(
        Math.pow(Math.cos(lat2) * Math.sin(diffLon), 2) +
        Math.pow(Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(diffLon), 2));
    }
}
