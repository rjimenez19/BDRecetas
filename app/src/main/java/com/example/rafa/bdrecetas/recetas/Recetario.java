package com.example.rafa.bdrecetas.recetas;


import android.database.Cursor;

import com.example.rafa.bdrecetas.base_de_datos.Contrato;

public class Recetario {

    private long idReceta;
    private String nombreReceta;
    private String foto;
    private String instrucciones;
    private String categoria;

    public Recetario() {
        this(0,"","","","");
    }

    public Recetario(long id, String nombre, String foto, String instrucciones, String categoria) {
        this.idReceta = id;
        this.nombreReceta = nombre;
        this.foto = foto;
        this.instrucciones = instrucciones;
        this.categoria = categoria;
    }

    public Recetario(String nombre, String foto, String instrucciones, String categoria) {
        this.nombreReceta = nombre;
        this.foto = foto;
        this.instrucciones = instrucciones;
        this.categoria = categoria;
    }

    public long getId() {
        return idReceta;
    }

    public void setId(long id) {
        this.idReceta = id;
    }

    public String getNombre() {
        return nombreReceta;
    }

    public void setNombre(String nombre) {
        this.nombreReceta = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.categoria = idcategoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void set(Cursor c){
        setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetario._ID)));
        setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetario.NOMBRERECETA)));
        setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetario.FOTO)));
        setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetario.INSTRUCCIONES)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recetario recetario = (Recetario) o;

        if (idReceta != recetario.idReceta) return false;
        return !(nombreReceta != null ? !nombreReceta.equals(recetario.nombreReceta) : recetario.nombreReceta != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (idReceta ^ (idReceta >>> 32));
        result = 31 * result + (nombreReceta != null ? nombreReceta.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recetario{" +
                "id=" + idReceta +
                ", nombre='" + nombreReceta + '\'' +
                ", foto='" + foto + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
