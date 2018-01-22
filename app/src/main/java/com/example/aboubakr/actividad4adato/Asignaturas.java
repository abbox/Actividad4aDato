package com.example.aboubakr.actividad4adato;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Asignaturas extends AppCompatActivity {


    EditText ediID,ediNombre,ediHoras;
    Button guardar,consula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        final MyDADatos mydato = new MyDADatos(getApplicationContext());


        guardar =(Button)findViewById(R.id.btnGuardarA);
        consula =(Button)findViewById(R.id.btnConsula);
        ediID = (EditText)findViewById(R.id.EdiID);
        ediNombre = (EditText)findViewById(R.id.EditNombreA);
        ediHoras = (EditText)findViewById(R.id.Edithoras);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mydato.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(MyDADatos.CAMPO_ID,ediID.getText().toString());
                valores.put(MyDADatos.CAMPO_NOMBREA,ediNombre.getText().toString());
                valores.put(MyDADatos.CAMPO_HORA,ediHoras.getText().toString());

                Long idGurdado = db.insert(MyDADatos.NOMBRE_TABLA2, MyDADatos.CAMPO_ID,valores);
                Toast.makeText(getApplicationContext(),"se guardo : "+idGurdado,Toast.LENGTH_LONG).show();

            }
        });

        consula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Asignaturas.this,Consultas.class);
                startActivity(i);
            }
        });
    }
}
