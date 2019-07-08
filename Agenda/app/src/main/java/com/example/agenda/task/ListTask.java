package com.example.agenda.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agenda.MainActivity;
import com.example.agenda.R;
import com.example.agenda.SharedResources;

import com.example.agenda.dicipline.AddDicipline;
import com.example.agenda.dicipline.DiciplineDAO;
import com.example.agenda.task.AddTask;
import com.example.agenda.dicipline.Dicipline;
import com.example.agenda.task.TaskListAdapter;

public class ListTask extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tarefas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        lvTasks = findViewById(R.id.lvTasks);



    }

    @Override
    protected void onResume() {
        super.onResume();

        TaskDAO dao = new TaskDAO(getBaseContext());
        lvTasks.setAdapter(new TaskListAdapter(this, dao.getAll()));
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(
                        ListTask.this,
                       EditTask.class);
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

    public void clickAddTask(View view){

        DiciplineDAO diciplineDAO = new DiciplineDAO(getBaseContext());
        if(diciplineDAO.getAll().isEmpty()){
            Toast.makeText(this, "Você não possui disciplinas cadastradas.\nPrimeiro cadastre uma disciplina.", Toast.LENGTH_SHORT).show();
           }
        else {
            Intent it = new Intent(
                    this, AddTask.class);
            startActivity(it);
        }
    }
}
