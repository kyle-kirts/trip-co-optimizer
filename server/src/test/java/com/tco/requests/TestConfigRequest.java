package com.tco.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestConfigRequest {

    private ConfigRequest conf;

    @BeforeEach
    public void createConfigurationForTestCases() {
        conf = new ConfigRequest();
        try {
            conf.buildResponse();
        } catch (Exception e) {
            fail("buildResponse threw an exception: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("base: Request type is \"config\"")
    public void testType() {
        String type = conf.getRequestType();
        assertEquals("config", type);
    }

    @Test
    @DisplayName("base: Features includes \"config\"")
    public void testFeaturesConfigExists() {
        assertTrue(conf.validFeature("config"));
    }

    @Test
    @DisplayName("luzovich: Features includes \"distances\"")
    public void testFeaturesDistancesExists() {
        assertTrue(conf.validFeature("distances"));
    }

    @Test
    @DisplayName("luzovich: Features includes \"tour\"")
    public void testFeaturesTourExists() {
        assertTrue(conf.validFeature("tour"));
    }

    @Test
    @DisplayName("luzovich: Features includes \"near\"")
    public void testFeaturesNearExists() {
       assertTrue(conf.validFeature("near"));
    }

    @Test
    @DisplayName("luzovich: Features includes \"find\"")
    public void testFeaturesFindExists() {
       assertTrue(conf.validFeature("find"));
    }

    @Test
    @DisplayName("luzovich: Removing distance-based fields won't return allowed formulas")
    public void testNonExistentDistancesNoSupportedFormulae() {
       conf.features.remove("distances");
       conf.features.remove("tour");
       conf.features.remove("near");
       assertNull(conf.listFormulae());
    }

    @Test
    @DisplayName("luzovich: \"distances\" double-implies \"formulae\"")
    public void testFeatureDistancesDoubleImpliesFormulae() {
        // Not either or both; if we have one we must have the other.
        assertTrue(
            !(conf.validFeature("distances") || conf.hasProperty("formulae")) ||
            (conf.validFeature("distances") && conf.hasProperty("formulae"))
        );
    }

    @Test
    @DisplayName("luzovich: Removing dataset-based fields won't return sources list")
    public void testNonExistentDatasetQueriesNoSourcesList() {
        conf.features.remove("near");
        assertNull(conf.listSources());
    }

    @Test
    @DisplayName("luzovich: \"near\" double-implies \"sources\"")
    public void testFeatureNearDoubleImpliesSource() {
        // Not either or both; if we have one we must have the other.
        assertTrue(
            !(conf.validFeature("near") || conf.hasProperty("sources")) ||
            (conf.validFeature("near") && conf.hasProperty("sources"))
        );
    }
    
    @Test
    @DisplayName("luzovich: Features list is expected length")
    public void testFeaturesLength() {
        assertEquals(conf.listFeatures().size(), 5);
    }

    @Test
    @DisplayName("luzovich: hasProperty() for random value is false")
    public void testRandomFieldOnHasPropertyReturnsFalse() {
        assertFalse(conf.hasProperty("some random thing goes here"));
    }
}
