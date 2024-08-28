package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Item;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ItemRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(ItemRepositoryTest.class.getName());

    private final ItemRepository itemRepository;
    private static String id;


    @Autowired
    ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    void testItemRepositoryAdd() {
        LOGGER.info("Item Repository Add Test");
        try {
            id = itemRepository.save(new Item("630f27478a45b8075df6fb7b", "Good progress", "GLAD")).getId();
            LOGGER.info(String.format("Auto Generated ID: %s", id));
            assert(itemRepository.findById(id).orElse(null).getDescription().equals("Good progress"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testItemRepositoryDelete() {
        LOGGER.info("Item Repository Delete Test");
        try {
            itemRepository.delete(itemRepository.findById(id).orElse(null));
            assert(itemRepository.findById(id).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}