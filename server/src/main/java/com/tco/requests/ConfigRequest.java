package com.tco.requests;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.tco.misc.InternalRequestException;
import com.tco.misc.JSONReader;
import com.tco.misc.RequestException;
import com.tco.misc.AboutFile;
import com.tco.misc.People;
import com.tco.misc.Team;

public class ConfigRequest extends Request {

    private static final transient Logger log = LoggerFactory.getLogger(ConfigRequest.class);

    private List<String> features;
    private Team team;
    private People people;

    @Override
    public void buildResponse() throws RequestException {
        processAboutFile();
        features = listFeatures();
        log.trace("buildResponse -> {}", this);
    }

    public List<String> listFeatures() {
        features = new ArrayList<>();
        features.add("config");
        return features;
    }

    private void processAboutFile() throws InternalRequestException {
        final String teamAboutFile = "/data/about.json";
        String aboutString = JSONReader.fetchValidatedJSONFile(teamAboutFile, AboutFile.class);
        AboutFile about = new Gson().fromJson(aboutString, AboutFile.class);
        team = about.team;
        people = about.people;
    }

    /*
     * The following methods exist only for testing purposes and are not used
     * during normal execution, including the constructor.
     */

    public ConfigRequest() {
        this.requestType = "config";
    }

    // TODO might need a few testing helpers for team, people, person

    public boolean validFeature(String feature) {
        return features.contains(feature);
    }
}
