package com.controllers;

import com.modals.Item;
import com.modals.OrderedItem;
import com.services.ItemService;
import com.services.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderedController {
    @Autowired
    @Qualifier("orders")
    private OrderedItemService orderedItemService;

    @GetMapping("/getOrders")
    public List<OrderedItem> getOrders(){
        return orderedItemService.getOrders();
    }
    @PostMapping("/addOrders")
    public List<OrderedItem> addOrders(@RequestBody List<OrderedItem> orderedItem){

        return orderedItemService.createOrders(orderedItem);
    }

    @RequestMapping(value = "/updateOrders",method = RequestMethod.PUT)
    public OrderedItem updateReminder(@RequestBody OrderedItem orderedItem){
        return orderedItemService.updateOrders(orderedItem);
    }

    @RequestMapping(value = "deleteOrders",method = RequestMethod.DELETE)
    public OrderedItem deleteReminder(@RequestBody OrderedItem orderedItem){
        return orderedItemService.deleteOrders(orderedItem);
    }
    @GetMapping("/getRecent")
    public List<OrderedItem> getByDate(){
        return orderedItemService.getByDate();
    }

    @GetMapping("/getSales")
    public List<OrderedItem> getHighestSales(){
        return orderedItemService.getHighestSales();
    }

    @GetMapping("/getRes")
    public List<OrderedItem> getresult(){
        return orderedItemService.getResults();
    }
    @GetMapping("/getTodaysRevenue")
    public List<OrderedItem> getTodayRevenue(){
        return  orderedItemService.getTodayRevenue();
    }

    @GetMapping("/getTopItems")
    public List<OrderedItem> getTopItems(){
        return orderedItemService.getTopItems();
    }
    @GetMapping("/getByInvoiceDetails")
    public List<OrderedItem> getByCategoryId(@RequestParam(name = "invoiceId") int invoiceId){
        return orderedItemService.getInvoiceDetails(invoiceId);
    }
}
