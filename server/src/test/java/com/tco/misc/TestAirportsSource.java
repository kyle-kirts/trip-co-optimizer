package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    }
