package com.example.aboubakr.actividad4adato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button alumnado;
    Button Profesor,btnAsignatura;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alumnado = (Button) findViewById(R.id.btnAlumnado);
        Profesor = (Button) findViewById(R.id.btnProfesor);
        btnAsignatura = (Button) findViewById(R.id.btnAsignatura);

        btnAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Asignaturas.class);
                startActivity(i);
            }
        });

        alumnado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Alumnado.class);
                startActivity(i);
            }
        });

        Profesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Profesor.class);
                startActivity(i);
            }
        });
    }
}

