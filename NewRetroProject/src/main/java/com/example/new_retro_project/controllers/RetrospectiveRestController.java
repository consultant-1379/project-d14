package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Retrospective;
import com.example.new_retro_project.pojo.RetrospectivePojo;
import com.example.new_retro_project.repository.RetrospectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/retrospective")
@CrossOrigin("http://localhost:8080")
public class RetrospectiveRestController {

    @Autowired
    private RetrospectiveRepository repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Retrospective>> getAllRetrospectives() {
        Collection<Retrospective> result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Retrospective> insertRetrospective(@RequestBody RetrospectivePojo retrospectivePojo) {
        Retrospective retrospective = new Retrospective(retrospectivePojo);
        repository.insert(retrospective);
        URI uri = URI.create("/retrospective/" + retrospective.getId());
        return ResponseEntity.created(uri).body(retrospective);
    }

    @PutMapping(value="/{id}", produces={"application/json","application/xml"})
    public ResponseEntity<Retrospective> setRetrospectiveComplete(@PathVariable("id") String id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        else {
            Optional<Retrospective> result = repository.findById(id);
            if (result.isPresent()) {
                Retrospective res1 = result.get();
                res1.setComplete(true);
                repository.save(res1);
                return ResponseEntity.ok().body(res1);
            }
            else { return ResponseEntity.notFound().build(); }
        }
    }


    @GetMapping(value="/{id}", produces={"application/json","application/xml"})
    public ResponseEntity<Retrospective> getRetrospectiveById(@PathVariable("id") String id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        else {
            Optional<Retrospective> result = repository.findById(id);
            if (result.isPresent()) {
                Retrospective res1 = result.get();
                return ResponseEntity.ok().body(res1);
            }
            else { return ResponseEntity.notFound().build(); }
        }
    }

    @GetMapping(value="/team/{teamId}", produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Retrospective>> getRetrospectivesByTeamId(@PathVariable("teamId") String teamId) {
        Collection<Retrospective> result = repository.findAll();
        List<Retrospective> retrospectivesForTeam = new ArrayList<>();
        for (Retrospective r : result) {
            if (r.getTeamId() != null) {
                if (r.getTeamId().equals(teamId)) {
                    System.out.println(r.getTeamId());
                    retrospectivesForTeam.add(r);
                }
            }

        }
        return ResponseEntity.ok().body(retrospectivesForTeam);
    }
}
