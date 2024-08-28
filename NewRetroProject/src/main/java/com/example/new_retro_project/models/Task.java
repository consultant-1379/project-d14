package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.TaskPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Task extends TaskPojo {

    public Task() {}

    public Task(String planId, String description) { super(planId, description); }

    public Task(TaskPojo taskPojo) {
        this(taskPojo.getPlanId(), taskPojo.getDescription());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getPlanId() { return super.getPlanId(); }

    @Field
    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
