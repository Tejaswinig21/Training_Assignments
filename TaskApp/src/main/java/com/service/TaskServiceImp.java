package com.service;

import com.database.DbConnection;
import com.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskServiceImp implements TaskService {

    DbConnection dbConnection = new DbConnection();
    List<Task> taskList= new ArrayList<Task>();

    @Override
    public List<Task> getTasks() {
        try {
            ResultSet resultSet = DbConnection.getTaskList();

            while (resultSet.next()){
                Task task = new Task();
                task.setTaskId(resultSet.getInt(1));
                task.setTaskName(resultSet.getString(2));
                task.setTaskType(resultSet.getString(3));
                taskList.add(task);
            }
            return taskList;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Task getTaskById(int TaskId) {
        return null;
    }

    @Override
    public int createTask(Task task) throws SQLException {
        int x=dbConnection.insertTask(task);
        taskList.add(task);

        return x;
    }

    @Override
    public int updateTask(Task task) throws SQLException {
        int x1=dbConnection.updateTask(task);
        return x1;
    }
    @Override
    public int deleteTask(Task task) throws SQLException {
        int x2=dbConnection.deleteTask(task);
        return x2;

    }
}
