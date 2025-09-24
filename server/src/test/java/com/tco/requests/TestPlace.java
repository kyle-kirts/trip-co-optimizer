package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.Set;

public class TestPlace {

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

    @Test
    @DisplayName("vercauteren: a specific place object contains two strings")
    public void testDefaultConstructorCordinateType(){
        Place place = new Place("45.1111", "-105.2222");
        Set<String> keys = place.keySet();
        Iterator<String> it = keys.iterator();
        assertTrue(it.next() instanceof String);
        assertTrue(place.get("45.1111") instanceof String);
    }

    @Test
    @DisplayName("vercauteren: a specific place object does not contain doubles")
    public void testDefaultConstructorCordinateType(){
        Place place = new Place("45.1111", "-105.2222");
        Set<String> keys = place.keySet();
        Iterator<String> it = keys.iterator();
        assertTrue(it.next() instanceof Double);
        assertTrue(place.get("45.1111") instanceof Double);
    }

}