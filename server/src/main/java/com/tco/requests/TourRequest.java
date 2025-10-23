package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.BadRequestException;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourOptimizer;

public class TourRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

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

    public Places getPlaces() {

        return this.places;
    }
    
    public Double getEarthRadius() {

        return this.earthRadius;
    }

    public Double getResponseTime() {

        return this.response;
    }

    @Override
    public void buildResponse() throws BadRequestException {

        if ((this.formula != null) && (!CalculatorFactory.getSupportedFormulae().contains(this.formula))) throw new BadRequestException();
        TourOptimizer optimizer = OptimizerFactory.get(this.places.size(), this.response);
        this.places = optimizer.construct(this.places, this.earthRadius, this.formula, this.response);
    }
}
