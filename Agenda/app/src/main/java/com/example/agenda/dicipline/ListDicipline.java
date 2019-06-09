package com.example.agenda.dicipline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.agenda.MainActivity;
import com.example.agenda.R;

import java.util.ArrayList;

public class ListDicipline extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvDiciplines;
    DiciplineListAdapter listAdapter;
    ArrayList<Dicipline>diciplines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dicipline);

        diciplines = new ArrayList<>();
        diciplines.add(new Dicipline("Sistemas Distribuídos", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Operacionais", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Distribuídos", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Operacionais", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Matemática Discreta", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Distribuídos", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Operacionais", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Matemática Discreta", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Distribuídos", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Operacionais", 5, 3,16,70,false));
        diciplines.add(new Dicipline("Matemática Discreta", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Distribuídos", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Sistemas Operacionais", 5, 3,16,70,true));
        diciplines.add(new Dicipline("Matemática Discreta", 5, 3,16,70,true));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diciplinas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        listAdapter = new DiciplineListAdapter(this,diciplines);
        lvDiciplines = findViewById(R.id.lvDiciplines);

        lvDiciplines.setAdapter(listAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }




}
