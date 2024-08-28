package com.example.new_retro_project.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamPojo {

    private String id;
    private String name;
    private List<String> teamMembers = new ArrayList<>();

    public TeamPojo() {}

    public TeamPojo(String name) {
        this.name = name;
    }

    public TeamPojo(String name, String[] teamMembers) {
        this.name = name;
        addTeamMembers(teamMembers);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMembers(String[] members) {
        Collections.addAll(teamMembers, members);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamMembers=" + teamMembers +
                '}';
    }
}

