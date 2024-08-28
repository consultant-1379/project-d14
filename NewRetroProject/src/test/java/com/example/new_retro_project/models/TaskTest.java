package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.TaskPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskTest {

    private static final Logger LOGGER = Logger.getLogger(TaskTest.class.getName());
    private Task task = new Task("630f279157e11558b2332832", "fix issues");

    @BeforeAll
    void setUp(){ task = new Task(new TaskPojo("630f279157e11558b2332832", "fix issues")); }

    @Test
    void testGetId() {
        LOGGER.info("Task Get Id Test");
        assertNull(task.getId());
    }

    @Test
    void testGetPlanId() {
        LOGGER.info("Task Get Plan Id Test");
        assertThat(task.getPlanId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testGetDescription() {
        LOGGER.info("Task Get Description Test");
        assertThat(task.getDescription().equals("fix issues"));
    }
}