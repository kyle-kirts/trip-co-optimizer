package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestPlaces {
    @Test
    @DisplayName("vercauteren: a new default places object exists")
    public void testDefaultConstructor(){
        Places places = new Places();
        assertTrue(places != null);
    }
}