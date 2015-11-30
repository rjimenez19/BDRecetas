package com.example.rafa.bdrecetas.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.bdrecetas.base_de_datos.Ayudante;
import com.example.rafa.bdrecetas.base_de_datos.Contrato;
import com.example.rafa.bdrecetas.recetas.RecetaIngrediente;

import java.util.ArrayList;
import java.util.List;

public class GestorRecetaIngrediente {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRecetaIngrediente(Context c) {
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

    public long insert(RecetaIngrediente pl){
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaRecetaIngrediente.CANTIDAD, pl.getCantidad());

        long id = bd.insert(Contrato.TablaRecetaIngrediente.TABLA,null,valores);
        return id;
    }

    public int delete(RecetaIngrediente p){
        return delete(p.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaRecetario._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaRecetario.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(RecetaIngrediente pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetaIngrediente.CANTIDAD, pl.getCantidad());

        String condicion = Contrato.TablaRecetario._ID + " = ?";
        String[] argumentos = { pl.getId() + "" };

        int cuenta = bd.update(Contrato.TablaRecetaIngrediente.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<RecetaIngrediente> select(){
        return select(null, null);
    }

    public List<RecetaIngrediente> select(String condicion, String[] params) {
        List<RecetaIngrediente> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        RecetaIngrediente ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public RecetaIngrediente getRow(Cursor c) {
        RecetaIngrediente pl = new RecetaIngrediente();
        Log.v("aadsql",""+c.getColumnCount());
        pl.setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetaIngrediente._ID)));
        pl.setCantidad(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD)));
        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaRecetaIngrediente.TABLA, null, condicion, parametros, null, null, Contrato.TablaRecetaIngrediente.CANTIDAD+", "+Contrato.TablaRecetario._ID);
        return cursor;
    }
}
