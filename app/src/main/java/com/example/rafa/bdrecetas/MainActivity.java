package com.example.rafa.bdrecetas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafa.bdrecetas.adaptador.Adaptador;
import com.example.rafa.bdrecetas.gestores.GestorIngrediente;
import com.example.rafa.bdrecetas.gestores.GestorRecetas;
import com.example.rafa.bdrecetas.recetas.Ingrediente;
import com.example.rafa.bdrecetas.recetas.Recetario;
import com.example.rafa.bdrecetas.util.Dialogo;
import com.example.rafa.bdrecetas.util.OnDialogoListener;

public class MainActivity extends AppCompatActivity {

    private GestorRecetas gr;
    private GestorIngrediente gi;

    private Adaptador adp;
    private ListView lv;
    private Cursor c, ci;
    private Button btAnadir;

    private EditText tv1,tv2,tv3,tv4;


    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gr = new GestorRecetas(this);
        gi = new GestorIngrediente(this);
        init();
    }

    @Override
    protected void onResume(){
        gr.open();
        c = gr.getCursor();
        adp = new Adaptador(this, c, gr);
        lv.setAdapter(adp);
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }

    public void init(){

        lv = (ListView) findViewById(R.id.listView);
        btAnadir = (Button) findViewById(R.id.button);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Recetario r = new Recetario();
                final Ingrediente i = new Ingrediente();

                r.set(c);

                final OnDialogoListener odl = new OnDialogoListener() {
                    @Override
                    public void onPreShow(View v) {

                        tv1 = (EditText) v.findViewById(R.id.nombreReceta);
                        tv2 = (EditText) v.findViewById(R.id.ingredienteReceta);
                        tv3 = (EditText) v.findViewById(R.id.preparacionReceta);
                        tv4 = (EditText) v.findViewById(R.id.categoriaReceta);

                        tv1.setText(r.getNombre());
                        tv2.setText(i.getNombre());
                        tv3.setText(r.getInstrucciones());
                        tv4.setText(r.getCategoria());
                    }

                    @Override
                    public void onOkSelecter(View v) {
                        r.setNombre(tv1.getText().toString());
                        i.setNombre(tv2.getText().toString());
                        r.setInstrucciones(tv3.getText().toString());
                        r.setCategoria(tv4.getText().toString());

                        gr.update(r);

                        c = gr.getCursor();
                        adp.changeCursor(c);
                    }

                    @Override
                    public void onCancelSelecter(View v) {
                        tostada("Cancelado");
                    }
                };
                tostada("1");
                Dialogo d = new Dialogo(MainActivity.this, R.layout.receta, odl);
                d.show();
            }
        });
    }

    public void añadir(View v){
        Intent i = new Intent(this,Aniadir.class);
        Bundle b = new Bundle();
        startActivity(i);
    }

    private void tostada(String i){
        Toast.makeText(this, i, Toast.LENGTH_LONG).show();
    }
}
