package com.example.agenda.dicipline;

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

import java.util.ArrayList;
import java.util.List;

public class EditDicipline extends AppCompatActivity {
    Toolbar toolbar;
    private EditText etName;
    private EditText etSemester;
    private EditText etFaultLimit;
    private EditText etGoal;
    private EditText etFaults;
    private boolean progress;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dicipline);
        etName = findViewById(R.id.etName);
        etSemester = findViewById(R.id.etSemester);
        etFaultLimit = findViewById(R.id.etFaultLimit);
        etGoal = findViewById(R.id.etGoal);
        etFaults = findViewById(R.id.etFaults);


        Intent it = getIntent();
        position = it.getIntExtra("position", 0);
        DiciplineDAO dao = new DiciplineDAO(getBaseContext());


          etName.setText(dao.getAll().get(position).getName());
          etSemester.setText(Integer.toString(dao.getAll().get(position).getSemester()));
          etFaultLimit.setText(Integer.toString(dao.getAll().get(position).getFaultLimit()));
          etGoal.setText(Float.toString(dao.getAll().get(position).getGoal()));
          etFaults.setText(Integer.toString(dao.getAll().get(position).getFaults()));
          progress = dao.getAll().get(position).isProgress();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Disciplina");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        List<String> status = new ArrayList<String>();
        status.add("Em andamento");
        status.add("Concluída");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        if(progress){
            spinner.setSelection(dataAdapter.getPosition("Em andamento"));
        }
        else {
            spinner.setSelection(dataAdapter.getPosition("Concluída"));
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                if(item == "Em andamento"){
                    progress = true;
                }
                else{
                    progress = false;
                }
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
                startActivity(new Intent(this, ListDicipline.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }

    public void confirm(View view){
        DiciplineDAO dao = new DiciplineDAO(getBaseContext());
        String name = etName.getText().toString();
        int semester = Integer.parseInt(etSemester.getText().toString());
        int faults = Integer.parseInt(etFaults.getText().toString());
        int faultLimit = Integer.parseInt(etFaultLimit.getText().toString());
        float goal = Float.parseFloat(etGoal.getText().toString());
        boolean progress = this.progress;

        dao.save(dao.getAll().get(position).getId(), name, semester, faults, faultLimit, goal, progress);
        Toast.makeText(this, "Disciplina editada com sucesso", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        DiciplineDAO dao = new DiciplineDAO(getBaseContext());
        dao.delete(dao.getAll().get(position).getId());
        Toast.makeText(this, "Disciplina removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}
