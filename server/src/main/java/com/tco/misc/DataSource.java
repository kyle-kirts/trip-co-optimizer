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
            selectNear(place, distance, earthRadius, checkLimit(limit));
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    public Integer checkLimit(Integer limit) {
        if (limit > 100) { 
            limit = 100;  
        }
        return limit;
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

    public Integer countMatch(String match) throws SQLException {
        return 0;
    }

    //testing methods
    public Places getResults() {
        return this.results;
    }
}
