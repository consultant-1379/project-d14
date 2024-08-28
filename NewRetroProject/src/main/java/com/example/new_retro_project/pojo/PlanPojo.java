package com.example.new_retro_project.pojo;

public class PlanPojo {

    private String id;
    private String retrospectiveId;

    public PlanPojo() {}

    public PlanPojo(String retrospectiveId) {
        this.retrospectiveId = retrospectiveId;
    }

    public String getId() {
        return id;
    }

    public String getRetrospectiveId() {
        return retrospectiveId;
    }


    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", retrospectiveId='" + retrospectiveId + '\'' +
                '}';
    }
}
