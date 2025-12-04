package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tco.misc.RequestException;

public class TestFindRequest{

  @Test
  @DisplayName("C873444063: Does not throw when created")
  public void baseInitialize() {
    assertDoesNotThrow(() -> new FindRequest());
  }

  @Test
  @DisplayName("luzovich: buildResponse() does not throw on empty object initialization")
  public void testEmptyBuildResponseDoesNotThrowException() {

    FindRequest req = new FindRequest();

    assertDoesNotThrow(() -> req.buildResponse());
  }

  @Test
  @DisplayName("jsibold: Default request type is 'find'")
  public void testDefaultRequestType() {
    FindRequest req = new FindRequest();
    assertEquals("find", req.getRequestType());
  }

  @Test
  @DisplayName("jsibold: Invalid source throws RequestException")
  public void testInvalidSourceThrowsException() throws Exception {
    FindRequest req = new FindRequest();
    var sourceField = FindRequest.class.getDeclaredField("source");
    sourceField.setAccessible(true);
    sourceField.set(req, "invalidSource");
    assertThrows(RequestException.class, req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse with 'airports' source runs without error")
  public void testValidAirportsSource() throws Exception {
    FindRequest req = new FindRequest();
    var sourceField = FindRequest.class.getDeclaredField("source");
    sourceField.setAccessible(true);
    sourceField.set(req, "airports");
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse with 'cities' source runs without error")
  public void testValidCitiesSource() throws Exception {
    FindRequest req = new FindRequest();
    var sourceField = FindRequest.class.getDeclaredField("source");
    sourceField.setAccessible(true);
    sourceField.set(req, "cities");
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse returns non-null places")
  public void testBuildResponseReturnsPlaces() throws Exception {
    FindRequest req = new FindRequest();
    req.buildResponse();
    var placesField = FindRequest.class.getDeclaredField("places");
    placesField.setAccessible(true);
    assertNotNull(placesField.get(req));
  }

  @Test
  @DisplayName("jsibold: buildResponse returns non-null found")
  public void testBuildResponseReturnsFound() throws Exception {
    FindRequest req = new FindRequest();
    req.buildResponse();
    var foundField = FindRequest.class.getDeclaredField("found");
    foundField.setAccessible(true);
    assertNotNull(foundField.get(req));
  }

  @Test
  @DisplayName("jsibold: Multiple buildResponse calls work")
  public void testMultipleBuildResponseCalls() {
    FindRequest req = new FindRequest();
    assertDoesNotThrow(() -> {
      req.buildResponse();
      req.buildResponse();
      req.buildResponse();
    });
  }

  @Test
  @DisplayName("jsibold: buildResponse with null source uses default")
  public void testBuildResponseNullSource() {
    FindRequest req = new FindRequest();
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse with match string works")
  public void testBuildResponseWithMatch() throws Exception {
    FindRequest req = new FindRequest();
    var matchField = FindRequest.class.getDeclaredField("match");
    matchField.setAccessible(true);
    matchField.set(req, "Denver");
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse with limit works")
  public void testBuildResponseWithLimit() throws Exception {
    FindRequest req = new FindRequest();
    var limitField = FindRequest.class.getDeclaredField("limit");
    limitField.setAccessible(true);
    limitField.set(req, 10);
    assertDoesNotThrow(req::buildResponse);
  }

  @Test
  @DisplayName("jsibold: buildResponse with null limit throws RequestException")
  public void testBuildResponseNullLimitThrowsException() throws Exception {
    FindRequest req = new FindRequest();
    var limitField = FindRequest.class.getDeclaredField("limit");
    limitField.setAccessible(true);
    limitField.set(req, null);
    assertThrows(RequestException.class, req::buildResponse);
  }
}
