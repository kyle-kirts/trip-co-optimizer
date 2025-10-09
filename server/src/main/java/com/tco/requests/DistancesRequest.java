package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.DistanceCalculator;
import com.tco.misc.GeographicCoordinate;

public class DistancesRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(DistancesRequest.class);
    private static final transient String DEFAULT_FORMULA = "vincenty";

    private Places places;
    private Double earthRadius;
    private Distances distances;
    private String formula;

    public DistancesRequest() {
        this.places = new Places();
        this.earthRadius = 6371.0;
        this.distances = new Distances();
        this.formula = "";
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

    public String getFormula() {
        return this.formula;
    }

    @Override
    public void buildResponse() {
        DistanceCalculator calculator = CalculatorFactory.getCalculator((!this.formula.equals("")) ? this.formula : DEFAULT_FORMULA);
        int tripLength = this.places.size();
        for(int i = 0; i<tripLength; i++)
        {
            GeographicCoordinate thisPlace = this.places.get(i);
            GeographicCoordinate nextPlace = (i+1<tripLength) ? this.places.get(i+1): this.places.get(0);
            this.distances.add(calculator.between(thisPlace, nextPlace, this.earthRadius));
        }

        if (this.formula.equals("")) this.formula = null;
    }
}
