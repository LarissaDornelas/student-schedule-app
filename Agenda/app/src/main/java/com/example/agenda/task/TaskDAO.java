package com.example.agenda.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.agenda.DbGateway;
import com.example.agenda.task.Task;

import java.util.ArrayList;


public class TaskDAO {

    private final String TABLE_TASK = "Task";
    private DbGateway gw;

    public TaskDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean save(String dicipline, String description, int value, double note, String date, String type, int priority){
        return save(0,dicipline, description, value, note, date, type, priority);
    }

    public boolean save(int id, String dicipline, String description, int value, double note, String date, String type, int priority){
        ContentValues cv = new ContentValues();
        cv.put("Dicipline", dicipline);
        cv.put("Description",description);
        cv.put("Value", value);
        cv.put("Note", note);
        cv.put("Date", date);
        cv.put("Type", type);
        cv.put("Priority", priority);
        if(id > 0)
            return gw.getDatabase().update(TABLE_TASK, cv, "ID=?", new String[]{id + ""}) > 0;

        else
            return gw.getDatabase().insert(TABLE_TASK, null, cv) > 0;

    }

    public ArrayList<Task> getAll(){
        ArrayList<Task> Tasks = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Task ORDER BY (substr(date, 7, 4) || '-' || substr(date, 4, 2) || '-' || substr(date, 1, 2));\n" +
                "\n ", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String dicipline = cursor.getString(cursor.getColumnIndex("Dicipline"));
            String description = cursor.getString(cursor.getColumnIndex("Description"));
            int value = cursor.getInt(cursor.getColumnIndex("Value"));
            double note = cursor.getDouble(cursor.getColumnIndex("Note"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            String type = cursor.getString(cursor.getColumnIndex("Type"));
            int priority = cursor.getInt(cursor.getColumnIndex("Priority"));

            Tasks.add(new Task(id, dicipline, description, value, note, date, type, priority));
            System.out.println(date);

        }
        cursor.close();
        return Tasks;
    }

    public boolean delete(int id){
        return gw.getDatabase().delete(TABLE_TASK, "ID=?", new String[]{id + ""}) > 0;
    }


}
