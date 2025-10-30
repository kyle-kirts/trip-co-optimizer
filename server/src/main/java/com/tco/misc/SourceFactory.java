package com.tco.misc;

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
}