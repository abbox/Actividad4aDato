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

public class Alumnado extends AppCompatActivity {

    Button guardar;
    Button atras,borrar,consultar;
    EditText ediNombre,ediEdad,ediCiclo,ediCurso,ediNota,ediBorrrado;




    public Alumnado() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnado);
        final MyDADatos mydato = new MyDADatos(getApplicationContext());



        guardar =(Button)findViewById(R.id.btnGuardar);
        atras = (Button)findViewById(R.id.btnAtras);
        borrar = (Button)findViewById(R.id.btnBorrar);
        ediNombre = (EditText)findViewById(R.id.ediNombre);
        ediEdad = (EditText)findViewById(R.id.ediEdad);
        ediCiclo = (EditText)findViewById(R.id.ediCiclo);
        ediCurso = (EditText)findViewById(R.id.ediCurso);
        ediNota = (EditText)findViewById(R.id.ediNota);
        consultar =(Button)findViewById(R.id.btnConsultar);
        ediBorrrado=(EditText)findViewById(R.id.ediBorrrado);


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Alumnado.this,Consultas.class);
                startActivity(i);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mydato.getWritableDatabase();
                ContentValues valores = new ContentValues();
                //valores.put(MyDADatos.CAMPO_NOMBRE,ediNombre.getText().toString());
                valores.put(MyDADatos.CAMPO_EDAD,ediEdad.getText().toString());
                valores.put(MyDADatos.CAMPO_CICLO,ediCiclo.getText().toString());
                valores.put(MyDADatos.CAMPO_CURSO,ediCurso.getText().toString());
                valores.put(MyDADatos.CAMPO_NOTAMEDIA,ediNota.getText().toString());

                Long idGurdado = db.insert(MyDADatos.NOMBRE_TABLA, MyDADatos.CAMPO_NOMBRE,valores);
                Toast.makeText(getApplicationContext(),"se guardo : "+idGurdado,Toast.LENGTH_LONG).show();


            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mydato.getWritableDatabase();
                String[] parametros = {ediBorrrado.getText().toString()};

                db.delete(MyDADatos.NOMBRE_TABLA,MyDADatos.CAMPO_NOMBRE+"=?",parametros);
                Toast.makeText(getApplicationContext(),"se borro correctamente ",Toast.LENGTH_LONG).show();
                limpiar();
                db.close();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Alumnado.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void limpiar(){
        ediNombre.setText("");
        ediEdad.setText("");
        ediCurso.setText("");
        ediCiclo.setText("");
        ediNota.setText("");
    }



}
