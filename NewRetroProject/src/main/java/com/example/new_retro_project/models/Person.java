package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.PersonPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Person extends PersonPojo {

    public Person() {}

    public Person(String email, String name) {
        super(email, name);
    }

    public Person(PersonPojo personPojo) {
        this(personPojo.getEmail(), personPojo.getName());
    }

    @Id
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Field
    @Override
    public String getName() {
        return super.getName();
    }

    @Field
    @Override
    public String getTeamId() {
        return super.getTeamId();
    }
}
