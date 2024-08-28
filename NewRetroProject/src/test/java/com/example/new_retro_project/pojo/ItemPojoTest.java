package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemPojoTest {

    private static final Logger LOGGER = Logger.getLogger(ItemPojoTest.class.getName());
    private ItemPojo itemPojo = new ItemPojo("630f27478a45b8075df6fb7b", "description", "SAD");

    @Test
    void testGetId() {
        LOGGER.info("Item POJO Get ID Test");
        assertNull(itemPojo.getId());
    }

    @Test
    void testGetRetrospectiveId() {
        LOGGER.info("Item POJO Get Retrospective ID Test");
        assertThat(itemPojo.getRetrospectiveId().equals("630f27478a45b8075df6fb7b"));
    }

    @Test
    void testGetDescription() {
        LOGGER.info("Item POJO Get Description Test");
        assertThat(itemPojo.getDescription().equals("description"));
    }

    @Test
    void testSetDescription() {
        LOGGER.info("Item POJO Set Description Test");
        itemPojo.setDescription("description2");
        assertThat(itemPojo.getDescription().equals("description2"));
        itemPojo.setDescription("description");
    }

    @Test
    void testGetCategory() {
        LOGGER.info("Item POJO Get Category Test");
        assertThat(itemPojo.getCategory().equals("SAD"));
    }

    @Test
    void testSetCategory() {
        LOGGER.info("Item POJO Set Category Test");
        itemPojo.setCategory("MAD");
        assertThat(itemPojo.getCategory().equals("MAD"));
        itemPojo.setCategory("GLAD");
        assertThat(itemPojo.getCategory().equals("GLAD"));
        itemPojo.setCategory("");
        assertThat(itemPojo.getCategory().equals("GLAD"));
        itemPojo.setCategory("SAD");
    }

    @Test
    void testToString() {
        LOGGER.info("Item POJO toString Test");
        assertThat(itemPojo.toString().equals("Item{id=, retrospectiveId=630f27478a45b8075df6fb7b, description='description', category='SAD'}"));
    }
}