package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.VotePojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VoteTest {

    private static final Logger LOGGER = Logger.getLogger(VoteTest.class.getName());
    private Vote vote = new Vote("john.doe@ericsson.com", "630f279157e11558b2332832", true);

    @BeforeAll
    void setUp(){ vote = new Vote(new VotePojo("john.doe@ericsson.com", "630f279157e11558b2332832", true)); }

    @Test
    void testGetId() {
        LOGGER.info("Vote Get Id Test");
        assertNull(vote.getId());
    }

    @Test
    void testGetEmail() {
        LOGGER.info("Vote Get Email Test");
        assertThat(vote.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testGetItemId() {
        LOGGER.info("Vote Get Email Test");
        assertThat(vote.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testIsVote() {
        LOGGER.info("Vote Is Vote Test");
        assertEquals(true, vote.isVote());
    }
}