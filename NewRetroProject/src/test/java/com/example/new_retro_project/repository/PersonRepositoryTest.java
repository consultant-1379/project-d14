package com.example.new_retro_project.repository;

import com.example.new_retro_project.models.Person;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.logging.Logger;
import static com.mongodb.assertions.Assertions.fail;

@DataMongoTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PersonRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(PersonRepositoryTest.class.getName());

    private final PersonRepository personRepository;
    private static String email;


    @Autowired
    PersonRepositoryTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    void testPersonRepositoryAdd() {
        LOGGER.info("Person Repository Add Test");
        try {
            email = personRepository.save(new Person("john.doe@ericsson.com", "John Doe")).getEmail();
            LOGGER.info(String.format("Auto Generated ID: %s", email));
            assert(personRepository.findById(email).orElse(null).getName().equals("John Doe"));
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }

    @Test
    void testPersonRepositoryDelete() {
        LOGGER.info("Person Repository Delete Test");
        try {
            personRepository.delete(personRepository.findById(email).orElse(null));
            assert(personRepository.findById(email).orElse(null) == null);
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
            fail();
        }
    }
}