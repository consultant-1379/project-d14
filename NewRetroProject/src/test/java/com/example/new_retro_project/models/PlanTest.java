package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.PlanPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlanTest {

    private static final Logger LOGGER = Logger.getLogger(PlanTest.class.getName());
    private Plan plan = new Plan("630f279157e11558b2332832");

    @BeforeAll
    void setUp(){ plan = new Plan(new PlanPojo("630f279157e11558b2332832")); }

    @Test
    void testGetId() {
        LOGGER.info("Plan Get Id Test");
        assertNull(plan.getId());
    }

    @Test
    void testGetRetrospectiveId() {
        LOGGER.info("Plan Get Retrospective Id Test");
        assertThat(plan.getRetrospectiveId().equals("630f279157e11558b2332832"));
    }
}