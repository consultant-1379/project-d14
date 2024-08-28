package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Plan;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PlanRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(PlanRepositoryTest.class.getName());

    private final PlanRepository planRepository;
    private static String id;


    @Autowired
    PlanRepositoryTest(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Test
    void testPlanRepositoryAdd() {
        LOGGER.info("Plan Repository Add Test");
        try {
            id = planRepository.save(new Plan("retrospectiveId")).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(planRepository.findById(id).orElse(null).getRetrospectiveId().equals("retrospectiveId"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testPlanRepositoryDelete() {
        LOGGER.info("Plan Repository Delete Test");
        try {
            planRepository.delete(planRepository.findById(id).orElse(null));
            assert(planRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}