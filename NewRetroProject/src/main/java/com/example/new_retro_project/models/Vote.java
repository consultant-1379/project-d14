package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.VotePojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Vote extends VotePojo {

    public Vote() {}

    public Vote(String email, String itemId, boolean vote) { super(email, itemId, vote); }

    public Vote(VotePojo votePojo) {
        this(votePojo.getEmail(), votePojo.getItemId(), votePojo.isVote());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getEmail() { return super.getEmail(); }

    @Field
    @Override
    public String getItemId() {
        return super.getItemId();
    }

    @Field
    @Override
    public boolean isVote() {
        return super.isVote();
    }
}
