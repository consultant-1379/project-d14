package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Person;
import com.example.new_retro_project.models.Team;
import com.example.new_retro_project.pojo.TeamPojo;
import com.example.new_retro_project.repository.PersonRepository;
import com.example.new_retro_project.repository.TeamRepository;

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
class TeamControllerTest {

    @InjectMocks
    private TeamRestController controller;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PersonRepository personRepository;

    private String[] members1 = {"Sammy", "Hello", "Yop"};
    private String[] members2 = {"Under", "The", "Computer"};

    private TeamPojo team1 = new TeamPojo("Rats Central", members1);
    private TeamPojo team2 = new TeamPojo("Rats Central number 2", members2);
    List<Team> teams = new ArrayList<>(Arrays.asList(new Team(team1), new Team(team2)));

    @Test
    public void testGetAllTeams() {
        when(teamRepository.findAll()).thenReturn(teams);

        ResponseEntity<Collection<Team>> result = controller.getAllTeams();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertIterableEquals(teams, result.getBody());
    }

    @Test
    void testGetItemById() {
        Optional<Team> opt = Optional.of(new Team(team1));
        when(teamRepository.existsById("id")).thenReturn(true);
        when(teamRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Team> result = controller.getTeam("id");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(team1.getName().equals(result.getBody().getName()));
    }

    @Test
    void testGetItemByIdNotExists() {
        when(teamRepository.existsById("id")).thenReturn(false);

        ResponseEntity<Team> result = controller.getTeam("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testGetItemByIdNotPresent() {
        Optional<Team> opt = Optional.empty();
        when(teamRepository.existsById("id")).thenReturn(true);
        when(teamRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Team> result = controller.getTeam("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
