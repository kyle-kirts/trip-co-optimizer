package com.tco.misc;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import java.sql.SQLException;
import java.util.Map;

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

        static final String URL = String.format("mongodb://%s:%s@cilantro:%d/?authSource=cs314", USER, PASSWORD, PORT);
    }

    
    public Places near(Place place, Integer distance, Double earthRadius, Integer limit) {
        results = new Places();

        try (MongoClient mongoClient = MongoClients.create(Credential.URL)) {
            MongoDatabase database = mongoClient.getDatabase("cs314");
            this.collection = database.getCollection("cities");
            selectNear(place, distance, earthRadius, checkLimit(limit));
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    public Places find(String match, Integer limit) {
        results = new Places();

        try(MongoClient mongoClient = MongoClients.create(Credential.URL)) {
            MongoDatabase database = mongoClient.getDatabase("cs314");
            this.collection = database.getCollection("cities");
            selectMatch(match, limit);
            results = convert();
            return results;
        }
        catch (Exception e) {
            return results;
        }
    }

    public void selectNear(Place place, Integer distance, Double earthRadius, Integer limit) throws Exception {
        Bson filter = nearFilter(place, distance, earthRadius);
        this.selectResults = collection.find(filter).limit(limit);
    }
    
    public void selectMatch(String match, Integer limit) throws Exception
    {
        Bson filter = matchFilter(match);
        this.selectResults = collection.find(filter).limit(limit);
    }

    //based on guide
    public Bson nearFilter(Place place, Integer distance, Double earthRadius)
    {
        Integer distanceInMeters = (int)(distance * (6371000.0 / earthRadius));
        Double dist = Double.valueOf(distanceInMeters);
        Double lon = Double.valueOf(place.get("longitude"));
        Double lat = Double.valueOf(place.get("latitude"));
        Point point = new Point(new Position(lon, lat));
        return Filters.nearSphere("location", point, dist, 0.0);
    }

    public Places convert() {
         Map<String, String> fields = Map.of(
                    "country",          "country",
                    "lng",              "longitude",
                    "lat",              "latitude",
                    "city",             "municipality",
                    "admin_name_ascii", "region",
                    "city_ascii",       "name"
        );

        for (Document doc : selectResults) {
            Place place = new Place();
            for(Map.Entry<String, Object> entry : doc.entrySet()){
                    if (entry.getValue() == null) continue;
                    String mongoField = entry.getKey();
                    if (!fields.containsKey(mongoField)) continue;
                    String placeField = fields.get(mongoField);
                    place.put(placeField, entry.getValue().toString());
            }
            results.add(place);
        }

        return results;
    }

    public Bson matchFilter(String matchRegex){
        return Filters.or(
            Filters.regex("city", matchRegex, "i"),
            Filters.regex("country", matchRegex, "i")
        );
    }

}
