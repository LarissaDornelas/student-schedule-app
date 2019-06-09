package com.example.agenda.dicipline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.MainActivity;
import com.example.agenda.R;

public class AddDicipline extends AppCompatActivity {
    Toolbar toolbar;
    private EditText etAddName;
    private EditText etAddSemester;
    private EditText etAddFaultLimit;
    private EditText etAddGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dicipline);
            etAddName = findViewById(R.id.etAddName);
            etAddSemester = findViewById(R.id.etAddSemester);
            etAddFaultLimit = findViewById(R.id.etAddFaultLimit);
            etAddGoal = findViewById(R.id.etAddGoal);

          toolbar = (Toolbar) findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);
          getSupportActionBar().setTitle("Cadastrar Disciplina");
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          getSupportActionBar().setHomeButtonEnabled(true);


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

    public void addNewDicipline(View view){

          String name = etAddName.getText().toString();
          int semester = Integer.parseInt(etAddSemester.getText().toString());
          int faults = 0;
          int faultLimit = Integer.parseInt(etAddFaultLimit.getText().toString());
          float goal = 70;
          boolean progress = true;

          Dicipline dicipline = new Dicipline(name, semester, faults, faultLimit, goal, progress);
          SharedResources.getInstance().getDiciplines().add(dicipline);

        Toast.makeText(this, "Disciplina cadastrada com sucesso!",
                Toast.LENGTH_SHORT).show();

        etAddName.setText("");
        etAddSemester.setText("");
        etAddFaultLimit.setText("");
        etAddGoal.setText("");



    }


}
