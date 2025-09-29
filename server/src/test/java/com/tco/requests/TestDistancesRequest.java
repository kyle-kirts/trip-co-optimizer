package com.tco.requests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class TestDistancesRequest {
    @Test
    @DisplayName("schlicting: Test buildResponse with zero places")
    public void testBuildResponseZeroPlaces() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6371.0, "Haversine");
        request.buildResponse();
        Distances distances = request.getDistances();
        assertNotNull(distances);
        assertEquals(0, distances.size());
    }

    
}
