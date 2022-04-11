package com.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Reminder;
import com.service.ReminderService;
import com.service.ReminderServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReminderServlet extends HttpServlet {

    private static final Gson gson=new GsonBuilder().create();
    private static final ReminderService reminderService =new ReminderServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getPathInfo()==null){
            List<Reminder> reminders =reminderService.getReminders();
            resp.setStatus(200);
            resp.setHeader("Content-Type:","application/json");
            resp.getOutputStream().println(gson.toJson(reminders));
        }
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Reminder reminder =gson.fromJson(req.getReader(), Reminder.class);
        try {
            reminderService.createReminder(reminder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(201);
        resp.setHeader("Content-type","application/json");
        resp.getOutputStream().println(gson.toJson(reminder));
    }

    protected void doPut(HttpServletRequest req,HttpServletResponse rep) throws IOException{
        Reminder reminder =gson.fromJson(req.getReader(), Reminder.class);
        try {
            reminderService.updateReminder(reminder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rep.setStatus(201);
        rep.setHeader("Content-type","application/json");
        rep.getOutputStream().println(gson.toJson(reminder));
    }

    protected void doDelete(HttpServletRequest req,HttpServletResponse res) throws IOException{
        Reminder reminder =gson.fromJson(req.getReader(), Reminder.class);
        try {
            reminderService.deleteReminder(reminder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        res.setStatus(201);
        res.setHeader("Content-type","application/json");
        res.getOutputStream().println(gson.toJson(reminder));
    }
}
