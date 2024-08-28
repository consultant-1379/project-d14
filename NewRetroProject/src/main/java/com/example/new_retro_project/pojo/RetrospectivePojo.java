package com.example.new_retro_project.pojo;

public class RetrospectivePojo {
    private String id;
    private String teamId;
    private boolean complete;

    public RetrospectivePojo() {}

    public RetrospectivePojo(String teamId) {
        this.teamId = teamId;
        this.complete = false;
    }

    public String getId() {
        return id;
    }

    public String getTeamId() {
        return teamId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Retrospective{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", complete=" + complete +
                '}';
    }
}
