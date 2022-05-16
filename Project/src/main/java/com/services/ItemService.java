package com.services;

import com.modals.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
    Item createItems(Item item);
    Item updateItems(Item item);
    Item deleteItems(Item item);

    List<Item> getStarters();

    List<Item> getBiryanis();

    List<Item> getDesserts();

    List<Item> getBeverages();

    List<Item> deleteById(int id);
    List<Item> getCategoryItems(int categoryId);
}
