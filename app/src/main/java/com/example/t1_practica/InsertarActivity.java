package com.example.t1_practica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t1_practica.db.DbNotas;


/**
 * Esta clase nos permite añadir una nota a la base de datos.
 */
public class InsertarActivity extends AppCompatActivity {

    /**
     * Estos son los atributos de la nota.
     */
    EditText nombre;
    EditText descripcion;
    EditText fecha;
    EditText contacto;


    Button guardar; //Boton que servira para guardar la nota


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        getWindow().setStatusBarColor(Color.parseColor("#c7bfc0"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000D")));


        /**
         * Se realiza la conexion a los EditText de activity_insertar.xml
         */
        nombre = findViewById(R.id.vNombre);
        descripcion = findViewById(R.id.vDescripcion);
        fecha = findViewById(R.id.vFecha);
        contacto = findViewById(R.id.vContacto);

        guardar = findViewById(R.id.vGuardar); // Se realiza la conexion con el boton de activity_insertar.xml


        /**
         * Si se pulsa el boton guardar se ejecutara el metodo de 'insertarEntrada' de DbNotas si se
         * cumplen las restricciones.
         * Cuando se pulse el boton se emitira un mensaje en funcion del resultado obtenido.
         */
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nombre.getText().toString().equals("") || fecha.getText().toString().equals("")) {
                    Toast.makeText(InsertarActivity.this, "El nombre o la fecha estan vacios", Toast.LENGTH_LONG).show();
                } else {

                    DbNotas dbNotas = new DbNotas(InsertarActivity.this); //Objeto DbNotas que nos permitira insertar una nota en la BD.

                    /**
                     * Se inserta la nota en la BD.
                     */
                    dbNotas.insertaEntrada(
                            nombre.getText().toString(),
                            descripcion.getText().toString(),
                            fecha.getText().toString(),
                            contacto.getText().toString()
                    );

                    Toast.makeText(InsertarActivity.this, "Nota guardada", Toast.LENGTH_LONG).show();


                    /**
                     * Cuando se introduce la nota se vacia los campos para facilitar una nuevo insercion.
                     */
                    nombre.setText("");
                    descripcion.setText("");
                    fecha.setText("");
                    contacto.setText("");


                }
            }
        });
    }
}