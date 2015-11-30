package com.example.rafa.bdrecetas.base_de_datos;


import android.provider.BaseColumns;

public class Contrato {

    private Contrato() {
    }

    public static abstract class TablaRecetario implements BaseColumns {
        public static final String TABLA = "recetario";
        public static final String NOMBRERECETA = "nombreReceta";
        public static final String FOTO = "foto";
        public static final String INSTRUCCIONES = "instrucciones";
        public static final String CATEGORIA = "categoria";
    }

    public static abstract class TablaIngrediente implements BaseColumns {
        public static final String TABLA = "ingredientes";
        public static final String NOMBREINGREDIENTE = "nombreIngrediente";
    }

    public static abstract class TablaRecetaIngrediente implements BaseColumns {
        public static final String TABLA = "recetaIngrediente";
        public static final String CANTIDAD = "cantidad";
        public static final String ID_RECETA = "id_receta";
        public static final String ID_INGREDIENTE = "id_ingrediente";
    }
}
