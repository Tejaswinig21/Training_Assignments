package com.database;

import com.model.Reminder;
import com.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DbConnection {

    private static Logger logger = LoggerFactory.getLogger(DbConnection.class);
    public static Connection connection;
    public static Statement statement;
    public static Connection setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "Teja2000@");
            statement=connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static ResultSet getReminderList() throws SQLException {
        Connection connection = setConnection();
        ResultSet resultSet=connection.createStatement().executeQuery("select * from remainder");
        return resultSet;
    }
    public static ResultSet getTaskList() throws SQLException {
        Connection connection = setConnection();
        ResultSet resultSet=connection.createStatement().executeQuery("select * from task");
        return resultSet;
    }
    public static void insertReminder(Reminder reminder) throws SQLException {
        Connection connection=setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into remainder values(?,?,?)");
        preparedStatement.setInt(1, reminder.getReminderId());
        preparedStatement.setDate(2, Date.valueOf(reminder.getDate()));
        preparedStatement.setInt(3, reminder.getRemindFrequency());
        preparedStatement.executeUpdate();
    }
    public static int insertTask(Task task) throws SQLException {
        Connection connection = setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into task values(?,?,?)");
        preparedStatement.setInt(1,task.getTaskId());
        preparedStatement.setString(2,task.getTaskName());
        preparedStatement.setString(3,task.getTaskType());
        return preparedStatement.executeUpdate();
    }
    public static void updateReminder(Reminder reminder) throws SQLException {
        Connection connection=setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update task set frequency=? where id=?");
        preparedStatement.setInt(2, reminder.getReminderId());
        preparedStatement.setInt(1, reminder.getRemindFrequency());
        preparedStatement.executeUpdate();
    }
    public static int updateTask(Task task) throws SQLException{
        Connection connection=setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update task set taskName=? where taskId=?");
        preparedStatement.setString(1,task.getTaskName());
        preparedStatement.setInt(2,task.getTaskId());
        return preparedStatement.executeUpdate();

    }
    public static void deleteReminder(Reminder reminder) throws SQLException {
        Connection connection=setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from remainder where id=?");
        preparedStatement.setInt(1, reminder.getReminderId());
        preparedStatement.executeUpdate();
    }
    public static int deleteTask(Task task) throws SQLException {
        Connection connection=setConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from task where id=?");
        preparedStatement.setInt(1,task.getTaskId());
        return preparedStatement.executeUpdate();
    }

}
