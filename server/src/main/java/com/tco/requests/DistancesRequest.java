package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.DistanceCalculator;
import com.tco.misc.GeographicCoordinate;
import com.tco.misc.BadRequestException;

public class DistancesRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);

    private Places places;
    private Double earthRadius;
    private Distances distances;
    private String formula;

    public DistancesRequest() {
        this.requestType = "distances";
        this.places = new Places();
        this.earthRadius = 6371.0;
        this.distances = new Distances();
        this.formula = null;
    }

    public DistancesRequest(Places places, Double earthRadius, String formula) {
        this.requestType = "distances";
        this.places = places;
        this.earthRadius = earthRadius;
        this.distances = new Distances();
        this.formula = formula;
    }

    public Distances getDistances() {
        return this.distances;
    }

    public String getFormula() {
        return this.formula;
    }

    @Override
    public void buildResponse() throws BadRequestException {
        if ((this.formula != null) && (!CalculatorFactory.getSupportedFormulae().contains(this.formula))) throw new BadRequestException();
        DistanceCalculator calculator = CalculatorFactory.getCalculator(this.formula);
        int tripLength = this.places.size();
        for(int i = 0; i<tripLength; i++)
        {
            GeographicCoordinate thisPlace = this.places.get(i);
            GeographicCoordinate nextPlace = (i+1<tripLength) ? this.places.get(i+1): this.places.get(0);
            if(thisPlace.equals(nextPlace)){this.distances.add(0l);}
            else{
                this.distances.add(calculator.between(thisPlace, nextPlace, this.earthRadius));
            }
        }
    }
}
