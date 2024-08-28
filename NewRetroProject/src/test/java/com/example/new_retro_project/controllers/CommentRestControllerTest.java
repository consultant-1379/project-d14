package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Comment;
import com.example.new_retro_project.pojo.CommentPojo;
import com.example.new_retro_project.repository.CommentRepository;
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
class CommentRestControllerTest {

    @InjectMocks
    private CommentRestController controller;

    @Mock
    private CommentRepository itemRepository;

    private CommentPojo comment1 = new CommentPojo("630f33a957172c1700901455", "john.doe@ericsson.com", "this is good");
    private CommentPojo comment2 = new CommentPojo("630f33a957172c1700901455", "mary.doe@ericsson.com", "this is bad");
    private List<Comment> comments = new ArrayList<>(Arrays.asList(new Comment(comment1), new Comment(comment2)));

    @Test
    void testGetAllComments() {
        when(itemRepository.findAll()).thenReturn(comments);

        ResponseEntity<Collection<Comment>> result = controller.getAllComments();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertIterableEquals(comments, result.getBody());
    }

    @Test
    void testInsertComment() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(itemRepository.insert(any(Comment.class))).thenReturn(new Comment(comment1));

        ResponseEntity<Comment> result = controller.insertComment(comment1);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(comment1.getItemId().equals(result.getBody().getItemId()));
    }

    @Test
    void getCommentById() {
        Optional<Comment> opt = Optional.of(new Comment(comment1));
        when(itemRepository.existsById("id")).thenReturn(true);
        when(itemRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Comment> result = controller.getCommentById("id");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(comment1.getItemId().equals(result.getBody().getItemId()));
    }

    @Test
    void testGetItemByIdNotExists() {
        when(itemRepository.existsById("id")).thenReturn(false);

        ResponseEntity<Comment> result = controller.getCommentById("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void testGetItemByIdNotPresent() {
        Optional<Comment> opt = Optional.empty();
        when(itemRepository.existsById("id")).thenReturn(true);
        when(itemRepository.findById("id")).thenReturn(opt);

        ResponseEntity<Comment> result = controller.getCommentById("id");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}