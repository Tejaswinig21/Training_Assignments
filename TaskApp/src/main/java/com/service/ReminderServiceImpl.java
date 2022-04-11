package com.service;

import com.database.DbConnection;
import com.model.Reminder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.database.DbConnection.insertReminder;
import static com.database.DbConnection.setConnection;

public class ReminderServiceImpl implements ReminderService {

    DbConnection dbConnection = new DbConnection();
    List<Reminder> reminderList = new ArrayList<>();

    @Override
    public List<Reminder> getReminders() {

        try {
            ResultSet resultSet = DbConnection.getReminderList();

            while (resultSet.next()){
                Reminder reminder =new Reminder();
                reminder.setReminderId(resultSet.getInt(1));
                reminder.setDate(String.valueOf(resultSet.getDate(2)));
                reminder.setRemindFrequency(resultSet.getInt(3));
                reminderList.add(reminder);
            }
            return reminderList;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Reminder getReminderById(int reminderId) {
        return null;
    }

    @Override
    public Reminder createReminder(Reminder reminder) throws SQLException {
        insertReminder(reminder);
        reminderList.add(reminder);
        return reminder;
    }

    @Override
    public Reminder updateReminder(Reminder reminder) throws SQLException {
        DbConnection.updateReminder(reminder);
        return reminder;
    }

    @Override
    public Reminder deleteReminder(Reminder reminder) throws SQLException {
        DbConnection.deleteReminder(reminder);
        return reminder;
    }
}
