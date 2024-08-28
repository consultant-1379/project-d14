package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.RetrospectivePojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class Retrospective extends RetrospectivePojo {

    @DocumentReference(lookup="{'retrospectiveId':?#{#self._id} }")
    private List<Item> items;

    public Retrospective() {}

    public Retrospective(String teamId) {
        super(teamId);
    }

    public Retrospective(RetrospectivePojo retrospectivePojo) {
        this(retrospectivePojo.getTeamId());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getTeamId() {
        return super.getTeamId();
    }

    @Field
    public List<Item> getItems(){
        return items;
    }

    @Field
    @Override
    public boolean isComplete() {
        return super.isComplete();
    }

    @Override
    public String toString() {
        return "Retrospective{" +
                "id=" + super.getId() +
                ", teamId=" + super.getTeamId() +
                ", complete=" + super.isComplete() +
                ", items=" + items +
                '}';
    }
}
