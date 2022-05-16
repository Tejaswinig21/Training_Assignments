package com.modals;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "categoryId")
    private int categoryId;
    @Column(name = "category",unique = true)
    private String category;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "categoryClass")

    private List<Item> items=new ArrayList<>();

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", items=" + items +
                '}';
    }
}
