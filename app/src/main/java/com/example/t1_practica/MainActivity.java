package com.example.t1_practica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.t1_practica.adaptadores.ListaNotasAdapter;
import com.example.t1_practica.db.DbNotas;
import com.example.t1_practica.db.dbHelper;
import com.example.t1_practica.entidades.Notas;

import java.util.ArrayList;

/**
 * Esta clase permitira visulizar todas las notas alamcenadas en la base de datos. Nos permitira tambien
 * añadir una nueva y pulsar en una nota para visualizarla.
 */
public class MainActivity extends AppCompatActivity {

   // Button crear; //Es el boton que nos redirigirá a la otra actividad
    BateriaLow bateria; //Obteto de tipo BateriaLow que usaremos para emitir un aviso de bateria baja

    //Button crearBD;

    RecyclerView listaNotas; //Nos permitira visulizar las notas
    ArrayList<Notas> arrayNotas; //Es el arrayList con las notas almacenadas en la BD.

    /**
     * Este metodo no permite iniciar la actidad.
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#c7bfc0"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000D")));

        /*
        crear = findViewById(R.id.boton); //Le damos valor al boton anteriormente creado
        crearBD = findViewById(R.id.bd_crear);

        crearBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper helper = new dbHelper(MainActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                if (db != null) {
                    Toast.makeText(MainActivity.this, "BD OK", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();

                }
            }



        });
         */

        arrayNotas = new ArrayList<>();


        listaNotas = findViewById(R.id.listaNotas);

        listaNotas.setLayoutManager(new LinearLayoutManager(this)); // Para que se apilen una debajo de la otra.

        DbNotas dbNotas = new DbNotas(MainActivity.this); //Objeto DbNotas que nos permitira usar sus mentodos. Como contexto se le pasa la clase en si.

        /**
         * Objeto de tipo listaNotasAdapter al que se le pasa el arrayList de notas obtenido del metodo
         * motrarNotas de DbNotas.
         */
        ListaNotasAdapter adapter = new ListaNotasAdapter(dbNotas.mostrarNotas());
        listaNotas.setAdapter(adapter); //Se estable el adapter para el RecyclerView




        bateria = new BateriaLow();
        //Si la bateria baja del 15% emite un aviso con Toast
        registerReceiver(bateria, new IntentFilter(Intent.ACTION_BATTERY_LOW));
    }


    /**
     * Este metodo mostrara las notas en la aplicacion.
     * @param menu objeto te tipo menu donde se almacenaran las notas.
     * @return devuelve true para indicar que se esta mostrando.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;

    }

    /**
     * Este metodo nos permite insertar una nota.
     * Al ejecutarlo nos llevara a la actividad de insertar nota.
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==R.id.menu_Nuevo) {
            Intent intent = new Intent(this, InsertarActivity.class);
            startActivity(intent);
            return true;

        } else {
            return super.onOptionsItemSelected(item);

        }

    }
}

