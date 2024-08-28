package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.CommentPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Comment extends CommentPojo {

    public Comment(){}

    public Comment(String itemId, String email, String comment) {
        super(itemId, email, comment);
    }

    public Comment(CommentPojo commentPojo) {
        this(commentPojo.getItemId(), commentPojo.getEmail(), commentPojo.getComment());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getItemId() {
        return super.getItemId();
    }

    @Field
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Field
    @Override
    public String getComment() {
        return super.getComment();
    }

}
