package com.tco.requests;

import java.util.ArrayList;

import com.tco.misc.Places;
import com.tco.misc.Place;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.function.Executable;

import com.tco.misc.BadRequestException;

public class TestTourRequest {

    public static void routeMatches(Places reference, Places target) {

        if (reference.size() != target.size()) {

            fail("Reference size and target size are not the same!");
            return;
        }

        ArrayList<Executable> assertions = new ArrayList<>();

        for (int i = 0; i < reference.size(); i++) {

            // We must do the logic outside because Executables must only use final or effectively final variables.
            final boolean assertion = reference.get(i).equals(target.get(i));
            final String assertionDescription = "Expect target [" + target.get(i) + "] to equal reference [" + reference.get(i) + "]";

            assertions.add(() -> assertTrue(assertion, assertionDescription));
        }

        assertAll("All target list matches reference list", assertions);        
    }

    @Test
    @DisplayName("vercauteren: base test for existence")
    public void testExists(){
        TourRequest tour = new TourRequest();
        assertNotNull(tour, "an object should exist.");
    }

    @Test
    @DisplayName("luzovich: Base constructor sets values")
    public void testBaseConstructor() {
        TourRequest tour = new TourRequest();
        assertEquals(6371.0, tour.getEarthRadius());
        assertEquals(0.0, tour.getResponseTime());
    }
    
    @Test
    @DisplayName("luzovich: Overloaded constructor sets values")
    public void testOverloadedConstructor() {
        TourRequest tour = new TourRequest(new Places(), 1000.0, 3.0, "vincenty");
        assertEquals(1000.0, tour.getEarthRadius());
        assertEquals(3.0, tour.getResponseTime());
    }

    @Test
    @DisplayName("luzovich: Build response throws BadRequestException due to invalid formula")
    public void testInvalidFormulaThrowsException() {

        TourRequest tour = new TourRequest(new Places(), 1000.0, 3.0, "something wrong goes here");
        assertThrows(BadRequestException.class, () -> tour.buildResponse());
    }

    @Test
    @DisplayName("luzovich: Build response yields proper NoOpt distance")
    public void testBuildResponseNoOptCorrect() {

        Place subPlace1 = new Place("0.0", "0.0");
        Place subPlace2 = new Place("90.0", "90.0");
        Place subPlace3 = new Place("45.0", "45.0");
        Place subPlace4 = new Place("90.0", "180.0");

        Places route = new Places();
        route.add(subPlace1);
        route.add(subPlace2);
        route.add(subPlace3);
        route.add(subPlace4);
        
        TourRequest tour = new TourRequest(route, 1000.0, 0.0, "vincenty");

        try {
            tour.buildResponse();
        } catch (BadRequestException e) {
            fail("The builder threw an exception: " + e);
        }

        routeMatches(route, tour.getPlaces());
    }

    @Test
    @DisplayName("luzovich: Don't rotate items in list when start is already at beginning")
    public void testDontRotateOnAlreadyStarted() {
        Place subPlace1 = new Place("0.0", "0.0");
        Place subPlace2 = new Place("1.0", "1.0");

        Places route = new Places();
        route.add(subPlace1);
        route.add(subPlace2);

        TourRequest tour = new TourRequest(route, 1000.0, 0.0, "vincenty");

        routeMatches(route, tour.rotate(route));
    }

    @Test
    @DisplayName("luzovich: Rotate all the way through")
    public void testRotateAllTheWayThrough() {
        Place subPlace1 = new Place("0.0", "0.0");
        Place subPlace2 = new Place("1.0", "1.0");

        Places route1 = new Places();
        route1.add(subPlace1);
        route1.add(subPlace2);
        
        Places route2 = new Places();
        route2.add(subPlace2);
        route2.add(subPlace1);

        TourRequest tour = new TourRequest(route2, 1000.0, 0.0, "vincenty");

        routeMatches(route2, tour.rotate(route1));
    }
}
