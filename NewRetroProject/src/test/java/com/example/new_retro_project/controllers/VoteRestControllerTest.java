package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Item;
import com.example.new_retro_project.models.Vote;
import com.example.new_retro_project.pojo.ItemPojo;
import com.example.new_retro_project.pojo.VotePojo;
import com.example.new_retro_project.repository.ItemRepository;
import com.example.new_retro_project.repository.VoteRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
class VoteRestControllerTest {

    @InjectMocks
    private VoteRestController controller;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private ItemRepository itemRepository;

    private VotePojo vote1 = new VotePojo("john.doe@ericsson.com", "630f33a957172c1700901455", true);
    private VotePojo vote2 = new VotePojo("mary.doe@ericsson.come", "630f33a957172c1700901457", false);
    private List<Vote> votes = new ArrayList<>(Arrays.asList(new Vote(vote1), new Vote(vote2)));

    private ItemPojo item1 = new ItemPojo("630f33a957172c1700901450", "desc", "SAD");
    private ItemPojo item2 = new ItemPojo("630f33a957172c1700901450", "desc2", "MAD");
    private List<Item> items = new ArrayList<>(Arrays.asList(new Item(item1), new Item(item2)));

    @Test
    void getVotesForItem() {
        when(itemRepository.existsById("630f33a957172c1700901455")).thenReturn(true);
        when(voteRepository.findAll()).thenReturn(votes);

        ResponseEntity<Collection<Vote>> result = controller.getVotesForItem("630f33a957172c1700901455");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(vote1.getItemId().equals(result.getBody().stream().findFirst().orElse(null).getItemId()));
    }

    @Test
    void insertVote() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(itemRepository.existsById("630f33a957172c1700901455")).thenReturn(true);
        when(voteRepository.insert(any(Vote.class))).thenReturn(new Vote(vote1));

        ResponseEntity<Vote> result = controller.insertVote("630f33a957172c1700901455", vote1);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(vote1.getItemId().equals(result.getBody().getItemId()));
    }

    @Test
    void updateVote() {
        Optional<Vote> opt = Optional.of(new Vote(vote1));
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(voteRepository.existsById("630f33a957172c1700901455")).thenReturn(true);
        when(voteRepository.findById("630f33a957172c1700901455")).thenReturn(opt);
        when(voteRepository.save(any(Vote.class))).thenReturn(new Vote(vote1));

        ResponseEntity<Vote> result = controller.updateVote(vote1, "630f33a957172c1700901455");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(vote1.getItemId().equals(result.getBody().getItemId()));
    }
}