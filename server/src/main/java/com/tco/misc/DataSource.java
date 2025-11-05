package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public abstract class DataSource {

    private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);
    private static final String DATABASE = "cs314";

    protected Places results;

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) {
        results = new Places();
        try {

            initialize(null);
            select();
            convert();

            if (results == null) {
                results = new Places();
            }
        } catch (Exception e) {
            log.warn("near() failed returning empty list: {}", e.toString());
            results = new Places();
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
    //So now when a citySource calls “initialize” it runs mongodb specific code and/or returns an appropriate object.
    public Places initialize(MongoIterable<Document> results) {
        MongoDatabase database = mongoClient.getDatabse(DATABASE);
        MongoCollection<Document> collection = database.getCollection(COLLECTION);
    }

    public void select() {
        // initialize should be called from inside a try catch block here.
    }

    public void convert() {
    }
}
