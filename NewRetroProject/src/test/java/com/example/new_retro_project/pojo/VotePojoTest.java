package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VotePojoTest {

    private static final Logger LOGGER = Logger.getLogger(VotePojoTest.class.getName());
    private VotePojo votePojo = new VotePojo("john.doe@ericsson.com", "630f279157e11558b2332832", true);

    @Test
    void testGetId() {
        LOGGER.info("Vote POJO Get ID Test");
        assertNull(votePojo.getId());
    }

    @Test
    void testGetEmail() {
        LOGGER.info("Vote POJO Get Email Test");
        assertThat(votePojo.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testSetEmail() {
        LOGGER.info("Vote POJO Set Email Test");
        votePojo.setEmail("mary.doe@ericsson.com");
        assertThat(votePojo.getEmail().equals("mary.doe@ericsson.com"));
        votePojo.setEmail("john.doe@ericsson.com");
    }

    @Test
    void testGetItemId() {
        LOGGER.info("Vote POJO Get Item ID Test");
        assertThat(votePojo.getItemId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testIsVote() {
        LOGGER.info("Vote POJO Is Vote Test");
        assertEquals(true, votePojo.isVote());
    }

    @Test
    void testSetVote() {
        LOGGER.info("Vote POJO Set Vote Test");
        votePojo.setVote(false);
        assertEquals(false, votePojo.isVote());
        votePojo.setVote(true);
    }

    @Test
    void testToString() {
        LOGGER.info("Vote POJO toString Test");
        assertThat(votePojo.toString().equals("Vote{id=, email='john.doe@ericsson.com', itemId='630f279157e11558b2332832', vote=true}"));
    }
}