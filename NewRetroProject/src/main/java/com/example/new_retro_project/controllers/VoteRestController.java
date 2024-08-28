package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Vote;
import com.example.new_retro_project.pojo.VotePojo;
import com.example.new_retro_project.repository.ItemRepository;
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
@RequestMapping("/retrospective/item/vote")
@CrossOrigin("http://localhost:8080")
public class VoteRestController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value="/{itemId}", produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Vote>> getVotesForItem(@PathVariable("itemId") String itemId) {
        if (!itemRepository.existsById(itemId))
            return ResponseEntity.notFound().build();
        else {
            Collection<Vote> votes = voteRepository.findAll();
            List<Vote> votesForItem = new ArrayList<>();
            for (Vote v: votes) {
                if (v.getItemId().equals(itemId)) {
                    votesForItem.add(v);
                }
            }
            return ResponseEntity.ok().body(votesForItem);
        }
    }

    @PostMapping(value="/{itemId}", consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Vote> insertVote(@PathVariable("itemId") String itemId, @RequestBody VotePojo votePojo) {
        if (!itemRepository.existsById(itemId)) {
            return ResponseEntity.notFound().build();
        } else {
            Vote vote = new Vote(votePojo.getEmail(), itemId, votePojo.isVote());
            voteRepository.insert(vote);
            URI uri = URI.create("/retrospective/item/" + vote.getItemId());
            return ResponseEntity.created(uri).body(vote);
        }
    }

    @PutMapping(value="/{id}", consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Vote> updateVote(@RequestBody VotePojo votePojo, @PathVariable("id") String id) {
        if (!voteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            Optional<Vote> result = voteRepository.findById(id);
            if (result.isPresent()) {
                Vote vote = result.get();
                vote.setVote(votePojo.isVote());
                voteRepository.save(vote);
                return ResponseEntity.ok().body(vote);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
