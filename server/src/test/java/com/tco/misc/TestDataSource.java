package com.tco.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
