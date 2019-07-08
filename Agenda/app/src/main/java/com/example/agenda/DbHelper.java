package com.example.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_DICIPLINE = "CREATE TABLE Dicipline (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, " +
                                            "Semester INTEGER NOT NULL, Faults INTEGER NOT NULL, FaultLimit INTEGER NOT NULL, " +
                                            "Goal REAL NOT NULL, Progress INTEGER NOT NULL);";

    private final String CREATE_TASK      = "CREATE TABLE Task (ID INTEGER PRIMARY KEY AUTOINCREMENT, Description TEXT NOT NULL, " +
                                            "Value INTEGER NOT NULL, Note REAL, Date TEXT NOT NULL, Type TEXT NOT NULL, " +
                                            "Priority INTEGER NOT NULL, Dicipline TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DICIPLINE);
        db.execSQL(CREATE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


