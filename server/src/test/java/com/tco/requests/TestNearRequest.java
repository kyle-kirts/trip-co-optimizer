package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
}
