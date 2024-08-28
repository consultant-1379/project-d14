package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Vote;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class VoteRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(VoteRepositoryTest.class.getName());

    private final VoteRepository voteRepository;
    private static String id;


    @Autowired
    VoteRepositoryTest(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Test
    void testVoteRepositoryAdd() {
        LOGGER.info("Vote Repository Add Test");
        try {
            id = voteRepository.save(new Vote("some.guy@ericsson.com", "itemId", true)).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(voteRepository.findById(id).orElse(null).getItemId().equals("itemId"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testVoteRepositoryDelete() {
        LOGGER.info("Vote Repository Delete Test");
        try {
            voteRepository.delete(voteRepository.findById(id).orElse(null));
            assert(voteRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}