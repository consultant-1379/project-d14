package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Person;
import com.example.new_retro_project.pojo.PersonPojo;
import com.example.new_retro_project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/person")
@CrossOrigin("http://localhost:8080")
public class PersonRestController {

    @Autowired
    private PersonRepository repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Person>> getAllPersons() {
        Collection<Person> result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Person> insertPerson(@RequestBody PersonPojo personPojo) {
        Person person = new Person(personPojo);
        repository.insert(person);
        URI uri = URI.create("/person/" + personPojo.getEmail());
        return ResponseEntity.created(uri).body(person);
    }

    @GetMapping(value="/{email}", produces={"application/json","application/xml"})
    public ResponseEntity<Person> getPersonByEmail(@PathVariable("email") String email) {
        if (!repository.existsById(email))
            return ResponseEntity.notFound().build();
        else {
            Optional<Person> result = repository.findById(email);
            if (result.isPresent()) {
                Person res1 = result.get();
                return ResponseEntity.ok().body(res1);
            }
            else { return ResponseEntity.notFound().build(); }
        }
    }
}
