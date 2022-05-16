package com.services;

import com.modals.Item;
import com.modals.OrderedItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;

import javax.persistence.OrderColumn;
import java.util.HashMap;
import java.util.List;

@Service("orders")
public class OrderedItemsServiceImpl implements OrderedItemService{

    @Autowired
    private SessionFactory sessionFactory;

    private HashMap<String,Integer> hm=new HashMap<>();
    @Override
    public List<OrderedItem> getOrders() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> orderedItems=session.createQuery("from OrderedItem order by orderId desc",OrderedItem.class).list();
        transaction.commit();
        session.close();
        return orderedItems;
    }

    @Override
    public List<OrderedItem> createOrders(List<OrderedItem> orderedItem) {
        System.out.println(orderedItem);
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        for(OrderedItem item:orderedItem){
            saveOrder(item);
        }

        transaction.commit();
        session.close();
        return orderedItem;
    }




    public void saveOrder (OrderedItem orderedItem) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(orderedItem);
        transaction.commit();
        session.close();
    }
    @Override
    public OrderedItem updateOrders(OrderedItem orderedItem) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.update(orderedItem);
        transaction.commit();
        session.close();
        return orderedItem;
    }

    @Override
    public OrderedItem deleteOrders(OrderedItem orderedItem) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(orderedItem);
        transaction.commit();
        session.close();
        return orderedItem;
    }

    public List<OrderedItem> getByDate(){
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> orderedItems=session.createNativeQuery("select * from OrderedItem where dateOfOrder=curdate() order by id desc",OrderedItem.class).list();
        transaction.commit();
        session.close();
        return orderedItems;
    }

    public List<OrderedItem> getHighestSales(){

        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> items=session.createNativeQuery("select sum(totalAmount) from invoice group by dateOfOrder order by sum(totalAmount) desc limit 1").list();
        transaction.commit();
        session.close();
        System.out.println(hm);
        return items;
    }

    @Override
    public List<OrderedItem> getResults() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> items=session.createNativeQuery("select itemName,sum(quantity) as numberOfItems from OrderedItem group by itemName order by quantity desc limit 1").list();
        transaction.commit();
        session.close();
        System.out.println(hm);
        return items;
    }

    @Override
    public List<OrderedItem> getTodayRevenue() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> orderedItems=session.createNativeQuery("select sum(totalAmount) from invoice where dateOfOrder=curdate() group by dateOfOrder").list();
        transaction.commit();
        session.close();
        return orderedItems;
    }

    @Override
    public List<OrderedItem> getTopItems() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<OrderedItem> orderedItems=session.createNativeQuery("select item,price,sum(total) from ordereditem group by item order by count(item) desc limit 5").list();
        transaction.commit();
        session.close();
        return orderedItems;
    }

    @Override
    public List<OrderedItem> getInvoiceDetails(int x) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        Query query =session.createNativeQuery("select o.id,o.item,o.price,o.quantity,o.total from ordereditem o join invoice i on o.invoiceId=i.id where o.invoiceId=:id1");
        query.setParameter("id1",x);
        List<OrderedItem> orderedItems=query.list();
        return orderedItems;
    }
}
