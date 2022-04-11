package com.Pramati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student extends Course {
    //static HashMap<String, List> hm=new HashMap<>();
    int student_id;
    String student_name;
    String username;
    String password;
    //ArrayList<Course> courses =new ArrayList<>();
    public ArrayList<Course> getCr() {
        return cr;
    }

    public void setCr(ArrayList<Course> cr) {
        this.cr = cr;
    }

    ArrayList<Course> cr=new ArrayList<>();




    Student(){

    }
    public Student(int student_id, String student_name, String username, String password, ArrayList corses){
        super();
        this.student_id=student_id;
        this.student_name=student_name;
        this.username=username;
        this.password=password;
        this.cr=corses;
        /*hm.put(username,new ArrayList<>());
        hm.get(username).add(student_id);
        hm.get(username).add(username);
        hm.get(username).add(password);
        hm.get(username).add(courses);*/
    }
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;

    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cr=" + cr +
                '}';
    }
}
