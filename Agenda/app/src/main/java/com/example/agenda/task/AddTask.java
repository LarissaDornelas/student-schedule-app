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

public class AddTask extends AppCompatActivity {
    Toolbar toolbar;
    Calendar myCalendar = Calendar.getInstance();

    private EditText etAddDescription;
    private EditText etAddValue;
    private EditText etAddPriority;
    private EditText etAddDate;
    private String   diciplineSpinner;
    private String   typeSpinner;
    private DatePickerDialog datePickerDialog;
    String myFormat = "dd-MM-YYYY";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

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

        etAddDate.setOnTouchListener(new View.OnTouchListener() {
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

        etAddDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){


            @Override
            public void onFocusChange(View v, boolean b) {
                if(b) {
                    new DatePickerDialog(AddTask.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });


    }
    private void updateLabel(){

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etAddDate.setText(sdf.format(myCalendar.getTime()));
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

        TaskDAO dao = new TaskDAO(getBaseContext());

        String dicipline = diciplineSpinner;
        String description = etAddDescription.getText().toString();
        int value = Integer.parseInt(etAddValue.getText().toString());
        double note = 0;
        String date = etAddDate.getText().toString();
        String type = typeSpinner;
        int priority = Integer.parseInt(etAddPriority.getText().toString());


        dao.save(dicipline, description, value, note, date, type, priority);
        Toast.makeText(this, "Tarefa cadastrada com sucesso!",
                Toast.LENGTH_SHORT).show();

        etAddDescription.setText("");
        etAddPriority.setText("");
        etAddDate.setText("");
        etAddValue.setText("");



    }


}
