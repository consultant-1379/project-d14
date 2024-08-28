package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Retrospective;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class RetrospectiveRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(PersonRepositoryTest.class.getName());

    private final RetrospectiveRepository retrospectiveRepository;
    private static String id;


    @Autowired
    RetrospectiveRepositoryTest(RetrospectiveRepository retrospectiveRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
    }

    @Test
    void testRetrospectiveRepositoryAdd() {
        LOGGER.info("Retrospective Repository Add Test");
        try {
            id = retrospectiveRepository.save(new Retrospective("teamId")).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(retrospectiveRepository.findById(id).orElse(null).getTeamId().equals("teamId"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testRetrospectiveRepositoryDelete() {
        LOGGER.info("Retrospective Repository Delete Test");
        try {
            retrospectiveRepository.delete(retrospectiveRepository.findById(id).orElse(null));
            assert(retrospectiveRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}