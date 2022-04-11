package com.service;

import com.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    Task getTaskById(int TaskId);
    int createTask(Task task) throws SQLException;
    int updateTask(Task task) throws SQLException;
    int deleteTask(Task task) throws SQLException;

}
