package com.services;

import com.model.Reminder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("data")
public class DatabaseReminderServiceImpl implements ReminderService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Reminder> getReminders() {
        Session session=sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        List<Reminder> reminders=session.createQuery("from Reminder", Reminder.class).list();
        transaction.commit();
        session.close();
        return reminders;
    }

    @Override
    public Reminder getReminderById(int reminderId) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Reminder reminder=session.get(Reminder.class,reminderId);
        transaction.commit();
        session.close();
        return reminder;
    }

    @Override
    public String createReminder(Reminder reminder) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(reminder);
        try {
            transaction.commit();
        }
        catch (Exception e){
            return "Cannot add the record";
        }
        session.close();
        return "Successfully added";
    }

    @Override
    public String updateReminder(Reminder reminder) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.update(reminder);
        try {
            transaction.commit();
        }
        catch (Exception e){
            return "Cannot update the record";
        }
        session.close();
        return "updated Successfully";
    }

    @Override
    public String deleteReminder(Reminder reminder) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.delete(reminder);
        try {
            transaction.commit();
        }
        catch (Exception e){
            return "Cannot delete the record";
        }
        session.close();
        return "deleted successfully";
    }
}
