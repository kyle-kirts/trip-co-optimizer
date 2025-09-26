package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestVincentyHelper {

    @Test
    @DisplayName("jsibold: Validate actual vs expected value")
    public void testComputeX() {
        VincentyHelper helper = new VincentyHelper();

        GeographicCoordinate from = new GeographicCoordinate(){
            public double latRadians(){return 39.7392;}
            public double lonRadians(){return -104.9903;}};

        GeographicCoordinate to = new GeographicCoordinate(){
            public double latRadians(){return 40.01499;}
            public double lonRadians(){return -105.2705;}};

        double actual = helper.computeX(from, to);
        assertEquals(0.9502513357399416, actual); 
    }}
