package com.tco.requests;

import com.tco.misc.BadRequestException;
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

  public FindRequest() {
    this.requestType = "find";
    this.match = "";
    this.source = null;
    this.limit = 0;
    this.found = 0;
    this.places = new Places();
  }  
    
  @Override
  public void buildResponse() throws RequestException {
    if (!validateRequest()) {
      throw new BadRequestException();
    }
    DataSource dataSource = SourceFactory.get(this.source);
    this.places = dataSource.find(this.match, this.limit);
    this.found = dataSource.found(this.match);
  }

  public boolean validateRequest() {
    boolean validRequest = true;

    if (limit == null) {
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
