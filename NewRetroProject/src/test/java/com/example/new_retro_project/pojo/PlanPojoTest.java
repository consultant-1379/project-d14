package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlanPojoTest {

    private static final Logger LOGGER = Logger.getLogger(PlanPojoTest.class.getName());
    private PlanPojo planPojo = new PlanPojo("630f27478a45b8075df6fb7b");

    @Test
    void testGetId() {
        LOGGER.info("Plan POJO Get ID Test");
        assertNull(planPojo.getId());
    }

    @Test
    void testGetRetrospectiveId() {
        LOGGER.info("Plan POJO Get Retrospective ID Test");
        assertThat(planPojo.getRetrospectiveId().equals("630f27478a45b8075df6fb7b"));
    }

    @Test
    void testToString() {
        LOGGER.info("Plan POJO toString Test");
        assertThat(planPojo.toString().equals("Plan{id=, retrospectiveId='630f27478a45b8075df6fb7b'}"));
    }
}