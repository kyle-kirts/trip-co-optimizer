package com.tco.misc;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCosineHelper {

    private static CosineHelper helper;
    
    public GeographicCoordinate coordinateMaker(double latitude, double longitude) {
        return new GeographicCoordinate() {
            public double latRadians() {
                return latitude;
            }
            public double lonRadians() {
                return longitude;
            }
        };
    }

    @BeforeEach
    @DisplayName("luzovich: set up CosineHelper for each test")
    public void createHelper() {
        helper = new CosineHelper();
    }
    
    @Test
    @DisplayName("luzovich: both locations are the same")
    public void testSameLocations() {

        GeographicCoordinate from = coordinateMaker(0.0, 0.0);

        assertEquals(0.0, helper.computeCentralAngle(from, from));
    }

    @Test
    @DisplayName("luzovich: confirm symmetry relation on varied data")
    public void testSymmetryRelation() {

        GeographicCoordinate from = coordinateMaker(12.922, 9.192);
        GeographicCoordinate to = coordinateMaker(93.943, 23.110);

        assertEquals(1.4807384503371424, helper.computeCentralAngle(from, to));
        assertEquals(1.4807384503371424, helper.computeCentralAngle(to, from));
    }

    @Test
    @DisplayName("luzovich: negative radians have no effect")
    public void testNegativeRadians() {

        GeographicCoordinate from_positive = coordinateMaker(1.0, 2.0);
        GeographicCoordinate to_positive = coordinateMaker(3.0, 4.0);

        GeographicCoordinate from_negative = coordinateMaker(-1.0, -2.0);
        GeographicCoordinate to_negative = coordinateMaker(-3.0, -4.0);

        assertEquals(helper.computeCentralAngle(from_positive, to_positive), helper.computeCentralAngle(from_negative, to_negative));
    }

    @Test
    @DisplayName("luzovich: tiny radians")
    public void testTinyRadians() {

        GeographicCoordinate from = coordinateMaker(0.0000292, 0.03194);
        GeographicCoordinate to = coordinateMaker(0.3839, 0.00016445724);

        assertEquals(0.3851186477566681, helper.computeCentralAngle(from, to));
    }
=======
public class TestCosineHelper {
    
>>>>>>> main
}
