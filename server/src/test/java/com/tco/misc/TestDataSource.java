package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.bson.Document;

public class TestDataSource {
    @Test
    @DisplayName("jsibold: Validate DataSource.near() returns an empty Places object placeholder")
    public void testNearReturnsEmptyPlaces() throws Exception {
        DataSource dataSource = new DataSource() {};

        Place place = new Place();
        Integer distance = 100;
        Double earthRadius = Double.MAX_VALUE;
        String formula = "vincenty";
        Integer limit = 5;

        Places result = dataSource.near(place, distance, earthRadius, formula, limit);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("jsibold: Validate DataSource.distance() returns an empty distances object placeholder")
    public void testDistancesReturnsEmptyDistances() {
        DataSource dataSource = new DataSource() {};

        Place place = new Place();
        Places places = new Places();

        Distances result = dataSource.distances(place, places, 6371, "vincenty");

        assertNotNull(result);
        assertEquals(0, result.size());
    }   

    @Test
    @DisplayName("vercauteren: Validate distances expected length and output")
    public void testDistancesLength() {
        DataSource ds = new DataSource() {};
        Place origin = new Place("0.0","0.0");
        Places places = new Places();

        places.add(new Place("1.0","0.0"));
        places.add(new Place("2.0", "0.0"));

        Distances result = ds.distances(origin, places, 57, "vincenty");
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("vercauteren: Validate distances expected length and output")
    public void testDistancesOutput() {
        DataSource ds = new DataSource() {};
        Place origin = new Place("0.0","0.0");
        Places places = new Places();

        places.add(new Place("1.0","0.0"));
        places.add(new Place("2.0", "0.0"));

        Distances result = ds.distances(origin, places, 57, "vincenty");
        assertEquals(1, result.get(0));
        assertEquals(2, result.get(1));
    }

    @Test
    @DisplayName("schlicting: Validate select() method runs without error")
    public void testSelectRunsWithoutError() {
        DataSource datasource = new DataSource() {};
        datasource.select(new Place(), 100.0, 6371, 1);
        assertEquals(datasource, datasource);
        assertDoesNotThrow(() -> {
            datasource.select(new Place(), 100.0, 6371, 1);
        });
    }

    @Test
    @DisplayName("schlicting: Validate convert() method runs without error")
    public void testConvertRunsWithoutError() throws Exception{
        DataSource datasource = new DataSource() {};
        datasource.convert();
        assertEquals(datasource, datasource);
        assertDoesNotThrow(() -> {
            datasource.convert();
        });
    }

    @Test
    @DisplayName("luzovich: Default initialize() does not throw an error when called")
    public void testEmptyInitializeCall() {
        DataSource datasource = new DataSource() {};
        assertDoesNotThrow(() -> {
            datasource.initialize();
        });
    }

    @Test
    @DisplayName("schlicting: Validate near() method runs without error")
    public void testNearRunsWithoutError() throws Exception {
        DataSource datasource = new DataSource() {};
        datasource.near(new Place(), 100.0, 6371, "vincenty", 5);
        assertEquals(datasource, datasource);
        assertDoesNotThrow(() -> {
            datasource.near(new Place(), 100.0, 6371, "vincenty", 5);
        });
    }

    @Test
    @DisplayName("schlicting: Validate initialize() method runs without error")
    public void testInitializeRunsWithoutError() {
        DataSource datasource = new DataSource() {};
        datasource.initialize();
        assertEquals(datasource, datasource);
        assertDoesNotThrow(() -> {
            datasource.initialize();
        });
    }

    @Test
    @DisplayName("vercauteren: Validate selectMatch() method runs without error")
    public void testSelectMatchRunsWithoutError() {
        DataSource datasource = new DataSource() {};
        datasource.selectMatch("GIBBERISH", 1);
        assertEquals(datasource, datasource);
        assertDoesNotThrow(() -> {
            datasource.selectMatch("GIBBERISH", 1);
        });
    }

    @Test
    @DisplayName("kyle-kirts: limits greater than 100 are set to 100")
    public void testCheckLimit() {
        DataSource datasource = new DataSource() {};
        int limit = 101;

        limit = datasource.checkLimit(limit);
        assertEquals(100, limit);
    }

    @Test        
    @DisplayName("luzovich: find() with gibberish returns empty Places")
    public void testGibberishYieldsEmptyPlacesFromFind() throws Exception {
        DataSource datasource = SourceFactory.get("cities");
        assertEquals(0, datasource.find("some random gibberish", 1).size());
    }

    @Test
    @DisplayName("luzovich: Cap off find() with many results")
    public void testLimitOfFindWithManyResults() throws Exception {
        DataSource datasource = SourceFactory.get("cities");
        assertEquals(12, datasource.find("Dave", 12).size());
    }
}
