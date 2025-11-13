package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportsSource extends DataSource {
    
    private Connection connection;
    ResultSet selectResults;

    @Override
    public void initialize() {
        results = new Places();
        try {
            String url = System.getProperty("mariadb.url", "jdbc:mariadb://faure.cs.colostate.edu:3306/cs314");
            String user = "cs314-db";
            String password = "REDACTED";
            connection = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException e) {
            connection = null;
        }
    }

    @Override
    public void selectNear(Place place, double distance, long earthRadius, int limit) {
        if (connection == null) return;
        try {
            double lon = Double.parseDouble(place.get("longitude"));
            double lat = Double.parseDouble(place.get("latitude"));
            double distanceInMeters = distance * (6371000.0 / earthRadius);

            String sql =
                "SELECT a.ident, a.name, a.municipality, r.name AS region, c.name AS country, " +
                "a.latitude_deg AS latitude, a.longitude_deg AS longitude " +
                "FROM airports a " +
                "JOIN regions r ON a.iso_region = r.code " +
                "JOIN countries c ON a.iso_country = c.code " +
                "WHERE ST_Distance_Sphere(POINT(a.longitude_deg, a.latitude_deg), POINT(?, ?)) < ? " +
                "LIMIT " + limit + ";";

            var stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, lon);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, distanceInMeters);
            this.selectResults = stmt.executeQuery();
        } catch (Exception e) {
            this.selectResults = null;
        }
    }

    @Override
    public void selectMatch(String match, int limit) {
        if (connection == null) return;
        try {
            String sql =
                "SELECT a.ident, a.name, a.municipality, r.name AS region, c.name AS country, " +
                "a.latitude_deg AS latitude, a.longitude_deg AS longitude " +
                "FROM airports a " +
                "JOIN regions r ON a.iso_region = r.code " +
                "JOIN countries c ON a.iso_country = c.code " +
                "WHERE a.ident LIKE ? OR a.name LIKE ? OR a.municipality LIKE ? " +
                "OR r.name LIKE ? OR c.name LIKE ? " +
                "LIMIT ?;";

            var stmt = connection.prepareStatement(sql);
            String pattern = "%" + match + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            stmt.setString(4, pattern);
            stmt.setString(5, pattern);
            stmt.setInt(6, limit);
            this.selectResults = stmt.executeQuery();
        } catch (Exception e) {
            this.selectResults = null;
        }
    }

    @Override
    public Places convert() throws Exception{
        String columns = "ident,name,municipality,region,country,latitude,longitude";
        int count = 0;
        String[] cols = columns.split(",");
        Places places = new Places();
        while (selectResults.next()) {
            Place place = new Place(selectResults.getString("latitude"), selectResults.getString("longitude"));
            for (String col : cols) {
                if(col.equals("latitude") || col.equals("longitude")) continue;
                place.put(col, selectResults.getString(col));
            }
            places.add(place);
        }
        return places;
    }
    

}
