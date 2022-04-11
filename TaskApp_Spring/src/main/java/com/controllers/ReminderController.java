package com.controllers;

import com.model.Reminder;
import com.services.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/remainder")
public class ReminderController<reminder> {

    @Autowired
    @Qualifier("data")
    private ReminderService reminderService;

    @RequestMapping(value = "/getReminders",method = RequestMethod.GET)
    public List<Reminder> getReminders(){
        return reminderService.getReminders();
    }

    @RequestMapping(value = "/getReminderById/{id}",method = RequestMethod.GET)
    public Reminder getBookById(@PathVariable(name = "id") int reminderId){
        return reminderService.getReminderById(reminderId);
    }

    @RequestMapping(value = "/add reminder",consumes = "application/json",produces = "application/json",method = RequestMethod.POST)
    public String createBook(@RequestBody Reminder reminder){
        return reminderService.createReminder(reminder);
    }

    @RequestMapping(value = "/update reminder",method = RequestMethod.PUT)
    public String updateReminder(@RequestBody Reminder reminder){
        return reminderService.updateReminder(reminder);
    }

    @RequestMapping(value = "delete reminder",method = RequestMethod.DELETE)
    public String deleteReminder(@RequestBody Reminder reminder){
        return reminderService.deleteReminder(reminder);
    }

}
