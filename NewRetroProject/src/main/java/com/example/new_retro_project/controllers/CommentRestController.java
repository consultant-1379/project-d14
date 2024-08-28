package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Comment;
import com.example.new_retro_project.models.Item;
import com.example.new_retro_project.pojo.CommentPojo;
import com.example.new_retro_project.pojo.ItemPojo;
import com.example.new_retro_project.repository.CommentRepository;
import com.example.new_retro_project.repository.ItemRepository;
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
@RequestMapping("/comment")
@CrossOrigin("http://localhost:8080")
public class CommentRestController {

    @Autowired
    private CommentRepository repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Comment>> getAllComments() {
        Collection<Comment> result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Comment> insertComment(@RequestBody CommentPojo commentPojo) {
        Comment comment = new Comment(commentPojo);
        repository.insert(comment);
        URI uri = URI.create("/comment/" + comment.getId());
        return ResponseEntity.created(uri).body(comment);
    }

    @GetMapping(value="/{id}", produces={"application/json","application/xml"})
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") String id) {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        else {
            Optional<Comment> result = repository.findById(id);
            if (result.isPresent()) {
                Comment comment = result.get();
                return ResponseEntity.ok().body(comment);
            }
            else { return ResponseEntity.notFound().build(); }
        }
    }

    @GetMapping(value="/item/{itemId}", produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Comment>> getCommentByItemId(@PathVariable("itemId") String itemId) {
        Collection<Comment> result = repository.findAll();
        List<Comment> commentsForItem = new ArrayList<>();
        for (Comment c: result) {
            if (c.getItemId().equals(itemId)) {
                commentsForItem.add(c);
            }
        }
        return ResponseEntity.ok().body(commentsForItem);
    }
}
