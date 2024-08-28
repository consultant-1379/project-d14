package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.TeamPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamTest {

    private static final Logger LOGGER = Logger.getLogger(TeamTest.class.getName());
    private Team team = new Team("Anonymous", new String[] {"John Doe", "Mary Doe"});

    @BeforeAll
    void setUp(){ team = new Team(new TeamPojo("Anonymous", new String[] {"John Doe", "Mary Doe"})); }

    @Test
    void testGetId() {
        LOGGER.info("Team Get Id Test");
        assertNull(team.getId());
    }

    @Test
    void testGetName() {
        LOGGER.info("Team Get Name Test");
        assertThat(team.getName().equals("Anonymous"));
    }

    @Test
    void testGetTeamMembers() {
        LOGGER.info("Team Get Team Members Test");
        List<String> expected = Arrays.asList("John Doe", "Mary Doe");
        assertIterableEquals(expected, team.getTeamMembers());
    }

    @Test
    void testGetRetrospectives() {
        LOGGER.info("Team Get Retrospectives Test");
        assertEquals(null, team.getRetrospectives());
    }

    @Test
    void testGetPersons() {
        LOGGER.info("Team Get Persons Test");
        assertEquals(null, team.getPersons());
    }
}