package com.services;

import com.modals.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("items")
public class DatabaseItemServiceImpl implements ItemService {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Item> getItems() {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        List<Item> items=session.createQuery("from Item order by itemId desc", Item.class).list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public Item createItems(Item item) {
        System.out.println("Hiii hello byeee byeee"+item);
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public Item updateItems(Item item) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.update(item);
        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public Item deleteItems(Item item) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(item);
        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public List<Item> getStarters() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Item> items=session.createNativeQuery("select * from Item where category=\"Starters\"",Item.class).list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public List<Item> getBiryanis() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Item> items=session.createNativeQuery("select * from Item where category=\"Biryanis\"",Item.class).list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public List<Item> getDesserts() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Item> items=session.createNativeQuery("select * from Item where category=\"Desserts\"",Item.class).list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public List<Item> getBeverages() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Item> items=session.createNativeQuery("select * from Item where category=\"Beverages\"",Item.class).list();
        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public List<Item> deleteById(int id) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        //Query q= session.createNativeQuery("delete from Item where itemId=?");
        Item item=new Item();
        item.setItemId(id);
        session.delete(item);
        List<Item> items=session.createNativeQuery("select itemId from Item").list();
        transaction.commit();
        session.close();
        return items;
    }

    public List<Item> getCategoryItems(int categoryId){
        Session session= sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query =session.createNativeQuery("select * from item where categoryId=:id");
        query.setParameter("id",categoryId);
        List<Item> items=query.list();
        return items;

    }
}
