package com.example.agenda.task;

public class Task {
    private int id;
    private String dicipline;
    private String description;
    private int value;
    private double note;
    private String date;
    private String type;
    private int priority;

    public Task(int id, String dicipline, String description, int value, double note, String date, String type, int priority) {
        this.id = id;
        this.dicipline = dicipline;
        this.description = description;
        this.value = value;
        this.note = note;
        this.date = date;
        this.type = type;
        this.priority = priority;
    }

    public String getDicipline() {
        return dicipline;
    }

    public void setDicipline(String dicipline) {
        this.dicipline = dicipline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int priority) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "dicipline='" + dicipline + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", note=" + note +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", priority=" + priority +
                '}';
    }
}
