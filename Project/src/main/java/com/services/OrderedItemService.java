package com.services;

import com.modals.Item;
import com.modals.OrderedItem;
import org.hibernate.criterion.Order;

import java.util.HashMap;
import java.util.List;

public interface OrderedItemService {
List<OrderedItem> getOrders();
    public List<OrderedItem> createOrders(List<OrderedItem> orderedItem);

OrderedItem updateOrders(OrderedItem orderedItem);
OrderedItem deleteOrders(OrderedItem orderedItem);

List<OrderedItem> getByDate();

List<OrderedItem> getHighestSales();

List<OrderedItem> getResults();
List<OrderedItem> getTodayRevenue();
List<OrderedItem> getTopItems();
List<OrderedItem> getInvoiceDetails(int x);
}
