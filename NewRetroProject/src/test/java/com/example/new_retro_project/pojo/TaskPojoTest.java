package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskPojoTest {

    private static final Logger LOGGER = Logger.getLogger(TaskPojoTest.class.getName());
    private TaskPojo taskPojo = new TaskPojo("630f279157e11558b2332832", "fix issues");

    @Test
    void testGetId() {
        LOGGER.info("Task POJO Get ID Test");
        assertNull(taskPojo.getId());
    }

    @Test
    void testGetPlanId() {
        LOGGER.info("Task POJO Get Plan ID Test");
        assertThat(taskPojo.getPlanId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testGetDescription() {
        LOGGER.info("Task POJO Get Description Test");
        assertThat(taskPojo.getDescription().equals("fix issues"));
    }

    @Test
    void testSetDescription() {
        LOGGER.info("Task POJO Set Description Test");
        taskPojo.setDescription("fix issues 2");
        assertThat(taskPojo.getDescription().equals("fix issues 2"));
        taskPojo.setDescription("fix issues");
    }

    @Test
    void testToString() {
        LOGGER.info("Task POJO toString Test");
        assertThat(taskPojo.toString().equals("Task{id=, planId=630f279157e11558b2332832, description='fix issues'}"));
    }
}