package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataSource {

        private static final transient Logger log = LoggerFactory.getLogger(DataSource.class);


    protected Places results;

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) {
        results = new Places();
        try {

            initialize();
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

    public void initialize() {
    }

    public void select() {

    }

    public void convert() {

    }
}
