package com.services;

import com.modals.OrderList;
import com.modals.OrderedItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderList")
public class OrderListServiceImpl implements OrderListService{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<OrderList> getOrderList() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderList> orderList=session.createNativeQuery("select * from OrderList order by id desc",OrderList.class).list();
        transaction.commit();
        session.close();
        return orderList;
    }

    @Override
    public OrderList addOrders(OrderList orderList) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(orderList);
        transaction.commit();
        session.close();
        return orderList;
    }

    @Override
    public OrderList updateOrderList(OrderList orderList) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.update(orderList);
        transaction.commit();
        session.close();
        return orderList;
    }

    @Override
    public OrderList deleteFromList(OrderList orderList) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(orderList);
        transaction.commit();
        session.close();
        return orderList;
    }
}
