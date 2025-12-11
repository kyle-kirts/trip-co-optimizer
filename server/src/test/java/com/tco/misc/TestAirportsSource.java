package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

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
    
    //@Test
    //@DisplayName("jsibold: AirportsSource.initialize() returns a valid connection or handles failure gracefully")
    //public void testInitializeConnection() {
    //    AirportsSource src = new AirportsSource();
    //    
    //    assertDoesNotThrow(() -> src.initialize(), "initialize() should not throw even if DB is unreachable");
    //}

    //@Test
    //@DisplayName("jsibold: AirportsSource.initialize() sets connection to null when SQLException occurs")
    //public void testInitializeReturnsNullOnSQLException() {
    //    String originalUrl = System.getProperty("mariadb.url");
    //    try {
    //        System.setProperty("mariadb.url", "jdbc:mariadb://invalid-host-that-does-not-exist:9999/invalid");
    //        AirportsSource src = new AirportsSource();
    //        src.initialize();
    //    } finally {
    //        if (originalUrl != null) {
    //            System.setProperty("mariadb.url", originalUrl);
    //        } else {
    //            System.clearProperty("mariadb.url");
    //        }
    //    }
    //}

    //@Test
    //@DisplayName("jsibold: AirportsSource.initialize() catches SQLException with malformed URL")
    //public void testInitializeCatchesMalformedURL() {
    //    String originalUrl = System.getProperty("mariadb.url");
    //    try {
    //        System.setProperty("mariadb.url", "not-a-valid-jdbc-url");
    //        AirportsSource src = new AirportsSource();
    //        src.initialize();
    //    } finally {
    //        if (originalUrl != null) {
    //            System.setProperty("mariadb.url", originalUrl);
    //        } else {
    //            System.clearProperty("mariadb.url");
    //        }
    //    }
    //}

    //@Test
    //@DisplayName("jsibold: AirportsSource.initialize() establishes a connection if database is reachable")
    //public void testInitializeConnects() {
    //    AirportsSource src = new AirportsSource();
    //    src.initialize();
   // }

    @Test
    @DisplayName("jsibold: AirportsSource.select() works")
    public void testSelectWhenConnected() throws Exception {
        AirportsSource src = new AirportsSource();
        Place place = new Place("40.5", "-105.1");
        Places selectResults = src.near(place, 50, 3959.0, 1);
        assertNotNull(selectResults);
    }

    // This test is still valid, I'm just not sure how to force a null connection without initialize()
    //@Test
    //@DisplayName("jsibold: select() returns null when connection is null")
    //public void testSelectWithNullConnection() {
    //    AirportsSource src = new AirportsSource();
    //    Place place = new Place("40.5", "-105.1");
    //    Places selectResults = src.near(place, 50, 3959.0, 1);
    //    assertNull(selectResults);
    //}

    @Test
    @DisplayName("jsibold: select() finds airports near Fort Collins")
    public void testSelectNearFortCollins() throws Exception {
        AirportsSource src = new AirportsSource();
        Place fortCollins = new Place("40.585", "-105.084");
        Places selectResults = src.find("fortCollins", 1);
        assertNotNull(selectResults);
    }

    @Test
    @DisplayName("jsibold: select() with different earth radius (kilometers)")
    public void testSelectWithKilometers() throws Exception {
        AirportsSource src = new AirportsSource();
        Place place = new Place("40.5", "-105.1");
        Places selectResults = src.near(place, 50, 6371.0, 1);
        assertNotNull(selectResults);
    }

    @Test
    @DisplayName("jsibold: select() with very small distance may find nothing")
    public void testSelectSmallDistance() throws Exception {
        AirportsSource src = new AirportsSource();
        Place place = new Place("0.0", "0.0"); 
        Places selectResults = src.near(place, 1, 3959.0, 1); 
        assertNotNull(selectResults);    
    }

    @Test@DisplayName("vercauteren: convert() handles columns for ID, name, municipality, country, region, lat and long")
    public void testConvertCols() throws Exception{
        AirportsSource src = new AirportsSource();
        Place nullIsland = new Place("0.0", "0.0"); 
        Places places = src.near(nullIsland, 10000, 395.0, 1); 

        assertTrue(places.get(0).get("ident") instanceof String);
        assertTrue(places.get(0).get("name") instanceof String);
        assertTrue(places.get(0).get("municipality") instanceof String);
        assertTrue(places.get(0).get("country") instanceof String);
        assertTrue(places.get(0).get("region") instanceof String);
        assertTrue(places.get(0).get("latitude") instanceof String);
        assertTrue(places.get(0).get("longitude") instanceof String);
    }

    @Test
    @DisplayName("jsibold: selectMatch() successfully executes query with valid connection")
    public void testSelectMatchWithConnection() throws Exception {
        AirportsSource src = new AirportsSource();
        Places selectResults = src.find("Denver", 10);
        assertNotNull(selectResults);
    }

    // This test is still valid, I am juist unsure how to force a null connection without initialize()
    //@Test
    //@DisplayName("jsibold: selectMatch() handles null connection gracefully")
    //public void testSelectMatchNullConnection() {
    //    AirportsSource src = new AirportsSource();
    //    Places selectResults = src.find("test", 5);
    //    assertNull(selectResults);
    //}

    @Test
    @DisplayName("jsibold: selectMatch() searches airport ident field")
    public void testSelectMatchSearchesIdent() throws Exception {
        AirportsSource src = new AirportsSource();
        Places selectResults = src.find("DEN", 10);
        assertNotNull(selectResults);
    }

    @Test
    @DisplayName("jsibold: selectMatch() searches municipality field")
    public void testSelectMatchSearchesMunicipality() throws Exception {
        AirportsSource src = new AirportsSource();
        Places selectResults = src.find("Fort Collins", 10);
        assertNotNull(selectResults);
    }

    @Test
    @DisplayName("jsibold: selectMatch() respects limit parameter")
    public void testSelectMatchRespectsLimit() throws Exception {
        AirportsSource src = new AirportsSource();
        Places selectResults = src.find("airport", 1);
        assertNotNull(selectResults);
    }


    //@Test
    //@DisplayName("jsibold: selectMatch() handles exception with closed connection")
    //public void testSelectMatchWithClosedConnection() throws Exception {
    //    AirportsSource src = new AirportsSource();
        
    //    Field connectionField = AirportsSource.class.getDeclaredField("connection");
    //    connectionField.setAccessible(true);
    //    Connection conn = (Connection) connectionField.get(src);
    //    if (conn != null) {
    //        conn.close();
    //    }
        
    //    Places selectResults = src.find("test", 10);
    //    assertNull(selectResults);
    //}

    @Test
    @DisplayName("jsibold: countMatch() returns count of matching airports")
    public void testCountMatchReturnsCount() throws Exception {
        AirportsSource src = new AirportsSource();
        Integer count = src.countMatch("Denver");
        assertNotNull(count);
        assertTrue(count > 0);
    }

    //TODO: under new design, this connection is no longer null. 
    // @Test
    // @DisplayName("jsibold: countMatch() returns 0 with null connection")
    // public void testCountMatchNullConnection() throws Exception {
    //     AirportsSource src = new AirportsSource();
    //     Integer count = src.countMatch("test");
    //     assertNotNull(count);
    //     assertTrue(count == 0);
    // }

    @Test
    @DisplayName("jsibold: countMatch() searches all fields")
    public void testCountMatchSearchesAllFields() throws Exception {
        AirportsSource src = new AirportsSource();
        Integer count = src.countMatch("Colorado");
        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    @DisplayName("kyle-kirts: countMatch() returns 0 when no matches")
    public void testNoMathcesFound() throws Exception{
        AirportsSource src = new AirportsSource();
        Integer count = src.countMatch("Kalkd;SDLKHF;adf");
        assertEquals(0, count);
    }

    @Test
    @DisplayName("kyle-kirts: find() isn't null for 'Colorado'")
    public void testFindColorado(){
        AirportsSource src = new AirportsSource();
        Places findResults = src.find("Colorado", 5);

        assertNotNull(findResults);
    }

    //TODO: rework this test with TWR block instead of initialize?
    // @Test
    // @DisplayName("jsibold: countMatch() handles exception gracefully")
    // public void testCountMatchHandlesException() throws Exception {
    //     AirportsSource src = new AirportsSource();
        
    //     Field connectionField = AirportsSource.class.getDeclaredField("connection");
    //     connectionField.setAccessible(true);
    //     Connection conn = (Connection) connectionField.get(src);
    //     if (conn != null) {
    //         conn.close();
    //     }
        
    //     Integer count = src.countMatch("test");
    //     assertNotNull(count);
    //     assertTrue(count == 0);
    // }

}
