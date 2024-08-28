package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Team;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TeamRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(TeamRepositoryTest.class.getName());

    private final TeamRepository teamRepository;
    private static String id;


    @Autowired
    TeamRepositoryTest(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Test
    void testTeamRepositoryAdd() {
        LOGGER.info("Team Repository Add Test");
        try {
            id = teamRepository.save(new Team("Anonymous", new String[0])).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(teamRepository.findById(id).orElse(null).getName().equals("Anonymous"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testTeamRepositoryDelete() {
        LOGGER.info("Team Repository Delete Test");
        try {
            teamRepository.delete(teamRepository.findById(id).orElse(null));
            assert(teamRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}