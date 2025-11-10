package com.tco.requests;

import com.tco.misc.CalculatorFactory;
import com.tco.misc.Distances;
import com.tco.misc.Place;
import com.tco.misc.Places;
import com.tco.misc.RequestException;
import com.tco.misc.SourceFactory;
import com.tco.misc.DataSource;

public class FindRequest extends Request {
  private String match;
  private String source;
  private Integer limit;
  private Integer found;
  private Places places;
    
  @Override
  public void buildResponse() throws RequestException {
  }
}
