package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RetrospectivePojoTest {

    private static final Logger LOGGER = Logger.getLogger(RetrospectivePojoTest.class.getName());
    private RetrospectivePojo retrospectivePojo = new RetrospectivePojo("630f27478a45b8075df6fb7b");

    @Test
    void testGetId() {
        LOGGER.info("Retrospective POJO Get ID Test");
        assertNull(retrospectivePojo.getId());
    }

    @Test
    void testGetTeamId() {
        LOGGER.info("Retrospective POJO Get Team ID Test");
        assertThat(retrospectivePojo.getTeamId().equals("630f27478a45b8075df6fb7b"));
    }

    @Test
    void testIsComplete() {
        LOGGER.info("Retrospective POJO Is Complete Test");
        assertEquals(false, retrospectivePojo.isComplete());
    }

    @Test
    void testSetComplete() {
        LOGGER.info("Retrospective POJO Is Complete Test");
        retrospectivePojo.setComplete(true);
        assertEquals(true, retrospectivePojo.isComplete());
        retrospectivePojo.setComplete(false);
    }

    @Test
    void testToString() {
        LOGGER.info("Retrospective POJO toString Test");
        assertThat(retrospectivePojo.toString().equals("Retrospective{id=, teamId=630f27478a45b8075df6fb7b, complete=false}"));
    }
}