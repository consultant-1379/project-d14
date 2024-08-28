package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.PlanPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Plan extends PlanPojo {

    public Plan() {}

    public Plan(String retrospectiveId) {
        super(retrospectiveId);
    }

    public Plan(PlanPojo planPojo) {this(planPojo.getRetrospectiveId());}

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getRetrospectiveId() {
        return super.getRetrospectiveId();
    }

}
