package com.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Task;
import com.service.TaskService;
import com.service.TaskServiceImp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TaskServlet extends HttpServlet {

    private static final Gson gson=new GsonBuilder().create();
    private static final TaskService taskService =new TaskServiceImp();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getPathInfo()==null){
            List<Task> tasks=taskService.getTasks();
            resp.setStatus(200);
            resp.setHeader("Content-Type:","application/json");
            resp.getOutputStream().println(gson.toJson(tasks));
        }
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Task task=gson.fromJson(req.getReader(), Task.class);

        try {
            int x=taskService.createTask(task);
            if(x>0)
            resp.getOutputStream().println("The status of adding a task is true");

        } catch (SQLException e) {
            resp.getOutputStream().println("The status of adding a task is false");
            e.printStackTrace();
        }
        resp.setStatus(201);
        resp.setHeader("Content-type","application/json");
        //resp.getOutputStream().println(gson.toJson(task));
    }

    protected void doPut(HttpServletRequest req,HttpServletResponse rep) throws IOException{
        Task task=gson.fromJson(req.getReader(), Task.class);
        try {
            int x1=taskService.updateTask(task);
            if(x1>0)
                rep.getOutputStream().println("The status of updating a task is true");
        } catch (SQLException e) {
            rep.getOutputStream().println("The status of updating a task is false");
            e.printStackTrace();
        }
        rep.setStatus(201);
        rep.setHeader("Content-type","application/json");
        rep.getOutputStream().println(gson.toJson(task));
    }

    protected void doDelete(HttpServletRequest req,HttpServletResponse res) throws IOException{
        Task task=gson.fromJson(req.getReader(), Task.class);
        try {
            int x2=taskService.deleteTask(task);
            if(x2>0)
                res.getOutputStream().println("The status of adding a task is true");
        } catch (SQLException e) {
            res.getOutputStream().println("The status of deleting a task is false");
            e.printStackTrace();
        }
        res.setStatus(201);
        res.setHeader("Content-type","application/json");
        res.getOutputStream().println(gson.toJson(task));
    }
}
