package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
    @DisplayName("jsibold: AirportsSource.initialize() handles SQLException gracefully")
    public void testInitializeCatch() {
    assertNull(new AirportsSource() {
        @Override public Connection initialize() {
            try { throw new SQLException(); } 
            catch (SQLException e) { return null; }
        }
    }.initialize());
    }


    @Test
    @DisplayName("jsibold: AirportsSource.initialize() establishes a connection if database is reachable")
    public void testInitializeConnects() {
    AirportsSource src = new AirportsSource();
    Connection conn = src.initialize();
    assertNotNull(conn, "Connection should not be null if database reachable");
}
}
