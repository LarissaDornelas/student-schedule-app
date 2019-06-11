package com.example.agenda;

import com.example.agenda.dicipline.Dicipline;
import com.example.agenda.task.Task;

import java.util.ArrayList;

public class SharedResources {
    public static SharedResources shared = null;

    //Singleton elements
    private static ArrayList<Dicipline> diciplines;
    private static ArrayList<Task> tasks;

    public static SharedResources getInstance() {
        if(shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    private SharedResources() {

        diciplines = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public ArrayList<Dicipline> getDiciplines() { return diciplines; }
    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
