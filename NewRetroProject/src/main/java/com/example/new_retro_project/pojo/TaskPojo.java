package com.example.new_retro_project.pojo;

public class TaskPojo {
    private String id;
    private String planId;
    private String description;

    public TaskPojo() {}

    public TaskPojo(String planId, String description) {
        this.planId = planId;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getPlanId() {
        return planId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", planId=" + planId +
                ", description='" + description + '\'' +
                '}';
    }
}
