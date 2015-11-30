package com.example.rafa.bdrecetas;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.rafa.bdrecetas.gestores.GestorIngrediente;
import com.example.rafa.bdrecetas.gestores.GestorRecetas;
import com.example.rafa.bdrecetas.recetas.Ingrediente;
import com.example.rafa.bdrecetas.recetas.Recetario;

public class Aniadir extends AppCompatActivity {

    private String nombre, preparacion, a;

    private Recetario rec;
    private Ingrediente ing;
    private GestorRecetas gr;
    private GestorIngrediente gi;

    private EditText edNombre, edPreparacion;
    private Button btAñadir;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3;
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir);
        init();
    }

    @Override
    protected void onResume(){
        gr.open();
        gi.open();
        Cursor c = gr.getCursor();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        gi.close();
        Log.v("APLICACION", "Resume Principal Close");
        super.onPause();
    }


    public void init(){
        gr = new GestorRecetas(this);
        gi = new GestorIngrediente(this);

        btAñadir = (Button) findViewById(R.id.button2);
        edNombre = (EditText) findViewById(R.id.editText);
        edPreparacion = (EditText) findViewById(R.id.editText2);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb2 = (RadioButton) findViewById(R.id.radioButton3);
    }


    public void añadir2(View v){

        nombre = edNombre.getText().toString();
        preparacion = edPreparacion.getText().toString();
        String foto = "foto";
        String categ = "";

        if (rb1.isChecked()){
            a = "Carne";
        }else if (rb2.isChecked()){
            a = "Pescado";
        }else{
            a = "Verdura";
        }

//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.radioButton){
//                    categ = "Carne";
//                }else if (checkedId == R.id.radioButton2){
//                    categ = "Pescado";
//                }else if (checkedId == R.id.radioButton3){
//                    categ = "Verdura";
//                }
//            }
//        });

        rec = new Recetario(nombre, foto, preparacion, categ);
        gr.insert(rec);

        Intent i = new Intent(this,MainActivity.class);
        Bundle b = new Bundle();
        startActivity(i);
    }

    public void anadirIngredientes(View v) {
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Ingredientes");
        LayoutInflater inflater= LayoutInflater.from(this);

        final View vista = inflater.inflate(R.layout.ingredientes, null);

        alert.setPositiveButton(R.string.anadir2,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        et1 = (EditText) vista.findViewById(R.id.ingrediente1);
                        et2 = (EditText) vista.findViewById(R.id.ingrediente2);
                        et3 = (EditText) vista.findViewById(R.id.ingrediente3);
                        et4 = (EditText) vista.findViewById(R.id.ingrediente4);

                        a = et1.getText().toString() + ", " + et2.getText().toString() + ", " + et3.getText().toString() + ", " + et4.getText().toString();
                        ing = new Ingrediente(a);
                        gi.insert(ing);
                    }
                });
        alert.setView(vista);
        alert.setNegativeButton(R.string.cerrar, null);
        alert.show();
    }
}

