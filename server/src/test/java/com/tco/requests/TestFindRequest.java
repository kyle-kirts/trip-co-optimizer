package com.tco.requests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.tco.misc.RequestException;

public class TestFindRequest{

  @Test
  @DisplayName("C873444063: Does not throw when created")
  public void baseInitialize() {
    assertDoesNotThrow(() -> new FindRequest());
  }

}