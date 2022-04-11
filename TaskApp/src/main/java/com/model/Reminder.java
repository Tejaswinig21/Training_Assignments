
package com.model;

public class Reminder {
    private int reminderId;
    private String date;
    private int remindFrequency;

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRemindFrequency() {
        return remindFrequency;
    }

    public void setRemindFrequency(int remindFrequency) {
        this.remindFrequency = remindFrequency;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", date='" + date + '\'' +
                ", remindFrequency=" + remindFrequency +
                '}';
    }
}
