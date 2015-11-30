package com.example.rafa.bdrecetas.recetas;


import android.database.Cursor;

import com.example.rafa.bdrecetas.base_de_datos.Contrato;

public class RecetaIngrediente {

    private long id;
    private long idReceta;
    private long idIngrediente;
    private int cantidad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void set(Cursor c){
        setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetario._ID)));
        setCantidad(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecetaIngrediente that = (RecetaIngrediente) o;

        if (id != that.id) return false;
        if (idReceta != that.idReceta) return false;
        if (idIngrediente != that.idIngrediente) return false;
        return cantidad == that.cantidad;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (idReceta ^ (idReceta >>> 32));
        result = 31 * result + (int) (idIngrediente ^ (idIngrediente >>> 32));
        result = 31 * result + cantidad;
        return result;
    }

    @Override
    public String toString() {
        return "RecetaIngrediente{" +
                "id=" + id +
                ", idReceta=" + idReceta +
                ", idIngrediente=" + idIngrediente +
                ", cantidad=" + cantidad +
                '}';
    }
}
