package com.example.agenda.dicipline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.agenda.MainActivity;
import com.example.agenda.R;

import java.util.ArrayList;

public class ListDicipline extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvDiciplines;
    DiciplineListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dicipline);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diciplinas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        lvDiciplines = findViewById(R.id.lvDiciplines);


    }
    @Override
    protected void onResume() {
        super.onResume();

        lvDiciplines.setAdapter(new DiciplineListAdapter(this, SharedResources.getInstance().getDiciplines()));
        lvDiciplines.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(
                        ListDicipline.this,
                        EditDicipline.class);
                it.putExtra("position", position);
                startActivity(it);
            }
        });
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

    public void clickAddDicipline(View view){
        Intent it = new Intent(
                this, AddDicipline.class);
        startActivity(it);
    }




}
