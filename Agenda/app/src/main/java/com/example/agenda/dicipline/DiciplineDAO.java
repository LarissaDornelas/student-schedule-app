package com.example.agenda.dicipline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.agenda.DbGateway;

import java.util.ArrayList;
import java.util.List;

public class DiciplineDAO {

    private final String TABLE_DICIPLINE = "Dicipline";
    private DbGateway gw;

    public DiciplineDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean save(String name, int semester, int faults, int faultLimit, float goal, boolean progress){
        return save(0,name, semester, faults, faultLimit, goal, progress);
    }

    public boolean save(int id, String name, int semester, int faults, int faultLimit, float goal, boolean progress){
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Semester", semester);
        cv.put("Faults", faults);
        cv.put("FaultLimit", faultLimit);
        cv.put("Goal", goal);
        cv.put("Progress", progress ? 1 : 0);
        if(id > 0)
            return gw.getDatabase().update(TABLE_DICIPLINE, cv, "ID=?", new String[]{id + ""}) > 0;

        else
            return gw.getDatabase().insert(TABLE_DICIPLINE, null, cv) > 0;

    }

    public ArrayList<Dicipline> getAll(){
        ArrayList<Dicipline> diciplines = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Dicipline", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            int semester = cursor.getInt(cursor.getColumnIndex("Semester"));
            int faults = cursor.getInt(cursor.getColumnIndex("Faults"));
            int faultLimit = cursor.getInt(cursor.getColumnIndex("FaultLimit"));
            float goal = cursor.getFloat(cursor.getColumnIndex("Goal"));
            boolean progress = cursor.getInt(cursor.getColumnIndex("Progress")) > 0;

            diciplines.add(new Dicipline(id, name, semester, faults, faultLimit, goal, progress));
        }
        cursor.close();
        return diciplines;
    }

    public boolean delete(int id){
        return gw.getDatabase().delete(TABLE_DICIPLINE, "ID=?", new String[]{id + ""}) > 0;
    }


}