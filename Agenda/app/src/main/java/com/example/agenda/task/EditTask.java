package com.example.agenda.task;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.SharedResources;
import com.example.agenda.dicipline.DiciplineDAO;
import com.example.agenda.task.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditTask extends AppCompatActivity {
        Toolbar toolbar;
        Calendar myCalendar = Calendar.getInstance();


        private EditText etDescription;
        private EditText etValue;
        private EditText etPriority;
        private EditText etDate;
        private EditText etNote;
//
        private String diciplineSpinner;
        private String typeSpinner;

        private DatePickerDialog datePickerDialog;
                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
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

        etDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setDateTimeField();
                // showDialog(DATE_DIALOG_ID);
                return false;
            }
        });

        DiciplineDAO diciplineDAO = new DiciplineDAO(getBaseContext());
        TaskDAO taskDAO = new TaskDAO(getBaseContext());

        List<String> typeOption = new ArrayList<String>();
        List<String> diciplineOption = new ArrayList<String>();
        typeOption.add("Prova");
        typeOption.add("Trabalho");
        typeOption.add("Seminário");


        for(int i=0; i<diciplineDAO.getAll().size();i++){
            diciplineOption.add(diciplineDAO.getAll().get(i).getName());
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




        etDescription.setText(taskDAO.getAll().get(position).getDescription());
        etValue.setText(Integer.toString(taskDAO.getAll().get(position).getValue()));
        etPriority.setText(Integer.toString(taskDAO.getAll().get(position).getPriority()));
        etDate.setText(taskDAO.getAll().get(position).getDate());
        if(taskDAO.getAll().get(position).getNote() > 0) {
            etNote.setText(Double.toString(taskDAO.getAll().get(position).getNote()));
        }
        type.setSelection(dataAdapterType.getPosition(taskDAO.getAll().get(position).getType()));
        dicipline.setSelection(dataAdapterDicipline.getPosition(taskDAO.getAll().get(position).getDicipline()));







    }

    private void setDateTimeField() {


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){


            @Override
            public void onFocusChange(View v, boolean b) {
                if(b) {
                    new DatePickerDialog(EditTask.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });


    }
    private void updateLabel(){

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etDate.setText(sdf.format(myCalendar.getTime()));
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

        TaskDAO dao = new TaskDAO(getBaseContext());

        Toast.makeText(this, "Tarefa atualizada com sucesso!",
                Toast.LENGTH_SHORT).show();

        String dicipline = diciplineSpinner;
        String description = etDescription.getText().toString();
        int value = Integer.parseInt(etValue.getText().toString());
        double note = 0;

        if(!etNote.getText().toString().isEmpty()) {
            note = Double.parseDouble(etNote.getText().toString());
        }



        String date = etDate.getText().toString();
        String type = typeSpinner;
        int priority = Integer.parseInt(etPriority.getText().toString());

        dao.save(dao.getAll().get(position).getId(), dicipline, description, value, note, date, type, priority);


    }

    public void delete(View view){
        TaskDAO dao = new TaskDAO(getBaseContext());

        dao.delete(dao.getAll().get(position).getId());
        Toast.makeText(this, "Tarefa removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

}

