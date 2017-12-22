package com.example.aboubakr.actividad4adato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by aboubakr on 27/11/17.
 */

public class MyDADatos extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "MiBasedeDatos.db";



    public static final String NOMBRE_TABLA = "alumnado";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_EDAD = "edad";
    public static final String CAMPO_CICLO = "ciclo";
    public static final String CAMPO_CURSO = "curso";
    public static final String CAMPO_NOTAMEDIA = "notaMedia";

    public static final String NOMBRE_TABLA1 = "profesor";
    public static final String CAMPO_NOMBREP = "nombre";
    public static final String CAMPO_EDADP = "edad";
    public static final String CAMPO_CICLOP = "ciclo";
    public static final String CAMPO_CURSOP = "curso";
    public static final String CAMPO_DESPACHO = "despacho";



    public static final String CREATE_TABLA_USUARIO = "CREATE TABLE " + NOMBRE_TABLA+ " " +
            "(" + CAMPO_NOMBRE + " TEXT PRIMARY KEY, " + CAMPO_EDAD + " INTEGER, " + CAMPO_CICLO + " TEXT, " + CAMPO_CURSO + " TEXT, " + CAMPO_NOTAMEDIA + " INTEGER);";


    public static final String CREATE_TABLA_PROFESOR = "CREATE TABLE " + NOMBRE_TABLA1+ " " +
            "(" + CAMPO_NOMBREP + " TEXT PRIMARY KEY, " + CAMPO_EDADP + " INTEGER, " + CAMPO_CICLOP + " TEXT, " + CAMPO_CURSOP + " TEXT, " + CAMPO_DESPACHO + " TEXT);";


    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + NOMBRE_TABLA;
    private static final String SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS" + NOMBRE_TABLA1;


    public MyDADatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLA_USUARIO);
        db.execSQL(CREATE_TABLA_PROFESOR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES1);
        onCreate(db);

    }

}
