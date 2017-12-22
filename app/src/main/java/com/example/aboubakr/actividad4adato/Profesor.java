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

import static com.example.aboubakr.actividad4adato.R.id.ediCurso;
import static com.example.aboubakr.actividad4adato.R.id.ediEdad;

public class Profesor extends AppCompatActivity {

    Button guardar,consultar, borrar;
    Button atras;
    EditText ediNomPro,ediEdaPro,ediCilPro,ediCursPro,ediDesPro,ediBorrrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        final MyDADatos mydato = new MyDADatos(getApplicationContext());

        guardar = (Button)findViewById(R.id.btnGuardarPro);
        atras = (Button)findViewById(R.id.btnAtrasPro);
        ediNomPro = (EditText)findViewById(R.id.ediNomPro);
        ediEdaPro = (EditText)findViewById(R.id.ediEdaPro);
        ediCilPro = (EditText)findViewById(R.id.ediCilPro);
        ediCursPro = (EditText)findViewById(R.id.ediCursPro);
        ediDesPro = (EditText)findViewById(R.id.ediDesPro);
        consultar = (Button)findViewById(R.id.btnConsultar);
        borrar = (Button)findViewById(R.id.btnBorrar);
        ediBorrrado=(EditText)findViewById(R.id.ediBorrrado);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mydato.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(MyDADatos.CAMPO_NOMBREP,ediNomPro.getText().toString());
                valores.put(MyDADatos.CAMPO_EDADP,ediEdaPro.getText().toString());
                valores.put(MyDADatos.CAMPO_CICLOP,ediCilPro.getText().toString());
                valores.put(MyDADatos.CAMPO_CURSOP,ediCursPro.getText().toString());
                valores.put(MyDADatos.CAMPO_DESPACHO,ediDesPro.getText().toString());

                Long idpGuardado = db.insert(MyDADatos.NOMBRE_TABLA1, "Paco",valores);
                Toast.makeText(getApplicationContext(),"se guardo : "+ idpGuardado,Toast.LENGTH_LONG).show();


            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profesor.this,MainActivity.class);
                startActivity(i);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profesor.this,Consultas.class);
                startActivity(i);
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mydato.getWritableDatabase();
                String[] parametros = {ediBorrrado.getText().toString()};

                db.delete(MyDADatos.NOMBRE_TABLA1,MyDADatos.CAMPO_NOMBREP+"=?",parametros);
                Toast.makeText(getApplicationContext(),"se borro correctamente ",Toast.LENGTH_LONG).show();
                limpiar();
                db.close();
            }
        });
    }
    private void limpiar(){
        ediNomPro.setText("");
        ediEdaPro.setText("");
        ediCursPro.setText("");
        ediCilPro.setText("");
        ediDesPro.setText("");
    }


}
