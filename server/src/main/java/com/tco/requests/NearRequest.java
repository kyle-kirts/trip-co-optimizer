package com.tco.requests;

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
      boolean formulaWasProvided = this.formula != null;
      boolean formulaIsSupported = CalculatorFactory.getSupportedFormulae().contains(this.formula);

      if (formulaWasProvided && !formulaIsSupported) {
        throw new RequestException();
      }
      boolean sourceWasProvided = this.source != null;
      boolean sourceIsSupported =
      SourceFactory.getSupportedSources().contains(this.source);

      if (sourceWasProvided && !sourceIsSupported) {
        throw new RequestException();
    }
      DataSource dataSource = SourceFactory.get(this.source);
      this.places = dataSource.near(this.place, this.distance, this.earthRadius, this.formula, this.limit);
  }
}