package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.TeamPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class Team extends TeamPojo {

    @DocumentReference(lookup="{'teamId':?#{#self._id} }")
    private List<Person> persons;
    @DocumentReference(lookup="{'teamId':?#{#self._id} }")
    private List<Retrospective> retrospectives;
    public Team() {}

    public Team(String name, List<String> teamMembers) {
        super(name);
        String[] teamMembersArray = teamMembers.toArray(new String[teamMembers.size()]);
        this.addTeamMembers(teamMembersArray);
    }

    public Team(String name, String[] teamMembers) {
        super(name, teamMembers);
    }

    public Team(TeamPojo teamPojo) {
        this(teamPojo.getName(), teamPojo.getTeamMembers());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getName() {
        return super.getName();
    }

    @Field
    @Override
    public List<String> getTeamMembers() {
        return super.getTeamMembers();
    }

    public List<Retrospective> getRetrospectives(){
        return retrospectives;
    }

    public List<Person> getPersons() {
        return persons;
    }

}
