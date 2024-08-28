package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.ItemPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {
    private static final Logger LOGGER = Logger.getLogger(CommentTest.class.getName());
    private Item item = new Item("630f279157e11558b2332832", "too much work", "SAD");

    @BeforeAll
    void setUp(){ item = new Item(new ItemPojo("630f279157e11558b2332832", "too much work", "SAD")); }

    @Test
    void testGetId() {
        LOGGER.info("Item Get Id Test");
        assertNull(item.getId());
    }

    @Test
    void testGetRetrospectiveId() {
        LOGGER.info("Item Get Retrospective Id Test");
        assertThat(item.getRetrospectiveId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testGetDescription() {
        LOGGER.info("Item Get Description Test");
        assertThat(item.getDescription().equals("too much work"));
    }

    @Test
    void testGetCategory() {
        LOGGER.info("Item Get Category Test");
        assertThat(item.getCategory().equals("SAD"));
    }
}