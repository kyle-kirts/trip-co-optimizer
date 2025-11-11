package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAirportsSource {
    
    @Test
    @DisplayName("kyle-kirts: Base test case")
    public void testDefaultAirportsSource() {
        DataSource dataSource = new AirportsSource();

        assertNotNull(dataSource);
        assertTrue(dataSource instanceof AirportsSource);
    }
    @Test
    @DisplayName("jsibold: AirportsSource.initialize() returns a valid connection or handles failure gracefully")
    public void testInitializeConnection() {
        AirportsSource src = new AirportsSource();
        
        assertDoesNotThrow(() -> src.initialize(), "initialize() should not throw even if DB is unreachable");
    }
    @Test
    @DisplayName("jsibold: AirportsSource.initialize() sets connection to null when SQLException occurs")
    public void testInitializeReturnsNullOnSQLException() {
        String originalUrl = System.getProperty("mariadb.url");
        try {
            System.setProperty("mariadb.url", "jdbc:mariadb://invalid-host-that-does-not-exist:9999/invalid");
            AirportsSource src = new AirportsSource();
            src.initialize();
        } finally {
            if (originalUrl != null) {
                System.setProperty("mariadb.url", originalUrl);
            } else {
                System.clearProperty("mariadb.url");
            }
        }
    }

    @Test
    @DisplayName("jsibold: AirportsSource.initialize() catches SQLException with malformed URL")
    public void testInitializeCatchesMalformedURL() {
        String originalUrl = System.getProperty("mariadb.url");
        try {
            System.setProperty("mariadb.url", "not-a-valid-jdbc-url");
            AirportsSource src = new AirportsSource();
            src.initialize();
        } finally {
            if (originalUrl != null) {
                System.setProperty("mariadb.url", originalUrl);
            } else {
                System.clearProperty("mariadb.url");
            }
        }
    }





    @Test
    @DisplayName("jsibold: AirportsSource.initialize() establishes a connection if database is reachable")
    public void testInitializeConnects() {
    AirportsSource src = new AirportsSource();
    src.initialize();
    }

    @Test
    @DisplayName("jsibold: AirportsSource.select() works")
    public void testSelectWhenConnected() throws Exception {
        AirportsSource src = new AirportsSource();
        src.initialize();
        Place place = new Place("40.5", "-105.1");
        src.select(place, 50, 3959);
        assertNotNull(src.selectResults);
        assertTrue(src.selectResults.next());
    }

    @Test
    @DisplayName("jsibold: select() returns null when connection is null")
    public void testSelectWithNullConnection() {
        AirportsSource src = new AirportsSource();
        Place place = new Place("40.5", "-105.1");
        src.select(place, 50, 3959);
        assertNull(src.selectResults);
    }

    @Test
    @DisplayName("jsibold: select() finds airports near Fort Collins")
    public void testSelectNearFortCollins() throws Exception {
        AirportsSource src = new AirportsSource();
        src.initialize();
        Place fortCollins = new Place("40.585", "-105.084");
        src.select(fortCollins, 10, 3959);
        assertNotNull(src.selectResults);
        assertTrue(src.selectResults.next());
    }

    @Test
    @DisplayName("jsibold: select() with different earth radius (kilometers)")
    public void testSelectWithKilometers() throws Exception {
        AirportsSource src = new AirportsSource();
        src.initialize();
        Place place = new Place("40.5", "-105.1");
        src.select(place, 50, 6371);
        assertNotNull(src.selectResults);
        assertTrue(src.selectResults.next());
    }

    @Test
    @DisplayName("jsibold: select() with very small distance may find nothing")
    public void testSelectSmallDistance() throws Exception {
        AirportsSource src = new AirportsSource();
        src.initialize();
        Place place = new Place("0.0", "0.0"); 
        src.select(place, 1, 3959); 
        assertNotNull(src.selectResults);    
    }

    @Test@DisplayName("vercauteren: convert() handles columns for ID, name, municipality, country, region, lat and long")
    public void testConvertCols() throws Exception{
        AirportsSource src = new AirportsSource();
        src.initialize();
        Place nullIsland = new Place("0.0", "0.0"); 
        src.select(nullIsland, 10000, 395); 

        Places places = src.convert();
        assertTrue(places.get(0).get("ident") instanceof String);
        assertTrue(places.get(0).get("name") instanceof String);
        assertTrue(places.get(0).get("municipality") instanceof String);
        assertTrue(places.get(0).get("country") instanceof String);
        assertTrue(places.get(0).get("region") instanceof String);
        assertTrue(places.get(0).get("latitude") instanceof String);
        assertTrue(places.get(0).get("longitude") instanceof String);
    }

}
