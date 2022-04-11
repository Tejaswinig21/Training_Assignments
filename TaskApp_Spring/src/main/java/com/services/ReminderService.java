package com.services;

import com.model.Reminder;

import java.util.List;

public interface ReminderService {
    List<Reminder> getReminders();
    Reminder getReminderById(int reminderId);
    String createReminder(Reminder reminder);
    String updateReminder(Reminder reminder);
    String deleteReminder(Reminder reminder);
}
