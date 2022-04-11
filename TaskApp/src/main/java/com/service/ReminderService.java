package com.service;

import com.model.Reminder;

import java.sql.SQLException;
import java.util.List;

public interface ReminderService {
    List<Reminder> getReminders();
    Reminder getReminderById(int reminderId);
    Reminder createReminder(Reminder reminder) throws SQLException;
    Reminder updateReminder(Reminder reminder) throws SQLException;
    Reminder deleteReminder(Reminder reminder) throws SQLException;

}
