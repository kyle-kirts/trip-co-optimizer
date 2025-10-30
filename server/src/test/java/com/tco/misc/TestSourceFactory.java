package com.tco.misc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSourceFactory{

      @Test
    @DisplayName("vercauteren: base existence for SourceFactory")
    public void testFactoryExists() {
        SourceFactory factory = new SourceFactory() {};
        assertNotNull(factory);
    }

    @Test
    @DisplayName("kyle-kirts: a null source gets a CitiesSource object")
    public void testGetNullSource(){
        String source = null;
        DataSource dataSource = SourceFactory.get(source);

        assertTrue(dataSource instanceof CitiesSource);
    }

    @Test
    @DisplayName("kyle-kirts: 'cities' source gets CitiesSource object")
    public void testGetCitiesSource() {
        String source = "cities";
        DataSource dataSource = SourceFactory.get(source);

        assertTrue(dataSource instanceof CitiesSource);
    }

    @Test
    @DisplayName("kyle-kirts: 'airports' source gets AirportsSource object")
    public void testGetAirportsSource() {
        String source = "airports";
        DataSource dataSource = SourceFactory.get(source);

        assertTrue(dataSource instanceof AirportsSource);
    }
}