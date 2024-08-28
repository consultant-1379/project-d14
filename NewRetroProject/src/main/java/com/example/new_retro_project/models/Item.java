package com.example.new_retro_project.models;

import com.example.new_retro_project.pojo.ItemPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Item extends ItemPojo {

    public Item(){}

    public Item(String retrospectiveId, String description, String category) {
        super(retrospectiveId, description, category);
    }

    public Item(ItemPojo itemPojo) {
        this(itemPojo.getRetrospectiveId(), itemPojo.getDescription(), itemPojo.getCategory());
    }

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    @Field
    @Override
    public String getRetrospectiveId() { return super.getRetrospectiveId(); }

    @Field
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Field
    @Override
    public String getCategory() {
        return super.getCategory();
    }
}
