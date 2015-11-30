package com.example.rafa.bdrecetas.recetas;


import android.database.Cursor;

import com.example.rafa.bdrecetas.base_de_datos.Contrato;

public class Ingrediente {

    private long idIngrediente;
    private String nombreIngrediente;

    public Ingrediente() {
    }

    public Ingrediente(long idIngrediente, String nombreIngrediente) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
    }

    public Ingrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public long getId() {
        return idIngrediente;
    }

    public void setId(long id) {
        this.idIngrediente = id;
    }

    public String getNombre() {
        return nombreIngrediente;
    }

    public void setNombre(String nombre) {
        this.nombreIngrediente = nombre;
    }

    public void set(Cursor c){
        setId(c.getLong(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBREINGREDIENTE)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingrediente that = (Ingrediente) o;

        if (idIngrediente != that.idIngrediente) return false;
        return !(nombreIngrediente != null ? !nombreIngrediente.equals(that.nombreIngrediente) : that.nombreIngrediente != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (idIngrediente ^ (idIngrediente >>> 32));
        result = 31 * result + (nombreIngrediente != null ? nombreIngrediente.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + idIngrediente +
                ", nombre='" + nombreIngrediente + '\'' +
                '}';
    }
}
