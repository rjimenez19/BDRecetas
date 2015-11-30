package com.example.rafa.bdrecetas.gestores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.bdrecetas.base_de_datos.Ayudante;
import com.example.rafa.bdrecetas.base_de_datos.Contrato;
import com.example.rafa.bdrecetas.recetas.Recetario;

import java.util.ArrayList;
import java.util.List;

public class GestorRecetas {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRecetas(Context c) {
        Log.v("SQLAAD", "Gestor de constructor de recetas");
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Recetario pl){
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaRecetario.NOMBRERECETA, pl.getNombre());
        valores.put(Contrato.TablaRecetario.FOTO, pl.getFoto());
        valores.put(Contrato.TablaRecetario.INSTRUCCIONES, pl.getInstrucciones());

        long id = bd.insert(Contrato.TablaRecetario.TABLA,null,valores);
        return id;
    }

    public int delete(Recetario p){
        return delete(p.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaRecetario._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaRecetario.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Recetario pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetario.NOMBRERECETA, pl.getNombre());
        valores.put(Contrato.TablaRecetario.FOTO, pl.getFoto());
        valores.put(Contrato.TablaRecetario.INSTRUCCIONES, pl.getInstrucciones());
        String condicion = Contrato.TablaRecetario._ID + " = ?";
        String[] argumentos = { pl.getId() + "" };

        int cuenta = bd.update(Contrato.TablaRecetario.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Recetario> select(){
        return select(null, null);
    }

    public List<Recetario> select(String condicion, String[] params) {
        List<Recetario> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        Recetario ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public Recetario getRow(Cursor c) {
        Recetario pl = new Recetario();
        Log.v("aadsql",""+c.getColumnCount());
        pl.setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetario._ID)));
        pl.setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetario.NOMBRERECETA)));
        pl.setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetario.FOTO)));
        pl.setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetario.INSTRUCCIONES)));
        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaRecetario.TABLA, null, condicion, parametros, null, null, Contrato.TablaRecetario.NOMBRERECETA + ", "+Contrato.TablaRecetario._ID);
        return cursor;
    }
}
