package com.tco.requests;

import com.tco.misc.Distances;
import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.RequestException;

public class NearRequest extends Request {
  private Place place;
  private Integer distance;
  private Double earthRadius;
  private Integer limit;
  private String formula;
  private String source;
  private Places places;
  private Distances distances;

  @Override
  public void buildResponse() throws RequestException {
    
  }
}