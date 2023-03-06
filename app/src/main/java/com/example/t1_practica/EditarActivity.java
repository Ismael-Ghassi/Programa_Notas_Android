package com.example.t1_practica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_practica.db.DbNotas;
import com.example.t1_practica.entidades.Notas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Esta clase nos permite editar una actividad
 */
public class EditarActivity extends AppCompatActivity {

    EditText nombre, descripcion,fecha,contacto; //Son los elementos a editar
    Button guardar; //Es el boton para guardar

    Notas nota; //Es la nota que se rellenara y sustituira a la antigua nota.
    int id=0; //Es el id de la nota que se va a editar

    FloatingActionButton editar, eliminar,llamar,sms; //Son los botones flotantes que se ocultaran a la hora de modificar



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        getWindow().setStatusBarColor(Color.parseColor("#c7bfc0"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000D")));

        /**
         * Le asignamos a cada variable un elemento su se encuentra en activity_ver.xml
         */
        nombre = findViewById(R.id.vNombre);
        descripcion = findViewById(R.id.vDescripcion);
        fecha = findViewById(R.id.vFecha);
        contacto = findViewById(R.id.vContacto);
        guardar = findViewById(R.id.vGuardar);



        editar = findViewById(R.id.btn_editar);
        editar.setVisibility(View.INVISIBLE);
        eliminar = findViewById(R.id.btn_eliminar);
        eliminar.setVisibility(View.INVISIBLE);

        llamar = findViewById(R.id.btnllamar);
        llamar.setVisibility(View.INVISIBLE);
        sms = findViewById(R.id.btnsms);
        sms.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras(); //Obtiene los datos de la actividad que se quiere editar
            if(extras==null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("id"); //Asignamos el id de la nota a la variable id.
            }
        }else{
            id = (int) savedInstanceState.getSerializable("id");
        }


        DbNotas dbNotas = new DbNotas(EditarActivity.this); //Objeto DbNotas que nos permitira usar sus metodos.
        nota = dbNotas.verNota(id); //Asignamos a la variable nota el valor de la nota con el id pasado por parametros de la nota que se quiere editar.

        if(nota!=null){

            /**
             * Se asigna a los EditText de la clase los valores de cada variable de la nota.
             */
            nombre.setText(nota.getNombre());
            descripcion.setText(nota.getDescripcion());
            fecha.setText(nota.getFecha());
            contacto.setText(nota.getContacto());
        }


        /**
         * Al pulsar el boton guardar, si se cumplen las restricciones, se ejecutara el metodo
         * modificarEntrada de la clase DbNotas y se modificara en la BD la nota.
         * Al modificarse la nota se volvera a la actividad principal donde se podra visualizar todas
         * las notas.
         * Este metodo cuenta con mensaje para avisar al usuario.
         */
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().equals("") || fecha.getText().toString().equals("")){
                    Toast.makeText(EditarActivity.this,"El nombre o la fecha estan vacios",Toast.LENGTH_LONG).show();
                }else{
                    dbNotas.modficarEntrada(id,nombre.getText().toString(),descripcion.getText().toString(),fecha.getText().toString(),contacto.getText().toString());
                    Toast.makeText(EditarActivity.this,"Modificada correctamente",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(EditarActivity.this, VerActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });



    }
}
