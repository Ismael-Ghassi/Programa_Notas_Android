package com.example.t1_practica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t1_practica.db.DbNotas;
import com.example.t1_practica.entidades.Notas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Esta clase nos permitira visulizar una nota seleccionada en la actividad principal y realizar diversas
 * acciones como, editar, eliminar, llamar al contacto asociado y mandarle un sms.
 */
public class VerActivity extends AppCompatActivity {


    EditText nombre, descripcion,fecha,contacto; //Edit text que referencian a los atributos de la nota.
    Button guardar; //Boton para guardar la nota que ocultaremos

    Notas nota; // Es la nota que esta visualizando
    int id=0; //Es el id de la nota

    FloatingActionButton editar,eliminar,llamar,sms; //Son los iconos que cuentan con la funcionalidad que su nombre indica.

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
        eliminar = findViewById(R.id.btn_eliminar);

        llamar = findViewById(R.id.btnllamar);
        sms = findViewById(R.id.btnsms);

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

        DbNotas dbNotas = new DbNotas(VerActivity.this); //Objeto DbNotas que nos permitira usar sus metodos.
        nota = dbNotas.verNota(id); //Asignamos a la variable nota el valor de la nota con el id pasado por parametros de la nota con la que se quiere trabajar.

        if(nota!=null){

            /**
             * Se asigna a los EditText de la clase los valores de cada variable de la nota.
             */
            nombre.setText(nota.getNombre());
            descripcion.setText(nota.getDescripcion());
            fecha.setText(nota.getFecha());
            contacto.setText(nota.getContacto());


            /**
             * Se oculta el boton de guardar y se desactiva la opcion de que los
             * campos puedan ser modificados
             */
            guardar.setVisibility(View.INVISIBLE);
            nombre.setInputType(InputType.TYPE_NULL);
            descripcion.setInputType(InputType.TYPE_NULL);
            fecha.setInputType(InputType.TYPE_NULL);
            contacto.setInputType(InputType.TYPE_NULL);
        }


        /**
         * Si se pulsa el icono de editar nos dirigiremos a la edicion de la nota.
         */
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        /**
         * Si se pulsa el icono de eliminar se procedera a la eliminacion de la nota pero antes saltara
         * un menasje para asegurarnos de la decision.
         */
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this); //Alerta para confirmar si se quiere eliminar la nota.
                builder.setMessage("¿Quieres eliminar la nota?").setPositiveButton("SI", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /**
                         * Si decidimos eliminar la nota procederemos a llamar al metodo eliminarEntrada
                         * de la clase DbNotas y le pasaremos el id de la nota.
                         * Al eliminar la nota el programa nos devolvera a la actividad principal donde poderemos notar
                         * que la nota eliminada ya no se encuentra.
                         */
                        dbNotas.eliminarEntrada(id);
                        Toast.makeText(VerActivity.this,"Nota eliminada",Toast.LENGTH_LONG).show(); //Mensaje de nota eliminada
                        Intent principal = new Intent(VerActivity.this, MainActivity.class);
                        startActivity(principal);

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Si decidimos retractarnos simplemente se cerrara el cuadro de dialogo.
                    }
                }).show();
            }
        });


        /**
         * Si se pulsa el icono de llamar se nos dirigira a la aplicacion de llamada del telefono
         * donde podremos llamar al contacto asiciado a la nota sin necesidad de buscarlo ya que
         * se lo pasamos al intent.
         */
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent llamada = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contacto.getText().toString()));
                startActivity(llamada);
            }
        });



        /**
         * Si se pulsa el icono de sms se nos dirigira a la aplicacion de sms del telefono
         * donde podremos mandar un mensaje al contacto asiciado a la nota sin necesidad de
         * buscarlo ya que se lo pasamos al intent.
         */
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mensaje = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+contacto.getText().toString()));
                startActivity(mensaje);
            }
        });
    }
}