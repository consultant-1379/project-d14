package com.example.new_retro_project.pojo;

public class VotePojo {

    private String id;
    private String email;
    private String itemId;
    private boolean vote;

    public VotePojo() {}

    public VotePojo(String email, String itemId, boolean vote) {
        this.email = email;
        this.itemId = itemId;
        this.vote = vote;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItemId() {
        return itemId;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", itemId='" + itemId + '\'' +
                ", vote=" + vote +
                '}';
    }
}
