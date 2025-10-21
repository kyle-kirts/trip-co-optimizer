package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.tco.misc.BadRequestException;

public class TestDistancesRequest {
    @Test
    @DisplayName("schlicting: Test buildResponse with zero places")
    public void testBuildResponseZeroPlaces() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e);
        }
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
        DistancesRequest request = new DistancesRequest(places, 6371.0, null);
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e);
        }
        assertNull(request.getFormula());
    }

    @Test
    @DisplayName("luzovich: Unsupported formula throws RequestException")
    public void testUnsupportedFormulaThrowsRequestException() {
        Places places = new Places();
        DistancesRequest request = new DistancesRequest(places, 6361.0, "something not supported");
        assertThrows(BadRequestException.class, () -> {
            request.buildResponse();
        });
    }

    @Test
    @DisplayName("schlicting: Test DistancesRequest() for 1 places")
    public void testDistancesRequestOnePlace() {
        Places places = new Places();
        places.add(new Place("0.0", "0.0"));
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e);
        }
        assertNotNull(request.getDistances());
        assertEquals(1, request.getDistances().size());
    }

    // @Test
    // @DisplayName("schlicting: Test DistancesRequest() for null things")
    // public void testDistancesRequestNullThings() {
    //     DistancesRequest request = new DistancesRequest(null, null, null);
    //     try {
    //         request.buildResponse();
    //     } catch (Exception e) {
    //         fail("buildResponse threw an exception: " + e);
    //     }
    //     assertNotNull(request.getDistances());
    //     assertEquals(0, request.getDistances().size());
    //     assertNull(request.getFormula());
    // }

    @Test
    @DisplayName("schlicting: Test DistancesRequest() for 2 places")
    public void testDistancesRequestTwoPlaces() {
        Places places = new Places();
        places.add(new Place("0.0", "0.0"));
        places.add(new Place("1.0", "1.0"));
        DistancesRequest request = new DistancesRequest(places, 6371.0, "vincenty");
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e);
        }
        assertNotNull(request.getDistances());
        assertEquals(2, request.getDistances().size());
    }

    @Test
    @DisplayName("luzovich: Test DistanceRequest() for 2 duplicate places")
    public void testDistancesRequestTwoDuplicatePlaces() {
        Places places = new Places();
        places.add(new Place("-19.27", "92.5"));
        places.add(new Place("-19.27", "92.5"));
        DistancesRequest request = new DistancesRequest(places, 123456789123456789123456789.0, "vincenty");
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e);
        }
        assertEquals(0L, request.getDistances().get(0));
    }
}
