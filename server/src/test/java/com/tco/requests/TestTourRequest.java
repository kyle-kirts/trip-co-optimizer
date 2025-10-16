package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTourRequest {

    @Test
    @DisplayName("vercauteren: base test for existence")
    public void testExists(){
        TourRequest tour = new TourRequest();
        assertNotNull(tour, "an object should exist.");
    }
}