package com.tco.misc;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCitiesSource{

    //from guide wiki directly
    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@black-bottle:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

    @Test
    @DisplayName("vercauteren: base existence for CitiesSource")
    public void testSourceExists() {
        CitiesSource cities = new CitiesSource() {};
        assertNotNull(cities);
    }

    @Test
    @DisplayName("vercauteren: select() queries a database and returns something.")
    public void testSelectExists() {

        CitiesSource cities = new CitiesSource() {};
        cities.initialize();
        Place place = new Place("45","-105");
        double distance = 1000000000;
        long earthRadius = 6371;
        cities.select(place, distance, earthRadius);
        assertNotNull(cities.selectResults);
    }

    @Test
    @DisplayName("vercauteren: selectMatch() queries a database and returns something.")
    public void testSelectMatchExists() {

        CitiesSource cities = new CitiesSource() {};
        cities.initialize();
        String match = "Dave";
        cities.selectMatch(match, 10);
        assertNotNull(cities.selectResults);
    }
}