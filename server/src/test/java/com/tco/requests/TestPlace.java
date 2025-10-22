package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlace {

    /**
    * Constructor tests
    */

    @Test
    @DisplayName("vercauteren: a new default place object exists")
    public void testDefaultConstructor(){
        Place place = new Place();
        assertTrue(place != null);
    }

    @Test
    @DisplayName("vercauteren: a default place object is empty")
    public void testDefaultConstructorIsEmpty(){
        Place place = new Place();
        assertTrue(place.isEmpty());
    }

    @Test
    @DisplayName("vercauteren: a new specific place object exists")
    public void testOverloadedConstructor(){
        Place place = new Place("45.1111", "-105.2222");
        assertTrue(place!=null);
    }

    @Test
    @DisplayName("vercauteren: a specific place object is not empty")
    public void testOverloadedConstructorIsEmpty(){
        Place place = new Place("45.1111", "-105.2222");
        assertFalse(place.isEmpty());
    }

    /**
     * latitude and longitude
     */

    @Test
    @DisplayName("vercauteren: longitude of an empty object returns 0")
    public void testLonRadiansEmpty(){
        Place place = new Place();
        assertTrue(place.lonRadians() == 0.0);
    }

    @Test
    @DisplayName("vercauteren: longitude of an specific object exists")
    public void testLonRadiansExists(){
        Place place = new Place("45.1111", "-105.2222");
        assertDoesNotThrow(() -> {place.lonRadians();});
    }

    @Test
    @DisplayName("vercauteren: longitude of a specific object returns a double")
    public void testLonRadiansDouble(){
        Place place = new Place("45.1111", "-105.2222");
        assertTrue((place.lonRadians()%1) != 0);
    } 

    @Test
    @DisplayName("vercauteren: longitude of a specific object is converted to radians")
    public void testLonRadiansValid(){
        Place place = new Place("45.1111", "-105.2222");
        double radians = -1.8364738361919775;
        assertTrue(place.lonRadians() == radians);
    } 

    @Test
    @DisplayName("vercauteren: latitude of an empty object returns 0")
    public void testLatRadiansEmpty(){
        Place place = new Place();
        assertTrue(place.latRadians() == 0.0);
    }

    @Test
    @DisplayName("vercauteren: latitude of an specific object exists")
    public void testLatRadiansExists(){
        Place place = new Place("45.1111", "-105.2222");
        assertDoesNotThrow(() -> {place.latRadians();});
    }

    @Test
    @DisplayName("vercauteren: latitude of a specific object returns a double")
    public void testLatRadiansDouble(){
        Place place = new Place("45.1111", "-105.2222");
        assertTrue((place.latRadians()%1) != 0);
    } 

    @Test
    @DisplayName("vercauteren: latitude of a specific object is converted to radians")
    public void testLatRadiansValid(){
        Place place = new Place("45.1111", "-105.2222");
        double radians = 0.787337224196414;
        assertTrue(place.latRadians() == radians);
    }

    @Test
    @DisplayName("luzovich: Test equal method is true")
    public void testEqualsTrue() {
        Place place1 = new Place("39.6", "129.33");
        Place place2 = new Place("39.6", "129.33");

        assertTrue(place1.equals(place2));
    }

    @Test
    @DisplayName("luzovich: Test equal method is false")
    public void testEqualsFalse() {
        Place place1 = new Place("25.7", "1.093");
        Place place2 = new Place("29.2", "95.4");

        assertFalse(place1.equals(place2));
    }
}
