package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.BadRequestException;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourOptimizer;
import com.tco.misc.Places;


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
        Places result = optimizer.construct(this.places, this.earthRadius, this.formula, this.response);
        this.places = rotate(result);
    }

    public Places rotate(Places tour){
        int originIndex = 0;
        for(int i = 0; i<tour.size(); i++){
            if(tour.get(i) == this.places.get(0))
            {
                originIndex = i;
            }
        }
        
        Places wrapAround = new Places();
        wrapAround.addAll(tour.subList(0, originIndex));
        Places newBeginning = new Places();
        newBeginning.addAll(tour.subList(originIndex, tour.size()));
        newBeginning.addAll(wrapAround);

        return newBeginning; 
    }
}
