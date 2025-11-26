package com.tco.misc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

public class TestCitiesSource{

    //from guide wiki directly
    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@cilantro:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

    @Test
    @DisplayName("vercauteren: base existence for CitiesSource")
    public void testSourceExists() {
        CitiesSource cities = new CitiesSource() {};
        assertNotNull(cities);
    }

    @Test
    @DisplayName("vercauteren: select() queries a database and returns something.")
    public void testSelectExists() throws RequestException {

        CitiesSource cities = new CitiesSource() {};
        Place place = new Place("45","-105");
        Integer distance = 1000000000;
        Double earthRadius = 6371.0;
        assertNotNull(cities.near(place, distance, earthRadius, 1));
    }

    @Test
    @DisplayName("vercauteren: selectMatch() queries a database and returns something.")
    public void testSelectMatchExists() throws RequestException {

        CitiesSource cities = new CitiesSource() {};
        String match = "Dave";
        assertNotNull(cities.find(match, 10));
    }

    @Test
    @DisplayName("kyle-kirts: convert changes fields to strings")
    public void testConvertToString() throws RequestException {
        CitiesSource source = new CitiesSource();
        Place place = new Place("50.0", "-45.5");
        Places places = source.near(place, 100000000, 395.0, 1);
        
        assertTrue(places.get(0).get("municipality") instanceof String);
        assertTrue(places.get(0).get("country") instanceof String);
        assertTrue(places.get(0).get("region") instanceof String);
        assertTrue(places.get(0).get("latitude") instanceof String);
        assertTrue(places.get(0).get("longitude") instanceof String);
    }
}
