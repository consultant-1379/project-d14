package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Item;
import com.example.new_retro_project.models.Vote;
import com.example.new_retro_project.pojo.ItemPojo;
import com.example.new_retro_project.pojo.VotePojo;
import com.example.new_retro_project.repository.ItemRepository;
import com.example.new_retro_project.repository.RetrospectiveRepository;
import com.example.new_retro_project.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/retrospective/item")
@CrossOrigin("http://localhost:8080")
public class ItemRestController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @GetMapping(value="/{retrospectiveId}", produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Item>> getItemsForRetrospective(@PathVariable("retrospectiveId") String retrospectiveId) {
        if (!retrospectiveRepository.existsById(retrospectiveId)) {
            return ResponseEntity.notFound().build();
        }
        else {
            Collection<Item> items = itemRepository.findAll();
            List<Item> itemsForRepository = new ArrayList<>();
            for (Item i: items) {
                if (i.getRetrospectiveId().equals(retrospectiveId)) {
                    itemsForRepository.add(i);
                }
            }
            return ResponseEntity.ok().body(itemsForRepository);
        }
    }

    @PostMapping(value="/{retrospectiveId}", consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Item> insertItem(@RequestBody ItemPojo itemPojo, @PathVariable("retrospectiveId") String retrospectiveId) {
        if (!retrospectiveRepository.existsById(retrospectiveId)) {
            return ResponseEntity.notFound().build();
        } else {
            Item item = new Item(retrospectiveId, itemPojo.getDescription(), itemPojo.getCategory());
            itemRepository.insert(item);
            URI uri = URI.create("/retrospective/item/" + item.getId());
            return ResponseEntity.created(uri).body(item);
        }
    }

    @PutMapping(value="/{id}", consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Item> updateItem(@RequestBody ItemPojo itemPojo, @PathVariable("id") String id) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        else {
            Optional<Item> result = itemRepository.findById(id);
            if (result.isPresent()) {
                Item item = result.get();
                item.setCategory(itemPojo.getCategory());
                item.setDescription(itemPojo.getDescription());
                itemRepository.save(item);
                return ResponseEntity.ok().body(item);
            }
            else { return ResponseEntity.notFound().build(); }
        }
    }
}
