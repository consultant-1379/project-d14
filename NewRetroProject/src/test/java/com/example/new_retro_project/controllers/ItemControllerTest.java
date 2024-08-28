package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Item;
import com.example.new_retro_project.pojo.ItemPojo;
import com.example.new_retro_project.repository.ItemRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
public class ItemControllerTest {

    @InjectMocks
    private ItemRestController controller;

    @Mock
    private  ItemRepository itemRepository;

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    private ItemPojo item1 = new ItemPojo("630f33a957172c1700901455", "Bad times", "SAD");
    private ItemPojo item2 = new ItemPojo("630f33a957172c1700901455", "Good times", "GLAD");
    private List<Item> items = new ArrayList<>(Arrays.asList(new Item(item1), new Item(item2)));

    @Test
    void testGetAllItems() {
        when(retrospectiveRepository.existsById("630f33a957172c1700901455")).thenReturn(true);
        when(itemRepository.findAll()).thenReturn(items);

        ResponseEntity<Collection<Item>> result = controller.getItemsForRetrospective("630f33a957172c1700901455");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertIterableEquals(items, result.getBody());
    }

    @Test
    void testInsertItem() {
        when(retrospectiveRepository.existsById("630f33a957172c1700901455")).thenReturn(true);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(itemRepository.insert(any(Item.class))).thenReturn(new Item(item1));

        ResponseEntity<Item> result = controller.insertItem(item1, "630f33a957172c1700901455");
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(item1.getRetrospectiveId().equals(result.getBody().getRetrospectiveId()));
    }
}
