package com.tco.misc;

import java.util.Arrays;
import java.util.List;

public class SourceFactory{
    
    public static DataSource get(String source) {
        DataSource dataSource;

        if (source == null || source == "cities") {
            dataSource = new CitiesSource();
        }
        else {
            dataSource = new AirportsSource();
        }

        return dataSource;
    }

    public static List<String> getSupportedSources() {
        return Arrays.asList("cities", "airports");
    }

}