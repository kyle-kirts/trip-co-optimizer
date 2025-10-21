package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class TestJSONReader {

  @Test
  @DisplayName("luzovich: Non-existent file throws InternalRequestException")
  public void testNonExistentFileYieldsException() {

    assertThrows(InternalRequestException.class, () -> JSONReader.fetchValidatedJSONFile("/some/random/path", AboutFile.class));
  }

  @Test
  @DisplayName("luzovich: Invalid JSON yields parse error and IOException")
  public void testInvalidJSONInputYieldsException() {

    assertThrows(InternalRequestException.class, () -> JSONReader.fetchValidatedJSONFile("/data/faulty.json", AboutFile.class));
  }
}
