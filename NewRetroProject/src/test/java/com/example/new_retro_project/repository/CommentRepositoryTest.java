package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Comment;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;

import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CommentRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(CommentRepositoryTest.class.getName());

    private final CommentRepository commentRepository;
    private static String id;


    @Autowired
    CommentRepositoryTest(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    void testCommentRepositoryAdd() {
        LOGGER.info("Comment Repository Add Test");
        try {
            id = commentRepository.save(new Comment("someItemId", "person@ericsson.com", "this is cool")).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(commentRepository.findById(id).orElse(null).getComment().equals("this is cool"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testCommentRepositoryDelete() {
        LOGGER.info("Comment Repository Delete Test");
        try {
            commentRepository.delete(commentRepository.findById(id).orElse(null));
            assert(commentRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}