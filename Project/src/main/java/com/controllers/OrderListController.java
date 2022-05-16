package com.controllers;

import com.modals.OrderList;
import com.services.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderListController {

    @Autowired
    @Qualifier("orderList")
    private OrderListService orderListService;

    @GetMapping("/getOrderList")
    public List<OrderList> getAll(){
        return orderListService.getOrderList();
    }
    @PostMapping("/addOrderList")
    public OrderList addOrder(@RequestBody OrderList orderList){
        return orderListService.addOrders(orderList);
    }

    @PutMapping("/updateOrderList")
    public OrderList updateOrder(@RequestBody OrderList orderList){
        return orderListService.updateOrderList(orderList);
    }

    @DeleteMapping("/deleteOrder")
    public OrderList deleteFromList(@RequestBody OrderList orderList){
        return orderListService.deleteFromList(orderList);
    }
}


