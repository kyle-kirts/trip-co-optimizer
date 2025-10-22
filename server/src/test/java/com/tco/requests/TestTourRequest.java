package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTourRequest {

    @Test
    @DisplayName("vercauteren: base test for existence")
    public void testExists(){
        TourRequest tour = new TourRequest();
        assertNotNull(tour, "an object should exist.");
    }

    @Test
    @DisplayName("luzovich: Case constructor sets values")
    public void testBaseConstructor() {
        TourRequest tour = new TourRequest();
        assertEquals(6371.0, tour.getEarthRadius());
        assertEquals(0.0, tour.getResponseTime());
    }
    
    @Test
    @DisplayName("luzovich: Overloaded constructor sets values")
    public void testOverloadedConstructor() {
        TourRequest tour = new TourRequest(new Places(), 1000.0, 3.0, "vincenty");
        assertEquals(1000.0, tour.getEarthRadius());
        assertEquals(3.0, tour.getResponseTime());
    }

    @Test
    @DisplayName("luzovich: Build response ")
}
