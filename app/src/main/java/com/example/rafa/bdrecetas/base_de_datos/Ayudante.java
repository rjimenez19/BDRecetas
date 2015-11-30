package com.example.rafa.bdrecetas.base_de_datos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "receta.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("SQLAAD", "Ayudante del Gestor de recetas");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1, sql2, sql3;

        //Recetario
        sql1="create table "+Contrato.TablaRecetario.TABLA
                + " ("+Contrato.TablaRecetario._ID+" integer primary key autoincrement, "
                + Contrato.TablaRecetario.NOMBRERECETA+" text, "
                + Contrato.TablaRecetario.FOTO+" text, "
                + Contrato.TablaRecetario.INSTRUCCIONES+" text,"
                + Contrato.TablaRecetario.CATEGORIA+" text)";
        Log.v("SQL1AAD", sql1);
        db.execSQL(sql1);

        //Ingredientes
        sql2="create table "+Contrato.TablaIngrediente.TABLA
                + " ("+Contrato.TablaIngrediente._ID+" integer primary key autoincrement, "
                + Contrato.TablaIngrediente.NOMBREINGREDIENTE+" text)";
        Log.v("SQL2AAD", sql2);
        db.execSQL(sql2);

        //RecetaIngrediente
        sql3="create table "+Contrato.TablaRecetaIngrediente.TABLA
                + " ("+Contrato.TablaRecetaIngrediente._ID+" integer primary key autoincrement, "
                + Contrato.TablaRecetaIngrediente.CANTIDAD+" text,"
                + Contrato.TablaRecetaIngrediente.ID_RECETA+" integer,"
                + Contrato.TablaRecetaIngrediente.ID_INGREDIENTE+" integer)";
        Log.v("SQL3AAD", sql3);
        db.execSQL(sql3);

        Log.v("SQLAAD", "onCreate Ayudante");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        String sqlB1,sqlB2,sqlB3;

        sqlB1 = "DROP TABLE IF EXISTS " + Contrato.TablaRecetario.TABLA ;
        sqlB2 = "DROP TABLE IF EXISTS " + Contrato.TablaIngrediente.TABLA;
        sqlB3 = "DROP TABLE IF EXISTS " + Contrato.TablaRecetaIngrediente.TABLA;

        db.execSQL(sqlB1);
        db.execSQL(sqlB2);
        db.execSQL(sqlB3);

        Log.v("SQLAAD", "onUpgrade");
        onCreate(db);
    }
}
