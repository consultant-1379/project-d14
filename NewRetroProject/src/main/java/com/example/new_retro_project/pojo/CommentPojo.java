package com.example.new_retro_project.pojo;

public class CommentPojo {

    private String id;
    private String itemId;
    private String email;
    private String comment;

    public CommentPojo(){}

    public CommentPojo(String itemId, String email, String comment) {
        this.itemId = itemId;
        this.email = email;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
