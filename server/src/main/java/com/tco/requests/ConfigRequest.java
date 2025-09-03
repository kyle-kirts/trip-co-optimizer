package com.tco.requests;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class ConfigRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private List<String> features;
    private Team team;
    private People people;

    @Override
    public void buildResponse() {

        processAboutFile();
        features = listFeatures();

        log.trace("buildResponse -> {}", this);
    }

    public List<String> listFeatures() {
        features = new ArrayList<>();
        features.add("config");
        return features;
    }

    private void processAboutFile() {
        try {
            final String teamAboutFile = "/data/about.json";
            InputStream aboutInputStream = ConfigRequest.class.getResourceAsStream(teamAboutFile);
            if(aboutInputStream == null) log.error("File Not Found - {}", teamAboutFile);
            InputStreamReader aboutInputStreamReader = new InputStreamReader(aboutInputStream);
            //JSONValidator.validate(aboutInputStreamReader, AboutFile.class);
            AboutFile about = new Gson().fromJson(aboutInputStreamReader, AboutFile.class);
            team = about.team;
            people = about.people;
        } catch (JsonIOException e) {
            log.error("Bad Format - {}", e.getMessage());
        }
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public ConfigRequest() {
        this.requestType = "config";
    }

    //TODO might need a few testing helpers for team, people, person

    public boolean validFeature(String feature){
        return features.contains(feature);
    }
}
