package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataSource {

    private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);

    protected Places results;

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) throws Exception {
        try {

            initialize();
            select(place, distance, earthRadius, checkLimit(limit));
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

    public Places near(Place place, Integer distance, Double earthRadius, String formula, Integer limit) {
        return new Places();
    }

    public Places find(String match, int limit) throws Exception {

        try {

            initialize();
            selectMatch(match, limit);
            convert();

            if (results == null) {
                results = new Places();
            }
        } catch (Exception e) {

            log.warn("find() failed returning empty list: {}", e.toString());
            throw e;
        }

        return results;
    }
    
    public Distances distances(Place place, Places places, long earthRadius, String formula) {
        Distances allDistances = new Distances();
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        for(Place p : places) {
            allDistances.add(calculator.between(place, p, earthRadius));
        }
        return allDistances;
    }
    
    public void initialize() {
    }

    public void select(Place place, double distance, long earthRadius, int limit) {
    }

    public void selectMatch(String match, int limit){
    }

    public Places convert() throws Exception{
        return new Places();
    }

    public int checkLimit(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return limit;
    }

    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@black-bottle:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

}
