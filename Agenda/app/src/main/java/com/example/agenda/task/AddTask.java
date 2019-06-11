package com.example.agenda.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.SharedResources;
import com.example.agenda.task.Task;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {
    Toolbar toolbar;
    private EditText etAddDescription;
    private EditText etAddValue;
    private EditText etAddPriority;
    private EditText etAddDate;
    private String   diciplineSpinner;
    private String   typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etAddDescription = findViewById(R.id.etAddDescription);
        etAddValue = findViewById(R.id.etAddValue);
        etAddPriority = findViewById(R.id.etAddPriority);
        etAddDate = findViewById(R.id.etAddDate);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastrar Tarefa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Spinner dicipline = (Spinner) findViewById(R.id.diciplineSpinner);
        Spinner type = (Spinner) findViewById(R.id.type);



        List<String> typeOption = new ArrayList<String>();
        List<String> diciplineOption = new ArrayList<String>();
        typeOption.add("Prova");
        typeOption.add("Trabalho");
        typeOption.add("Seminário");

        diciplineOption.add("teste1");
        diciplineOption.add("teste2");

        for(int i=0; i<SharedResources.getInstance().getDiciplines().size();i++){
            diciplineOption.add(SharedResources.getInstance().getDiciplines().get(i).getName());
        }

        ArrayAdapter<String> dataAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeOption);
        ArrayAdapter<String> dataAdapterDicipline = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diciplineOption);

        dataAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterDicipline.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(dataAdapterType);
        dicipline.setAdapter(dataAdapterDicipline);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                typeSpinner = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dicipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                diciplineSpinner = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, ListTask.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }

    public void addNewTask(View view){

        String dicipline = diciplineSpinner;
        String description = etAddDescription.getText().toString();
        int value = Integer.parseInt(etAddValue.getText().toString());
        double note = 0;
        String date = etAddDate.getText().toString();
        String type = typeSpinner;
        int priority = Integer.parseInt(etAddPriority.getText().toString());

        Task task = new Task(dicipline, description, value, note, date, type, priority);
        SharedResources.getInstance().getTasks().add(task);

        Toast.makeText(this, "Tarefa cadastrada com sucesso!",
                Toast.LENGTH_SHORT).show();

        etAddDescription.setText("");
        etAddPriority.setText("");
        etAddDate.setText("");
        etAddValue.setText("");
        Toast.makeText(this, "You Clicked ", Toast.LENGTH_LONG).show();



    }


}
