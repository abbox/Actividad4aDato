package com.example.aboubakr.actividad4adato;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Consultas extends AppCompatActivity {

    TextView estado;
    Spinner SpinnerSelecion;
    Button atras;
    ListView listViewEstudiCurso;


    //ListView listViewEstudiCurso;
    ArrayList<String> listaInformacion;
    ArrayList<alumnados> listaUsuarios;
    ArrayList<profesores> listaProfesores;

    MyDADatos mydato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        estado=(TextView)findViewById(R.id.etiSelecion);
        SpinnerSelecion = (Spinner)findViewById(R.id.idSpinnerSelecion);
        atras=(Button)findViewById(R.id.buttonatras);
        listViewEstudiCurso=(ListView)findViewById(R.id.listViewEstudiCurso);


        mydato = new MyDADatos(getApplicationContext());


        ArrayList<String> comboConsultas = new ArrayList<String>();
        comboConsultas.add("Seleccione");
        comboConsultas.add("Estudiantes por ciclo");
        comboConsultas.add("Estudiantes por curso");
        comboConsultas.add("Estudiantes por ciclo y curso");
        comboConsultas.add("Todos los estudiantes");
        comboConsultas.add("Profesores por ciclo");
        comboConsultas.add("Profesores por curso");
        comboConsultas.add("Profesores por ciclo y curso");
        comboConsultas.add("Todos los profesores");
        comboConsultas.add("Todos los profesores y alumnos");

        ArrayAdapter<CharSequence> adapterSpinner;

        adapterSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item,comboConsultas);

        SpinnerSelecion.setAdapter(adapterSpinner);

        SpinnerSelecion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if(position!=0) {


                    ArrayAdapter<CharSequence> adapterListView;
                    if (position == 1) {
                        Log.d("Manel", "has polsat l'opció 1");
                        listaInformacion = estudiantePorCiclo();

                    }
                    if (position == 2) {
                        listaInformacion = estudiantePorCurso();
                        //listaInformacion=estudiantePorCurso();
                        // listViewEstudiCurso.setAdapter(adapter);

                    }
                    if (position == 3) {
                        listaInformacion=estudiantePorCursoCiclo();
                        //listaInformacion=estudiantePorCursoYCiclo();
                        //listViewEstudiCurso.setAdapter(adapter);
                    }
                    if (position == 4){
                        listaInformacion =todoEstudiantes();
                    }
                    if (position == 5){
                        listaInformacion = profesoresCiclo();
                    }
                    if (position == 6){
                        listaInformacion = profesoresCurso();
                    }
                    if (position == 7){
                        listaInformacion = profesoresCursoCiclo();
                    }
                    if (position == 8){
                        listaInformacion = todosProfesores();
                    }
                    if (position == 9){
                        //listaInformacion =todoEstudiantes();
                        //listaInformacion= todosProfesores();
                        listaInformacion =todosProfesoresAlumnos();
                    }
                    //Log.d("Manel", "has polsat l'opció " + position);
                    adapterListView = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaInformacion);
                    listViewEstudiCurso.setAdapter(adapterListView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Consultas.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
    //consulta de nombre estudiante por ciclo
    private ArrayList<String> estudiantePorCiclo() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        alumnados alum = null;
        listaUsuarios = new ArrayList<alumnados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA,null);
       // Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA+ " where " +MyDADatos.CAMPO_CICLOP+" = 'DAW' AND "+MyDADatos.CAMPO_CURSO+" = '2';",null);

        while (cursor.moveToNext()){
            alum = new alumnados(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaUsuarios.add(alum);
        }
        return obteberLista(listaUsuarios);
    }
    private ArrayList<String> obteberLista(ArrayList<alumnados> listaAlumnos) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaUsuarios.size();i++){

            al.add(listaAlumnos.get(i).getNombre());
            al.add(listaAlumnos.get(i).getCiclo());
        }
        //consulta de  nombre estudiante por curso
        return al;

        //Estudiantes por curso
    }
    private ArrayList<String> estudiantePorCurso() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        alumnados alum = null;
        listaUsuarios = new ArrayList<alumnados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA,null);

        while (cursor.moveToNext()){
            alum = new alumnados(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaUsuarios.add(alum);
        }
        return obteberListacurso(listaUsuarios);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberListacurso(ArrayList<alumnados> listaAlumnos) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaUsuarios.size();i++){
            al.add(listaAlumnos.get(i).getNombre());
            al.add(listaAlumnos.get(i).getCurso());
        }
        return al;
    }

    // consulta estudiante por curso y ciclo
    private ArrayList<String> estudiantePorCursoCiclo() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        alumnados alum = null;
        listaUsuarios = new ArrayList<alumnados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA,null);
        //Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA+ " where " +MyDADatos.CAMPO_CICLOP+" = 'DAW' AND "+MyDADatos.CAMPO_CURSO+" = '2';",null);

        while (cursor.moveToNext()){
            alum = new alumnados(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaUsuarios.add(alum);
        }
        return obteberListacursoCiclo(listaUsuarios);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberListacursoCiclo(ArrayList<alumnados> listaAlumnos) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaUsuarios.size();i++){
            al.add(listaAlumnos.get(i).getNombre());
            al.add(listaAlumnos.get(i).getCurso());
            al.add(listaAlumnos.get(i).getCiclo());
        }
        //consulta de  nombre estudiante por curso
        return al;
    }
    // consulta todos los estudiantes
    private ArrayList<String> todoEstudiantes() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        alumnados alum = null;
        listaUsuarios = new ArrayList<alumnados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA,null);

        while (cursor.moveToNext()){
            alum = new alumnados(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaUsuarios.add(alum);
        }
        return obtebertodoEstudiantes(listaUsuarios);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obtebertodoEstudiantes(ArrayList<alumnados> listaAlumnos) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaUsuarios.size();i++){
            al.add(listaAlumnos.get(i).getNombre());
        }
        //consulta de  nombre estudiante por curso
        return al;
    }

    //consulta tabla profesores
    private ArrayList<String> profesoresCiclo() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        profesores pro = null;
        listaProfesores = new ArrayList<profesores>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA1,null);

        while (cursor.moveToNext()){
            pro = new profesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaProfesores.add(pro);
        }
        return obteberProfesorCiclo(listaProfesores);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberProfesorCiclo(ArrayList<profesores> listaProfesores) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaProfesores.size();i++){
            al.add(listaProfesores.get(i).getNombre());
            al.add(listaProfesores.get(i).getCiclo());

        }
        return al;
    }

    //consulta profesores por curso
    private ArrayList<String> profesoresCurso() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        profesores pro = null;
        listaProfesores = new ArrayList<profesores>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA1,null);

        while (cursor.moveToNext()){
            pro = new profesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaProfesores.add(pro);
        }
        return obteberProfesorCurso(listaProfesores);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberProfesorCurso(ArrayList<profesores> listaProfesores) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaProfesores.size();i++){
            al.add(listaProfesores.get(i).getNombre());
            al.add(listaProfesores.get(i).getCurso());
        }
        return al;
    }

    //consulta profesores por curso y ciclo
    private ArrayList<String> profesoresCursoCiclo() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        profesores pro = null;
        listaProfesores = new ArrayList<profesores>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA1,null);

        while (cursor.moveToNext()){
            pro = new profesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaProfesores.add(pro);
        }
        return obteberProfesorCursoCiclo(listaProfesores);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberProfesorCursoCiclo(ArrayList<profesores> listaProfesores) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaProfesores.size();i++){
            al.add(listaProfesores.get(i).getNombre());
            al.add(listaProfesores.get(i).getCurso());
            al.add(listaProfesores.get(i).getCiclo());

        }
        return al;
    }

    //consulta tabla todos los profesores
    private ArrayList<String> todosProfesores() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        profesores pro = null;
        listaProfesores = new ArrayList<profesores>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA1,null);

        while (cursor.moveToNext()){
            pro = new profesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaProfesores.add(pro);
        }
        return obteberTodoProfesores(listaProfesores);
    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberTodoProfesores(ArrayList<profesores> listaProfesores) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaProfesores.size();i++){
            al.add(listaProfesores.get(i).getNombre());


        }
        return al;
    }
    //todos los profesores y alumnos
    private ArrayList<String> todosProfesoresAlumnos() {
        SQLiteDatabase db=mydato.getReadableDatabase();

        profesores pro = null;
        //alumnados alu =null;
        listaProfesores = new ArrayList<profesores>();
        //listaUsuarios = new ArrayList<alumnados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA1,null);
        //Cursor curso = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA,null);

        ////Cursor cursor = db.rawQuery("SELECT * FROM "+MyDADatos.NOMBRE_TABLA+ " where " +MyDADatos.CAMPO_CICLOP+" = 'DAW' AND "+MyDADatos.CAMPO_CURSO+" = '2';",null);

        while (cursor.moveToNext()){
            pro = new profesores(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

            listaProfesores.add(pro);
        }
        /*while (cursor.moveToNext()){
            alu = new alumnados(curso.getString(0),curso.getString(1),curso.getString(2),curso.getString(3),curso.getString(4));

            listaUsuarios.add(alu);
        }*/
        return obteberTodoProfesoresAlumnos(listaProfesores);

    }
    //obtenemos la informacion y la guardamos en nuestra lista
    private ArrayList<String> obteberTodoProfesoresAlumnos(ArrayList<profesores> listaProfesores) {
        ArrayList <String> al = new ArrayList<String>();

        for(int i=0;i<listaProfesores.size();i++){
            al.add(listaProfesores.get(i).getNombre());
        }
        return al;
    }



}
