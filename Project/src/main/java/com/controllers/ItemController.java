package com.controllers;

import com.modals.Item;
import com.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    @Qualifier("items")
    private ItemService itemService;

    @GetMapping("/getItems")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/getStarters")
    public List<Item> getStarters(){
        return itemService.getStarters();
    }
    @GetMapping("/getBiryanis")
    public List<Item> getBiryanis(){
        return itemService.getBiryanis();
    }
    @GetMapping("/getDesserts")
    public List<Item> getDesserts(){
        return itemService.getDesserts();
    }
    @GetMapping("/getBeverages")
    public List<Item> getBeverages(){
        return itemService.getBeverages();
    }
    @PostMapping("/addItems")
    public Item createItems(@RequestBody Item item){
        System.out.println(item);
        return itemService.createItems(item);
    }

    @GetMapping("/getByCategoryId")
    public List<Item> getByCategoryId(@RequestParam(name = "categoryId") int categoryId){
        return itemService.getCategoryItems(categoryId);
    }
    @DeleteMapping("/deleteById")
    public List<Item> deleteById(@RequestParam(name = "itemId") int id){
        return itemService.deleteById(id);
    }

    @RequestMapping(value = "/updateItems",method = RequestMethod.PUT)
    public Item updateReminder(@RequestBody Item item){
        return itemService.updateItems(item);
    }

    @RequestMapping(value = "deleteItems",method = RequestMethod.DELETE)
    public Item deleteReminder(@RequestBody Item item){
        return itemService.deleteItems(item);
    }
}
