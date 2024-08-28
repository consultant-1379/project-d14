package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Task;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TaskRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(TaskRepositoryTest.class.getName());

    private final TaskRepository taskRepository;
    private static String id;


    @Autowired
    TaskRepositoryTest(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    void testTaskRepositoryAdd() {
        LOGGER.info("Task Repository Add Test");
        try {
            id = taskRepository.save(new Task("planId", "Fix Git issues")).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(taskRepository.findById(id).orElse(null).getDescription().equals("Fix Git issues"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testTaskRepositoryDelete() {
        LOGGER.info("Task Repository Delete Test");
        try {
            taskRepository.delete(taskRepository.findById(id).orElse(null));
            assert(taskRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}