package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDataSource {
@Test
@DisplayName("jsibold: Validate DataSource.near() returns an empty Places object placeholder")
public void testNearReturnsEmptyPlaces() {
    DataSource dataSource = new DataSource() {};

    Place place = new Place();
    double distance = 100.0;
    long earthRadius = Long.MAX_VALUE;
    String formula = "vincenty";
    int limit = 5;

    Places result = dataSource.near(place, distance, earthRadius, formula, limit);

    assertNotNull(result);
    assertEquals(0, result.size());
}
@Test
@DisplayName("jsibold: Validate DataSource.distance() returns an empty distances object placeholder")
public void testDistancesReturnsEmptyDistances() {
    DataSource dataSource = new DataSource() {};

    Place place = new Place();
    Places places = new Places();

    Distances result = dataSource.distances(place, places);

    assertNotNull(result);
    assertEquals(0, result.size());
}   
}
    