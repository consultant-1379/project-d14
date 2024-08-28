package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Person;
import com.example.new_retro_project.pojo.PersonPojo;
import com.example.new_retro_project.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
public class PersonControllerTest {

    @InjectMocks
    private PersonRestController controller;

    @Mock
    private PersonRepository personRepository;

    private PersonPojo person1 = new PersonPojo("sam@hotmail.com", "sam");
    private PersonPojo person2 = new PersonPojo("whatsup@hotmail.ie", "Hi there");
    List<Person> persons = new ArrayList<>(Arrays.asList(new Person(person1), new Person(person2)));

    @Test
    public void testGetAllPeople() {
        when(personRepository.findAll()).thenReturn(persons);

        ResponseEntity<Collection<Person>> result = controller.getAllPersons();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertIterableEquals(persons, result.getBody());
    }

    @Test
    void testInsertPerson() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(personRepository.insert(any(Person.class))).thenReturn(new Person(person1));

        ResponseEntity<Person> result = controller.insertPerson(person1);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(person1.getEmail().equals(result.getBody().getEmail()));
    }

    @Test
    void testGetPersonByEmail() {
        Optional<Person> opt = Optional.of(new Person(person1));
        when(personRepository.existsById("sam@hotmail.com")).thenReturn(true);
        when(personRepository.findById("sam@hotmail.com")).thenReturn(opt);

        ResponseEntity<Person> result = controller.getPersonByEmail("sam@hotmail.com");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(person1.getEmail().equals(result.getBody().getEmail()));
    }

    @Test
    void testGetPersonByEmailNotExists() {
        when(personRepository.existsById("sam@hotmail.com")).thenReturn(false);

        ResponseEntity<Person> result = controller.getPersonByEmail("sam@hotmail.com");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testGetPersonByEmailNotPresent() {
        Optional<Person> opt = Optional.empty();
        when(personRepository.existsById("sam@hotmail.com")).thenReturn(true);
        when(personRepository.findById("sam@hotmail.com")).thenReturn(opt);

        ResponseEntity<Person> result = controller.getPersonByEmail("sam@hotmail.com");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
