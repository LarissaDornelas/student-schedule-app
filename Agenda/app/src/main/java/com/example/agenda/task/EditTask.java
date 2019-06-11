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

public class EditTask extends AppCompatActivity {
        Toolbar toolbar;
        private EditText etDescription;
        private EditText etValue;
        private EditText etPriority;
        private EditText etDate;
        private EditText etNote;
//
        private String diciplineSpinner;
        private String typeSpinner;
//
//
      private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        etDescription = findViewById(R.id.etDescription);
        etValue = findViewById(R.id.etValue);
        etPriority = findViewById(R.id.etPriority);
        etDate = findViewById(R.id.etDate);
        etNote = findViewById(R.id.etNote);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Tarefa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

         Spinner dicipline = (Spinner) findViewById(R.id.diciplineSpinner);
         Spinner type = (Spinner) findViewById(R.id.type);

         List<String> typeOption = new ArrayList<String>();
         List<String> diciplineOption = new ArrayList<String>();
          typeOption.add("Prova");
          typeOption.add("Trabalho");
          typeOption.add("Seminário");

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




        etDescription.setText(SharedResources.getInstance().getTasks().get(position).getDescription());
        etValue.setText(Integer.toString(SharedResources.getInstance().getTasks().get(position).getValue()));
        etPriority.setText(Integer.toString(SharedResources.getInstance().getTasks().get(position).getPriority()));
        etDate.setText(SharedResources.getInstance().getTasks().get(position).getDescription());
        etNote.setText(Double.toString(SharedResources.getInstance().getTasks().get(position).getNote()));
        type.setSelection(dataAdapterType.getPosition(SharedResources.getInstance().getTasks().get(position).getType()));
        dicipline.setSelection(dataAdapterDicipline.getPosition(SharedResources.getInstance().getTasks().get(position).getDicipline()));







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

    public void confirm(View view){

         Toast.makeText(this, "Tarefa atualizada com sucesso!",
                Toast.LENGTH_SHORT).show();

        String dicipline = diciplineSpinner;
        String description = etDescription.getText().toString();
        int value = Integer.parseInt(etValue.getText().toString());
        double note = Double.parseDouble(etNote.getText().toString());
        String date = etDate.getText().toString();
        String type = typeSpinner;
        int priority = Integer.parseInt(etPriority.getText().toString());

        Task task = new Task(dicipline, description, value, note, date, type, priority);
        SharedResources.getInstance().getTasks().set(position,task);

    }

    public void delete(View view){
        SharedResources.getInstance().getTasks().remove(position);
        Toast.makeText(this, "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

}

