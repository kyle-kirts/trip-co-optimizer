package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportsSource extends DataSource {
    
    ResultSet selectResults;
    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = System.getProperty("mariadb.url", "jdbc:mariadb://faure.cs.colostate.edu:3306/cs314");
    }

    @Override
    public Places near(Place place, Integer distance, Double earthRadius, Integer limit) {
        results = new Places();

        try (Connection connection = DriverManager.getConnection(Credential.URL, Credential.USER, Credential.PASSWORD)) {
            selectNear(place, distance, earthRadius, checkLimit(limit), connection);
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    @Override
    public Places find(String match, Integer limit) {
        results = new Places();

        try (Connection connection = DriverManager.getConnection(Credential.URL, Credential.USER, Credential.PASSWORD)) {
            selectMatch(match, limit, connection);
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    public void selectNear(Place place, Integer distance, Double earthRadius, Integer limit, Connection conn) {
        if (conn == null) return;
        try {
            double lon = Double.parseDouble(place.get("longitude"));
            double lat = Double.parseDouble(place.get("latitude"));
            Integer distanceInMeters = (int)(distance * (6371000.0 / earthRadius));

            String sql =
                "SELECT a.ident, a.name, a.municipality, r.name AS region, c.name AS country, " +
                "a.latitude_deg AS latitude, a.longitude_deg AS longitude " +
                "FROM airports a " +
                "JOIN regions r ON a.iso_region = r.code " +
                "JOIN countries c ON a.iso_country = c.code " +
                "WHERE ST_Distance_Sphere(POINT(a.longitude_deg, a.latitude_deg), POINT(?, ?)) < ? " +
                "LIMIT " + limit + ";";

            var stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lon);
            stmt.setDouble(2, lat);
            stmt.setDouble(3, distanceInMeters);
            this.selectResults = stmt.executeQuery();
        } catch (Exception e) {
            this.selectResults = null;
        }
    }

    public void selectMatch(String match, Integer limit, Connection conn) {
        if (conn == null) return;
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

            var stmt = conn.prepareStatement(sql);
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
    public Places convert() throws SQLException {
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

    @Override
    public Integer countMatch(String match) throws SQLException {
        if (connection == null) return 0;
        try {
            String sql =
                "SELECT COUNT(*) AS total " +
                "FROM airports a " +
                "JOIN regions r ON a.iso_region = r.code " +
                "JOIN countries c ON a.iso_country = c.code " +
                "WHERE a.ident LIKE ? OR a.name LIKE ? OR a.municipality LIKE ? " +
                "OR r.name LIKE ? OR c.name LIKE ?;";

            var stmt = connection.prepareStatement(sql);
            String pattern = "%" + match + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            stmt.setString(4, pattern);
            stmt.setString(5, pattern);
            var results = stmt.executeQuery();
            if (results.next()) {
                return results.getInt("total");
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
}
