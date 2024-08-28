package com.example.new_retro_project.pojo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonPojoTest {

    private static final Logger LOGGER = Logger.getLogger(PersonPojoTest.class.getName());
    private PersonPojo personPojo = new PersonPojo("john.doe@ericsson.com", "John Doe");

    @BeforeAll
    void setUp(){
        personPojo.setTeamId("630f279157e11558b2332832");
    }

    @Test
    void testGetEmail() {
        LOGGER.info("Person POJO Get Email Test");
        assertThat(personPojo.getEmail().equals("john.doe@ericsson.com"));
    }

    @Test
    void testGetName() {
        LOGGER.info("Person POJO Get Name Test");
        assertThat(personPojo.getName().equals("John Doe"));
    }

    @Test
    void testSetName() {
        LOGGER.info("Person POJO Set Name Test");
        personPojo.setName("Mary Doe");
        assertThat(personPojo.getName().equals("Mary Doe"));
        personPojo.setName("John Doe");
    }

    @Test
    void testGetTeamId() {
        LOGGER.info("Person POJO Get Team ID Test");
        assertThat(personPojo.getTeamId().equals("630f279157e11558b2332832"));
    }

    @Test
    void testSetTeamId() {
        LOGGER.info("Person POJO Set Team ID Test");
        personPojo.setTeamId("630f27478a45b8075df6fb7b");
        assertThat(personPojo.getTeamId().equals("630f27478a45b8075df6fb7b"));
        personPojo.setTeamId("630f279157e11558b2332832");
    }

    @Test
    void testToString() {
        LOGGER.info("Person POJO toString Test");
        assertThat(personPojo.toString().equals("Person{email=john.doe@ericsson.com, name='John Doe', teamId=630f279157e11558b2332832}"));
    }
}