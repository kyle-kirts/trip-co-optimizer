package com.tco.misc;

public abstract class DataSource {

    public Places near(Place place, double distance, long earthRadius, String formula, int limit) {
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
}