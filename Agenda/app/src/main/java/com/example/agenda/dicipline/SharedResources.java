package com.example.agenda.dicipline;

import java.util.ArrayList;

class SharedResources {
    public static SharedResources shared = null;

    //Singleton elements
    private static ArrayList<Dicipline> diciplines;

    public static SharedResources getInstance() {
        if(shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    private SharedResources() {
        diciplines = new ArrayList<>();
    }

    public ArrayList<Dicipline> getDiciplines() {
        return diciplines;
    }
}
