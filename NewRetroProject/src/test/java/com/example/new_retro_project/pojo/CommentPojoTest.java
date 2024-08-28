package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommentPojoTest {

    private static final Logger LOGGER = Logger.getLogger(CommentPojoTest.class.getName());
    private CommentPojo commentPojo = new CommentPojo("itemId", "john.doe@ericsson.com", "some comment");

    @Test
    void testGetId() {
        LOGGER.info("Comment POJO Get ID Test");
        assertNull(commentPojo.getId());
    }

    @Test
    void testGetItemId() {
        LOGGER.info("Comment POJO Get Item ID Test");
        assertThat(commentPojo.getItemId().equals("itemId"));
    }

    @Test
    void testGetEmail() {
        LOGGER.info("Comment POJO Get Email Test");
        assertThat(commentPojo.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testSetEmail() {
        LOGGER.info("Comment POJO Set Email Test");
        commentPojo.setEmail("mary.doe@ericsson.com");
        assertThat(commentPojo.getEmail().equals("mary.doe@ericsson.com"));
        commentPojo.setEmail("john.doe@ericsson.com");
    }

    @Test
    void testGetComment() {
        LOGGER.info("Comment POJO Get Comment Test");
        assertThat(commentPojo.getComment().equals("some comment"));
    }

    @Test
    void testSetComment() {
        LOGGER.info("Comment POJO Set Comment Test");
        commentPojo.setComment("comment2");
        assertThat(commentPojo.getComment().equals("comment2"));
        commentPojo.setEmail("some comment");
    }

    @Test
    void testToString() {
        LOGGER.info("Comment POJO toString Test");
        assertThat(commentPojo.toString().equals("Comment{id=, itemId=itemId, email='john.doe@ericsson.com', comment='some comment'}"));
    }
}