package com.tco.misc;

public abstract class DataSource {

    public Places near(Place place, Integer distance, Double earthRadius, String formula, Integer limit) {
        return new Places();
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
