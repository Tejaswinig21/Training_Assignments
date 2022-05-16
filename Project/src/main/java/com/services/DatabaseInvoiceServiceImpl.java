package com.services;

import com.modals.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("invoice")
public class DatabaseInvoiceServiceImpl implements InvoiceService{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Invoice> getInvoices() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Invoice> invoices=session.createQuery("from Invoice order by id desc",Invoice.class).list();
        transaction.commit();
        session.close();
        return invoices;
    }

    @Override
    public int addInvoice(Invoice invoice) {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        session.save(invoice);
        //List<Invoice> invoices=session.createQuery("from Invoice order by id desc",Invoice.class).list();
        transaction.commit();
        session.close();
        return getInvoices().get(0).getInvoiceId();
    }

    public List<Invoice> getRevenueByYear(){
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Invoice> invoices=session.createNativeQuery("select monthname(dateOfOrder),sum(Amount) from invoice group by monthname(dateOfOrder) order by month(dateOfOrder)").list();
        transaction.commit();
        session.close();
        return invoices;
    }


    public List<Invoice> getRevenueByMonth(){
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Invoice> invoices=session.createNativeQuery("select date(dateOfOrder),sum(Amount) from invoice where month(dateOfOrder)=month(now()) group by dateOfOrder").list();
        transaction.commit();
        session.close();
        return invoices;
    }

    @Override
    public List<Invoice> getTodaysData() {
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Invoice> invoices=session.createNativeQuery("select count(Amount),sum(Amount) from invoice where dateOfOrder=curdate() group by dateOfOrder").list();
        transaction.commit();
        session.close();
        return invoices;
    }


}
