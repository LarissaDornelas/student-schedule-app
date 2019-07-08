package com.example.agenda.dicipline;

public class Dicipline {
    private int id;
    private String name;
    private int semester;
    private int faults;
    private int faultLimit;
    private float goal;
    private boolean progress;

    public Dicipline(int id, String name, int semester, int faults, int faultLimit, float goal, boolean progress) {

        this.id = id;
        this.name = name;
        this.semester = semester;
        this.faults = faults;
        this.faultLimit = faultLimit;
        this.goal = goal;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public int getFaultLimit() {
        return faultLimit;
    }

    public void setFaultLimit(int faultLimit) {
        this.faultLimit = faultLimit;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }


    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public int getId(){return this.id;}

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dicipline{" +
                "name='" + name + '\'' +
                ", semester=" + semester +
                ", faults=" + faults +
                ", faultLimit=" + faultLimit +
                ", goal=" + goal +
                ", progress=" + progress +
                '}';
    }
}
