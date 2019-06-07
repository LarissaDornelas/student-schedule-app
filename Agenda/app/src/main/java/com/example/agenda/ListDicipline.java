package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDicipline extends AppCompatActivity {

    private ArrayList<Dicipline> diciplines;
    private ListView lvDicipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dicipline);
        diciplines = new ArrayList<>();
        diciplines.add(new Dicipline("Programação Móvel", 7, 3, 16, 70, true  ));
        diciplines.add(new Dicipline("Teoria dos Grafos", 7, 3, 16, 70, true  ));
        diciplines.add(new Dicipline("Programação Línear", 7, 3, 16, 70, true  ));
        diciplines.add(new Dicipline("Matemática Discreta", 7, 3, 16, 70, true  ));
        diciplines.add(new Dicipline("Gestão da Tecnologia da Informação", 7, 3, 16, 70, true  ));

        lvDicipline = findViewById(R.id.lvDicipline);
        lvDicipline.setAdapter(new DiciplineAdapter(diciplines, this));
    }
    public void addDicipline(View view) {
        Intent it = new Intent(
                this, AddDicipline.class);
        startActivity(it);
    }
}
