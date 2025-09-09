package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestConfigRequest {

    private ConfigRequest conf;

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new ConfigRequest();
        conf.buildResponse();
    }

    @Test
    @DisplayName("base: Request type is \"config\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("config", type);
    }

    @Test
    @DisplayName("base: Features includes \"config\"")
    public void testFeatures(){
        assertTrue(conf.validFeature("config"));
    }

    @Test
    @DisplayName("base: Features list is expected length")
    public void testFeaturesLength(){
        assertEquals(conf.listFeatures().size(), 1);
    }

    /*
    @Test
    @DisplayName("base: Team name is correct")
    public void testServerName() {
        String name = conf.getServerName();
        assertEquals("Team Name", name);
    }

    @Test
    @DisplayName("base: Team number is correct")
    public void testTeamNumber() {
        String teamNumber = conf.getTeamNumber();
        assertEquals("t00", teamNumber);
    }
    */

    @Test
    @DisplayName("base: Mission statement is correct")
    public void testMissionStatement() {
        String missionStatement = conf.getMissionStatement();
        assertEquals("Our objective is to be a welcoming team; we invite communication, transparency, and empathy. We will do this by leveraging in-person dialogue and including all members of the team as much as possible. Our success is not only code-based, but also highly dependent on our ability to communicate with one another. In addition to technical skills (e.g., Slack, Git, Java, Restful API's), we will strive to improve our soft-skills (e.g., communication, collaboration, organization, time management, adaptability, and others). We endeavor to be mindful of shortcomings and use them as opportunities. Our differences should be our strengths, not our weaknesses.",
                     missionStatement);
    }

    /*
    @Test
    @DisplayName("base: People list is expected length")
    public void testPeopleLength(){
        assertEquals(conf.getPeople().size(), 5);
    }
    */

}