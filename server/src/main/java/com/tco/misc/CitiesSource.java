package com.tco.misc;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

    @Override
    public void initialize() {
        MongoClient mongoClient = MongoClients.create(Credential.URL);
        MongoDatabase database = mongoClient.getDatabase("cs314");
        this.collection = database.getCollection("cities");
    }


    @Override
    public void select(Place place, double distance, long earthRadius, int limit){
        Bson filter = nearFilter(place, distance, earthRadius);
        this.selectResults = collection.find(filter).limit(limit);
    }

    @Override
    public void selectMatch(String match, int limit)
    {
        Bson filter = matchFilter(match);
        this.selectResults = collection.find(filter).limit(limit);
    }

    //based on guide
    public Bson nearFilter(Place place, double distance, long earthRadius)
    {
        Double dist = Double.valueOf(distance);
        Double lon = Double.valueOf(place.get("longitude"));
        Double lat = Double.valueOf(place.get("latitude"));
        Point point = new Point(new Position(lon, lat));
        return Filters.nearSphere("location", point, dist, 0.0);
    }

    public Bson matchFilter(String matchRegex){
        return Filters.or(
            Filters.regex("city", matchRegex, "i"),
            Filters.regex("country", matchRegex, "i")
        );
    }

}
