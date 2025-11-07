package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AirportsSource extends DataSource {

    @Override
    public Connection initialize() {
        try {
            String url = System.getProperty("mariadb.url", "jdbc:mariadb://faure.cs.colostate.edu:3306/cs314");
            String user = "cs314-db";
            String password = "REDACTED";
            return DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException e) {
            return null;
        }
    }
}
