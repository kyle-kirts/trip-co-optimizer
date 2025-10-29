package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSourceFactory{

     @Test
    @DisplayName("vercauteren: base existence for SourceFactory")
    public void testFactoryExists() {
        SourceFactory factory = new SourceFactory() {};
        assertNotNull(factory);
    }
}