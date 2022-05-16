package com.services;

import com.modals.OrderList;

import java.util.List;

public interface OrderListService {
    List<OrderList> getOrderList();
    OrderList addOrders(OrderList orderList);

    OrderList updateOrderList(OrderList orderList);

    OrderList deleteFromList(OrderList orderList);
}
