package com.tco.misc;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataSource {

    private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);

    protected Places results;

    public Places near(Place place, Integer distance, Double earthRadius, Integer limit) {
        return new Places();
    }

    public Places find(String match, Integer limit) {
        results = new Places();
        return results;
    }

    public Integer checkLimit(Integer limit) {
        if (limit > 100) { 
            limit = 100;  
        }
        return limit;
    }
    
    public Distances distances(Place place, Places places, Double earthRadius, String formula) {
        Distances allDistances = new Distances();
        DistanceCalculator calculator = CalculatorFactory.getCalculator(formula);
        for(Place p : places) {
            allDistances.add(calculator.between(place, p, earthRadius));
        }
        return allDistances;
    }

    public Places convert() throws SQLException {
        return new Places();
    }

    //testing methods
    public Places getResults() {
        return this.results;
    }
}
