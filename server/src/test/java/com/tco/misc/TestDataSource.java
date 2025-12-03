package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestDataSource {


    @Test        
    @DisplayName("luzovich: find() with gibberish returns empty Places")
    public void testGibberishYieldsEmptyPlacesFromFind() throws Exception {
        DataSource datasource = SourceFactory.get("cities");
        assertEquals(0, datasource.find("some random gibberish", 1).size());
    }

    @Test
    @DisplayName("luzovich: Cap off find() with many results")
    public void testLimitOfFindWithManyResults() throws Exception {
        DataSource datasource = new CitiesSource();
        assertEquals(12, datasource.find("Dave", 12).size());
    }

    @Test
    @DisplayName("kyle-kirts: limit greater than 100 is set to 100")
    public void testCappingLimit(){
        DataSource source = new CitiesSource();
        Integer limit = source.checkLimit(101);

        assertEquals(100, limit);
    }

    @Test
    @DisplayName("kyle-kirts: valid limit less than 100 remains the same")
    public void testCheckLimitValid(){
        DataSource source = new CitiesSource();
        Integer limit = source.checkLimit(99);

        assertEquals(99, limit);
    }

    @Test
    @DisplayName("jsibold: found() returns count from countMatch() on success")
    public void testFoundReturnsCountOnSuccess() throws Exception {
        DataSource src = new CitiesSource();
        Integer count = src.found("Denver");
        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    @DisplayName("jsibold: found() searches all fields via countMatch()")
    public void testFoundSearchesAllFields() throws Exception {
        DataSource src = new CitiesSource();
        Integer count = src.found("Colorado");
        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    @DisplayName("jsibold: found() returns non-zero for valid search term")
    public void testFoundReturnsNonZeroForValidTerm() throws Exception {
        DataSource src = new CitiesSource();
        Integer count = src.found("Dave");
        assertNotNull(count);
        assertTrue(count > 0);
    }

    @Test
    @DisplayName("jsibold: found() handles zero matches gracefully")
    public void testFoundHandlesZeroMatches() throws Exception {
        DataSource src = new CitiesSource();
        Integer count = src.found("xyznonexistentcity123");
        assertNotNull(count);
        assertTrue(count == 0);
    }

    @Test
    @DisplayName("jsibold: found() returns 0 on exception")
    public void testFoundReturnsZeroOnException() throws Exception {
        DataSource src = new CitiesSource();
        Integer count = src.found(null);
        assertNotNull(count);
        assertTrue(count == 0);
    }

    @Test
    @DisplayName("jsibold: found() catches and logs exceptions without throwing")
    public void testFoundCatchesExceptionWithoutThrowing() throws Exception {
        DataSource src = new CitiesSource();
        assertDoesNotThrow(() -> src.found(null));
    }

    @Test
    @DisplayName("jsibold: found() delegates to countMatch()")
    public void testFoundDelegatesToCountMatch() throws Exception {
        DataSource src = SourceFactory.get("cities");
        Integer count = src.found("some random gibberish");
        assertNotNull(count);
        assertTrue(count == 0);
    }

    @Test
    @DisplayName("jsibold: found() works with AirportsSource")
    public void testFoundWorksWithAirportsSource() throws Exception {
        DataSource src = new AirportsSource();
        Integer count = src.found("Denver");
        assertNotNull(count);
        assertTrue(count > 0);
    }
}
