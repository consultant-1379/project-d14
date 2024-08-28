package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamPojoTest {

    private static final Logger LOGGER = Logger.getLogger(TeamPojoTest.class.getName());
    private TeamPojo teamPojo = new TeamPojo("Anonymous");

    @BeforeAll
    void setUp(){ teamPojo = new TeamPojo("Anonymous", new String[] {"John Doe", "Mary Doe"}); }

    @Test
    void test1GetId() {
        LOGGER.info("Team POJO Get Id Test");
        assertNull(teamPojo.getId());
    }

    @Test
    void test2GetName() {
        LOGGER.info("Team POJO Get Name Test");
        assertThat(teamPojo.getName().equals("Anonymous"));
    }

    @Test
    void test3SetName() {
        LOGGER.info("Team POJO Set Name Test");
        teamPojo.setName("NewAnonymous");
        assertThat(teamPojo.getName().equals("NewAnonymous"));
        teamPojo.setName("Anonymous");
    }

    @Test
    void test4GetTeamMembers() {
        LOGGER.info("Team POJO Get Team Members Test");
        List<String> expected = Arrays.asList("John Doe", "Mary Doe");
        assertIterableEquals(expected, teamPojo.getTeamMembers());
    }

    @Test
    void test5AddTeamMembers() {
        LOGGER.info("Team POJO Add Team Members Test");
        teamPojo.addTeamMembers(new String[] {"Bob Smith"});
        List<String> expected = Arrays.asList("John Doe", "Mary Doe", "Bob Smith");
        assertIterableEquals(expected, teamPojo.getTeamMembers());
    }

    @Test
    void test6ToString() {
        LOGGER.info("Team POJO toString Test");
        assertThat(teamPojo.toString().equals("Team{id=, name='Anonymous', teamMembers=[John Doe, Mary Doe, Bob Smith]}"));
    }
}