package com.tco.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.TourOptimizer;

public class TourRequest extends Request {

    private Places places;
    private Double earthRadius;
    private String formula;
    private Double response;

    @Override
    public void buildResponse(){
        //I had to add this so it would build!
    }
}