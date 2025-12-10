package com.tco.requests;

import com.tco.misc.BadRequestException;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.Distances;
import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.RequestException;
import com.tco.misc.SourceFactory;
import com.tco.misc.DataSource;

public class NearRequest extends Request {
  private Place place;
  private Integer distance;
  private Double earthRadius;
  private Integer limit;
  private String formula;
  private String source;
  private Places places;
  private Distances distances;

  public NearRequest() {
    this.requestType = "near";
    this.place = new Place();
    this.distance = 0;
    this.earthRadius = 6371.0;
    this.limit = 0;
    this.formula = null;
    this.source = null;
    this.places = new Places();
    this.distances = new Distances();
  }

  @Override
  public void buildResponse() throws RequestException {
    if (!validateRequest()) {
      throw new BadRequestException();
    }

    DataSource dataSource = SourceFactory.get(this.source);
    this.places = dataSource.near(this.place, this.distance, this.earthRadius, this.limit);
    this.distances = dataSource.distances(this.place, this.places, this.earthRadius, this.formula);
  }

  public boolean validateRequest() {
    boolean formulaWasProvided = this.formula != null;
    boolean formulaIsSupported = CalculatorFactory.getSupportedFormulae().contains(this.formula);
    boolean validRequest = true;

    if (formulaWasProvided && !formulaIsSupported) {
      validRequest = false;
    }
    boolean sourceWasProvided = this.source != null;
    boolean sourceIsSupported = SourceFactory.getSupportedSources().contains(this.source);

    if (sourceWasProvided && !sourceIsSupported) {
      validRequest = false;
    }

    return validRequest;
  }
  
}
