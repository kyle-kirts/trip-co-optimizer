package com.tco.misc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestCalculatorFactory {

    @Test
    public void testSupportedFormulaeContainsVincenty() {
        List<String> formulas = CalculatorFactory.getSupportedFormulae();
        assertTrue(formulas.contains("vincenty"));
    }
}
