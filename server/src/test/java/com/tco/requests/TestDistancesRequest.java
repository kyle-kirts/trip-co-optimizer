package com.tco.requests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class TestDistancesRequest {
    @Test
    @DisplayName("schlicting: Test buildResponse with zero places")
    public void testBuildResponseZeroPlaces() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        request.buildResponse();
        Distances distances = request.getDistances();
        assertNotNull(distances);
        assertEquals(0, distances.size());
    }

    @Test
    @DisplayName("schlicting: Test DistancesRequest() for 0 places")
    public void testDistancesRequestZeroPlaces() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        assertNotNull(request.getDistances());
        assertEquals(0, request.getDistances().size());
    }

    @Test
    @DisplayName("luzovich: Test DistancesRequest for omitted default formulae of \"vincenty\"")
    public void testDistancesRequestDefaultFormulaOmitted() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6371.0, "");
        request.buildResponse();
        assertNull(request.getFormula());
    }

    @Test
    @DisplayName("schlicting: Test DistancesRequest() for 1 places")
    public void testDistancesRequestOnePlace() {
        Places places = new Places();
        places.add(new Place("0.0", "0.0"));
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        request.buildResponse(); 
        assertNotNull(request.getDistances());
        assertEquals(1, request.getDistances().size());
    }
}
