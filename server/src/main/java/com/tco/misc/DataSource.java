package com.tco.misc;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataSource {

    private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);

    protected Places results;

    public Places near(Place place, Integer distance, Double earthRadius, String formula, Integer limit) {
        results = new Places();

        try {
            initialize();
            selectNear(place, distance, earthRadius, limit);
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    public Places find(String match, Integer limit) {
        results = new Places();

        try {
            initialize();
            selectMatch(match, limit);
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }
    
    public Distances distances(Place place, Places places, Double earthRadius, String formula) {
        Distances allDistances = new Distances();
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        for(Place p : places) {
            allDistances.add(calculator.between(place, p, earthRadius));
        }
        return allDistances;
    }
    
    public void initialize() throws SQLException{
    }

    public void selectNear(Place place, Integer distance, Double earthRadius, Integer limit) throws SQLException {
    }

    public void selectMatch(String match, Integer limit) throws SQLException {
    }

    public Places convert() throws SQLException {
        return new Places();
    }

    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@black-bottle:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

    //testing methods
    public Places getResults() {
        return this.results;
    }
}
