package com.example.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.agenda.dicipline.ListDicipline;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDicipline(View view){
        Intent it = new Intent(
                this, ListDicipline.class);
        startActivity(it);
    }
    public void clickTasks(View view){
        Toast.makeText(this, "Você clicou na opção 'Tarefas'!", Toast.LENGTH_SHORT).show();
    }
    public void clickProfile(View view){
        Toast.makeText(this, "Você clicou na opção 'Perfil'!", Toast.LENGTH_SHORT).show();
    }
    public void clickSettings(View view){
        Toast.makeText(this, "Você clicou na opção 'Configurações'!", Toast.LENGTH_SHORT).show();
    }
}
