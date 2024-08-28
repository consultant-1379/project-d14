package com.example.new_retro_project.pojo;

import com.example.new_retro_project.models.Category;
import org.bson.types.ObjectId;

public class ItemPojo {

    private String id;
    private ObjectId retrospectiveId;
    private String description;
    private Category category;

    public ItemPojo(){}

    public ItemPojo(String retrospectiveId, String description, String category) {
        this.retrospectiveId = new ObjectId(retrospectiveId);
        this.description = description;
        setCategory(category);
    }

    public String getId() {
        return id;
    }

    public String getRetrospectiveId() {
        return retrospectiveId.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(String category) {
        if (category != null) {
            switch (category){
                case "SAD":
                    this.category = Category.SAD;
                    break;
                case "MAD":
                    this.category = Category.MAD;
                    break;
                case "GLAD":
                    this.category = Category.GLAD;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", retrospectiveId=" + retrospectiveId +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
