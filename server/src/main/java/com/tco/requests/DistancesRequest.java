package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    private Places places;
    private Double earthRadius;
    private Distances distances;
    private String formula;

    public DistancesRequest() {
        this.places = new Places();
        this.earthRadius = 6371.0;
        this.distances = new Distances();
        this.formula = "vincenty";
    }

    public DistancesRequest(Places places, Double earthRadius, String formula) {
        this.places = places;
        this.earthRadius = earthRadius;
        this.distances = new Distances();
        this.formula = formula;
    }

    public Distances getDistances() {
        return this.distances;
    }

    @Override
    public void buildResponse() {
        if (!(places == null || places.size() == 0)) {
            this.distances = new Distances();
        }
    }
}
