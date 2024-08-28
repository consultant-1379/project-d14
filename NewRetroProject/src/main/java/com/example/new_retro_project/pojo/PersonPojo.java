package com.example.new_retro_project.pojo;

import org.bson.types.ObjectId;

public class PersonPojo {
    private String email;
    private String name;

    private ObjectId teamId;

    public PersonPojo() {}

    public PersonPojo(String email, String name) {
        this.email = email;
        this.name = name;
        this.teamId = new ObjectId("6315cdf9d4020229220ec9b6");
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamId() {
        return teamId.toString();
    }

    public void setTeamId(String teamId) {
        this.teamId = new ObjectId(teamId);
    }

    @Override
    public String toString() {
        return "Person{" +
                "email=" + email +
                ", name='" + name + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
