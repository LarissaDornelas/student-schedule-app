package com.example.agenda;

import android.content.Context;

import com.example.agenda.dicipline.Dicipline;
import com.example.agenda.task.Task;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StoreData {
    private ArrayList<Dicipline> diciplines;
    private ArrayList<Task> tasks;
    private static String FILENAME_DICIPLINE = "dicipline";
    private static String FILENAME_TASK = "task";
    FileOutputStream outputStream = null;

    public StoreData(ArrayList<Dicipline> diciplines, ArrayList<Task> tasks) {
        this.diciplines = diciplines;
        this.tasks = tasks;
        storeDiciplines();
        storeTasks();
    }

    public void storeDiciplines() {


    }



    public void storeTasks(){

    }
}