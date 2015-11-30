package com.example.rafa.bdrecetas.adaptador;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.rafa.bdrecetas.R;
import com.example.rafa.bdrecetas.gestores.GestorRecetas;
import com.example.rafa.bdrecetas.recetas.Recetario;

public class Adaptador extends CursorAdapter{

    private GestorRecetas gestor;
    private Context contexto;
    private Cursor cu;

    public Adaptador (Context co, Cursor cu, GestorRecetas gr) {
        super(co, cu, true);
        this.gestor = gr;
        this.contexto = co;
        this.cu = cu;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView) view.findViewById(R.id.textView);
        Recetario p = gestor.getRow(cursor);
        tv1.setText(p.getNombre());
    }

    public void borrar(){

    }

    public void detalles(){

        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Receta");
        LayoutInflater inflater = LayoutInflater.from(contexto);

        final View vista = inflater.inflate(R.layout.receta, null);
        


        alert.setView(vista);
        alert.setNegativeButton(R.string.cerrar, null);
        alert.show();
    }
}
