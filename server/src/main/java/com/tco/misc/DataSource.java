package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class DataSource {

    MongoCollection<Document> collection;
    FindIterable<Document> selectResults;

    private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);

    protected Places results;

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) throws Exception {
        try {

            initialize();
            select(place, distance, earthRadius);
            convert();

            if (results == null) {
                results = new Places();
            }
        } catch (Exception e) {
            log.warn("near() failed returning empty list: {}", e.toString());
           throw e;
        }
        return results;
    }
    
    public Distances distances(Place place, Places places, long earthRadius, String formula) {
        Distances allDistances = new Distances();
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        for(Place p : places){
            allDistances.add(calculator.between(place, p, earthRadius));
        }
        return allDistances;
    }
    
    // @Override to be added later
    public void initialize() {
        MongoClient mongoClient = MongoClients.create(Credential.URL);
        MongoDatabase database = mongoClient.getDatabase("cs314");
        this.collection = database.getCollection("cities");
    }

    public void select(Place place, double distance, long earthRadius) {

    }

    public Places convert() {
        return new Places();
    }

    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@black-bottle:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

}
