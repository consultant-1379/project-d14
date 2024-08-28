package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Person;
import com.example.new_retro_project.models.Team;
import com.example.new_retro_project.pojo.TeamPojo;
import com.example.new_retro_project.repository.PersonRepository;
import com.example.new_retro_project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@Controller
@RequestMapping("/team")
@CrossOrigin("http://localhost:8080")
public class TeamRestController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Team>> getAllTeams() {
        Collection<Team> result = teamRepository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Team> insertTeam(@RequestBody TeamPojo teamPojo) {
        if (checkTeamIdIsNull(teamPojo.getTeamMembers())) {
            Team team = new Team(teamPojo);
            teamRepository.insert(team);
            URI uri = URI.create("/team/" + team.getId());
            for (String email : team.getTeamMembers()) {
                Optional<Person> person = personRepository.findById(email);
                if (person.isPresent()) {
                    Person person1 = person.get();
                    person1.setTeamId(team.getId());
                    personRepository.save(person1);
                }
            }
            return ResponseEntity.created(uri).body(team);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping(value="/{id}", produces={"application/json","application/xml"})
    public ResponseEntity<Team> getTeam(@PathVariable("id") String id) {
        if (!teamRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else {
            Optional<Team> result = teamRepository.findById(id);
            if (result.isPresent()) {
                Team res1 = result.get();
                return ResponseEntity.ok().body(res1);
            }
            else { return ResponseEntity.notFound().build(); }

        }
    }

    public boolean checkTeamIdIsNull(List<String> teamMembers) {
        for (String email : teamMembers) {
            Optional<Person> person = personRepository.findById(email);
            if (person.isPresent()) {
                Person person1 = person.get();
                if (!person1.getTeamId().equals("6315cdf9d4020229220ec9b6")) {
                    return false;
                }
            }
        }
        return true;

    }


}
