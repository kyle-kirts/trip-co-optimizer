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

    @Test
    @DisplayName("vercauteren: the getPlace() method finds a place that exists in places")
    public void testGetPlace(){
        Places places = new Places();
        Place place = new Place("40.01","-105.001");
        places.add(place);
        int index = places.getPlace(place);
        assertTrue(index == 0);
    }

    @Test
    @DisplayName("vercauteren: the getPlace() method does not find a place that doesnt exist")
    public void testBadGetPlace(){
        Places places = new Places();
        Place place = new Place("40.01","-105.001");
        places.add(place);
        int index = places.getPlace(new Place());
        assertFalse(index >= 0);
    }
}