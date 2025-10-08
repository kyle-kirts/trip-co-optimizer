package com.tco.misc;

public class HaversineHelper extends AbstractCalculatorHelper{
  
  public double computeCentralAngle(GeographicCoordinate from, GeographicCoordinate to){
    double lat1 = from.latRadians();
    double lat2 = to.latRadians();
    double lon1 = from.lonRadians();
    double lon2 = to.lonRadians();
    double deltaPhi = lat2 - lat1;
    double deltaLambda = lon2 - lon1;
    double avgPhi = (lat2 + lat1) / 2;

    double theta = 0;
    theta = 2 * Math.asin(   Math.sqrt( Math.pow(Math.sin(deltaPhi/2),2) + ( Math.pow(Math.cos(avgPhi),2) - Math.pow(Math.sin(deltaPhi/2),2) ) * Math.pow(Math.sin(deltaLambda/2),2) )  );

    return theta;
  }
}
