package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tco.misc.RequestException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNearRequest {

  @Test
  @DisplayName("luzovich: Does not throw exception when initialized")
  public void testInitializationDoesNotThrowException() {

    assertDoesNotThrow(() -> new NearRequest());
  }

  @Test
  @DisplayName("luzovich: buildResponse() does not throw exception on empty object initialization")
  public void testEmptyBuildResponseDoesNotThrowException() {

    NearRequest req = new NearRequest();

    assertDoesNotThrow(() -> req.buildResponse());
  }

  @Test
  @DisplayName("jsibold: Default request type is 'near'")
  public void testDefaultRequestType() {
    NearRequest req = new NearRequest();
    assertEquals("near", req.getRequestType());
  }

  @Test
  @DisplayName("jsibold: Valid vincenty formula does not throw")
  public void testValidVincentyFormula() throws Exception {
    NearRequest req = new NearRequest();
    var formulaField = NearRequest.class.getDeclaredField("formula");
    formulaField.setAccessible(true);
    formulaField.set(req, "vincenty");
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: Invalid formula throws RequestException")
  public void testInvalidFormulaThrowsException() throws Exception {
    NearRequest req = new NearRequest();
    var formulaField = NearRequest.class.getDeclaredField("formula");
    formulaField.setAccessible(true);
    formulaField.set(req, "invalidFormula");
    assertThrows(RequestException.class, req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: Invalid source throws RequestException")
  public void testInvalidSourceThrowsException() throws Exception {
    NearRequest req = new NearRequest();
    var sourceField = NearRequest.class.getDeclaredField("source");
    sourceField.setAccessible(true);
    sourceField.set(req, "invalidSource");
    assertThrows(RequestException.class, req::buildResponse);
  }
  
  @Test
  @DisplayName("jsibold: buildResponse with 'airports' source runs without error")
  public void testValidAirportsSource() throws Exception {
    NearRequest req = new NearRequest();
    var sourceField = NearRequest.class.getDeclaredField("source");
    sourceField.setAccessible(true);
    sourceField.set(req, "airports");
    assertDoesNotThrow(req::buildResponse);
  }


  @Test
  @DisplayName("jsibold: buildResponse returns non-null places")
  public void testBuildResponseReturnsPlaces() throws Exception {
    NearRequest req = new NearRequest();
    req.buildResponse();
    var placesField = NearRequest.class.getDeclaredField("places");
    placesField.setAccessible(true);
    assertNotNull(placesField.get(req));
  }

  @Test
  @DisplayName("jsibold: Multiple buildResponse calls work")
  public void testMultipleBuildResponseCalls() {
    NearRequest req = new NearRequest();
    assertDoesNotThrow(() -> {
      req.buildResponse();
      req.buildResponse();
      req.buildResponse();});
  }

  @Test
  @DisplayName("jsibold: buildResponse with null source uses default")
  public void testBuildResponseNullSource() {
    NearRequest req = new NearRequest();
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("kyle-kirts: limit greater than 100 is set to 100")
  public void testCheckLimit(){
    NearRequest request = new NearRequest(101);
    request.checkLimit();

    assertEquals(100, request.getLimit());
  }
}