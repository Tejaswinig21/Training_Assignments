package com.modals;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Item`")
public class Item {

    @Id
    @Column(name = "itemId")
    private int itemId;
    @Column(name = "item",unique = true)
    @NotNull
    private String item;
    @Column(name = "price")
    @NotNull
    private int price;

    @Column(name = "category")
    @NotNull
    private String category;


    public Category getCategoryClass() {
        return categoryClass;
    }

    public void setCategoryClass(Category categoryClass) {
        this.categoryClass = categoryClass;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryId",referencedColumnName = "categoryId")
    private Category categoryClass;

    @OneToMany(mappedBy = "item")
    private List<OrderedItem> orderedItems=new ArrayList<>();


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", categoryClass=" + categoryClass +
                '}';
    }
}
