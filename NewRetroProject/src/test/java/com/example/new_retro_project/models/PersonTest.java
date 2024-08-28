package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.PersonPojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonTest {

    private static final Logger LOGGER = Logger.getLogger(PersonTest.class.getName());
    private Person person = new Person("john.doe@ericsson.com", "John Doe");

    @BeforeAll
    void setUp(){ person = new Person(new PersonPojo("john.doe@ericsson.com", "John Doe")); }

    @Test
    void testGetEmail() {
        LOGGER.info("Person Get Email Test");
        assertThat(person.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testGetName() {
        LOGGER.info("Person Get Name Test");
        assertThat(person.getName().equals("John Doe"));
    }

    @Test
    void testGetTeamId() {
        LOGGER.info("Person Get Team ID Test");
        person.setTeamId("630f279157e11558b2332832");
        assertThat(person.getTeamId().equals("630f279157e11558b2332832"));
    }
}