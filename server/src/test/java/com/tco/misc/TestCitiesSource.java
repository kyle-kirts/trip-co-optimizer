package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCitiesSource{

     @Test
    @DisplayName("vercauteren: base existence for CitiesSource")
    public void testSourceExists() {
        CitiesSource cities = new CitiesSource() {};
        assertNotNull(cities);
    }
}