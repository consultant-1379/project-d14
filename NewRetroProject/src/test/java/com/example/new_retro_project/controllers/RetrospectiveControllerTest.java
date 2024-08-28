package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Retrospective;
import com.example.new_retro_project.repository.RetrospectiveRepository;
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
public class RetrospectiveControllerTest {

    @InjectMocks
    private RetrospectiveRestController controller;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    private Retrospective retrospective1 = new Retrospective("630e4e63f92f977d8148e478");
    private Retrospective retrospective2 = new Retrospective("630e4e63f92f977d8148e478");
    List<Retrospective> retrospectives = new ArrayList<>(Arrays.asList(new Retrospective(retrospective1), new Retrospective(retrospective2)));

    @Test
    public void testGetAllRetrospectives() {
        when(retrospectiveRepository.findAll()).thenReturn(retrospectives);

        ResponseEntity<Collection<Retrospective>> result = controller.getAllRetrospectives();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertIterableEquals(retrospectives, result.getBody());

    }

    @Test
    void testInsertRetrospective() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(retrospectiveRepository.insert(any(Retrospective.class))).thenReturn(new Retrospective(retrospective1));

        ResponseEntity<Retrospective> result = controller.insertRetrospective(retrospective1);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(retrospective1.getTeamId().equals(result.getBody().getTeamId()));
    }

    @Test
    void testGetRetrospectiveById() {
        Optional<Retrospective> opt = Optional.of(new Retrospective(retrospective1));
        when(retrospectiveRepository.existsById("id")).thenReturn(true);
        when(retrospectiveRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Retrospective> result = controller.getRetrospectiveById("id");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(retrospective1.getTeamId().equals(result.getBody().getTeamId()));
    }

    @Test
    void testGetItemByIdNotExists() {
        when(retrospectiveRepository.existsById("id")).thenReturn(false);

        ResponseEntity<Retrospective> result = controller.getRetrospectiveById("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testGetItemByIdNotPresent() {
        Optional<Retrospective> opt = Optional.empty();
        when(retrospectiveRepository.existsById("id")).thenReturn(true);
        when(retrospectiveRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Retrospective> result = controller.getRetrospectiveById("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
