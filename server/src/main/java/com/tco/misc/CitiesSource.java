package com.tco.misc;

import java.util.Map;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import org.bson.Document;
import org.bson.conversions.Bson;

public class CitiesSource extends DataSource{

    MongoCollection<Document> collection;
    FindIterable<Document> selectResults;

    //from guide wiki directly
    static class Credential {
        static final int PORT = 27017;
        // shared user with read-only access
        static final String USER = "cs314-db";
        static final String PASSWORD = "REDACTED";

        static final String URL = String.format("mongodb://%s:%s@black-bottle:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

    

    //based on guide
    public Bson newFilter(Place place, Long distance, Double earthRadius)
    {
        Double dist = Double.valueOf(distance);
        Double lon = Double.valueOf(place.get("longitude"));
        Double lat = Double.valueOf(place.get("latitude"));
        Point point = new Point(new Position(lon, lat));
        return Filters.nearSphere("location", point, dist, 0.0);
    }

}