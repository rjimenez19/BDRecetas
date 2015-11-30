package com.example.rafa.bdrecetas.gestores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.bdrecetas.base_de_datos.Ayudante;
import com.example.rafa.bdrecetas.base_de_datos.Contrato;
import com.example.rafa.bdrecetas.recetas.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class GestorIngrediente {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorIngrediente(Context c) {
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

    public long insert(Ingrediente pl){
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBREINGREDIENTE, pl.getNombre());

        long id = bd.insert(Contrato.TablaIngrediente.TABLA,null,valores);
        return id;
    }

    public int delete(Ingrediente p){
        return delete(p.getId());
    }

    public int delete(long id){
        String condicion = Contrato.TablaIngrediente._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Ingrediente pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBREINGREDIENTE, pl.getNombre());

        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { pl.getId() + "" };

        int cuenta = bd.update(Contrato.TablaIngrediente.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Ingrediente> select(){
        return select(null, null);
    }

    public List<Ingrediente> select(String condicion, String[] params) {
        List<Ingrediente> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        Ingrediente ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public Ingrediente getRow(Cursor c) {
        Ingrediente pl = new Ingrediente();
        Log.v("aadsql",""+c.getColumnCount());
        pl.setId(c.getLong(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        pl.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBREINGREDIENTE)));

        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaIngrediente.TABLA, null, condicion, parametros, null, null, Contrato.TablaIngrediente.NOMBREINGREDIENTE+", "+Contrato.TablaRecetario._ID);
        return cursor;
    }
}
