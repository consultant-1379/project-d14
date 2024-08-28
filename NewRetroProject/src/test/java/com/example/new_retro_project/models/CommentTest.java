package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.CommentPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentTest {

    private static final Logger LOGGER = Logger.getLogger(CommentTest.class.getName());
    private Comment comment = new Comment("630f279157e11558b2332832", "john.doe@ericsson.com", "agree");

    @BeforeAll
    void setUp(){ comment = new Comment(new CommentPojo("630f279157e11558b2332832", "john.doe@ericsson.com", "agree")); }

    @Test
    void testGetId() {
        LOGGER.info("Comment Get Id Test");
        assertNull(comment.getId());
    }

    @Test
    void testGetItemId() {
        LOGGER.info("Comment Get Item Id Test");
        assertThat(comment.getItemId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testGetEmail() {
        LOGGER.info("Comment Get Email Test");
        assertThat(comment.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testGetComment() {
        LOGGER.info("Comment Get Email Test");
        assertThat(comment.getComment().equals("agree"));
    }
}