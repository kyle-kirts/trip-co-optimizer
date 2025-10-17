package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourOptimizer;

public class TourRequest extends Request {

    private String requestType;
    private Places places;
    private Double earthRadius;
    private String formula;
    private Double response;

    public TourRequest() {

        this.requestType = "tour";
        this.places = new Places();
        this.earthRadius = 6371.0;
        this.response = 0.0;
        this.formula = null;
    }

    public TourRequest(Places places, Double earthRadius, Double response, String formula) {

        this.requestType = "tour";
        this.places = places;
        this.earthRadius = earthRadius;
        this.response = response;
        this.formula = formula;
    }

    public Double getEarthRadius() {

        return this.earthRadius;
    }

    public Double getResponseTime() {

        return this.response;
    }
    
    @Override
    public void buildResponse(){
        //I had to add this so it would build!
    }
}
