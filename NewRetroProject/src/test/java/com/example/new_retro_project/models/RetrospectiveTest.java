package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.RetrospectivePojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RetrospectiveTest {

    private static final Logger LOGGER = Logger.getLogger(RetrospectiveTest.class.getName());
    private Retrospective retrospective = new Retrospective("630f279157e11558b2332832");

    @BeforeAll
    void setUp(){ retrospective = new Retrospective(new RetrospectivePojo("630f279157e11558b2332832")); }

    @Test
    void testGetId() {
        LOGGER.info("Retrospective Get Id Test");
        assertNull(retrospective.getId());
    }

    @Test
    void testGetTeamId() {
        LOGGER.info("Retrospective Get Team Id Test");
        assertThat(retrospective.getTeamId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testGetItems() {
        LOGGER.info("Retrospective Get Items Test");
        assertEquals(null, retrospective.getItems());
    }

    @Test
    void testIsComplete() {
        LOGGER.info("Retrospective Is Complete Test");
        assertEquals(false, retrospective.isComplete());
    }

    @Test
    void testToString() {
        LOGGER.info("Retrospective toString Test");
        assertThat(retrospective.toString().equals("Retrospective{id=, teamId=630f279157e11558b2332832, complete=false, items=null}"));
    }
}