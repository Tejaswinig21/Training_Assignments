package com.services;

import com.modals.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.TableRowSorter;
import java.util.List;

@Service("categories")
public class DatabaseCategoryServiceImpl implements CategoryService{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> getCategories() {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        List<Category> categories=session.createQuery("from Category order by categoryId desc",Category.class).list();
        transaction.commit();
        session.close();
        return categories;
    }

    @Override
    public Category addCategories(Category category) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
        return category;
    }

    @Override
    public Category deleteCategory(Category category) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(category);
        transaction.commit();
        session.close();
        return category;
    }
}
