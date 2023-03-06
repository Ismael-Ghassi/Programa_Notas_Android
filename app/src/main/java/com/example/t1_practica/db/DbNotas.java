package com.example.t1_practica.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t1_practica.entidades.Notas;

import java.util.ArrayList;

/**
 * Esta clase nos permitira añadir y trabajar con las notas.
 */
public class DbNotas extends dbHelper{

    Context context; //Nos permitira trabajar con las notas

    /**
     * Constructor por parametros que nos permita darle valor al objeto context.
     * @param context es el objeto context.
     */
    public DbNotas(@Nullable Context context) {
        super(context);
        this.context=context;
    }


    /**
     * Este metodo nos permite insertar una nota en la base de datos.
     * @param nombre es el nombre
     * @param descripcion es la descripcion
     * @param fecha es la fecha
     * @param contacto es el contacto asociado a la nota
     * @return el id de la entrada
     */
    public long insertaEntrada(String nombre, String descripcion, String fecha, String contacto ){
        dbHelper helper = new dbHelper(context); //A este objeto se le pasa el context de la BD.
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues(); //Este objeto contendra la informacion de la nota
        valores.put("nombre",nombre);
        valores.put("descripcion",descripcion);
        valores.put("fecha",fecha);
        valores.put("contacto",contacto);

        long id = db.insert(TABLE_NOTAS, null, valores); //Se inserta la nota en la BD.

        return id;
    }


    /**
     * Este metodo nos permitira mostrar las notas almacenadas en la BD.
     * @return el arrayList con las notas
     */
    public ArrayList<Notas> mostrarNotas(){
        dbHelper helper = new dbHelper(context); //A este objeto se le pasa el context de la BD.
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Notas> listaNotas = new ArrayList<>();
        Notas nota = null;
        Cursor cursorNotas = null;


        cursorNotas = db.rawQuery("SELECT * FROM "+ TABLE_NOTAS,null); //Consulta que devuelve todas las notas.

        if (cursorNotas.moveToFirst()){
            /**
             * En este vucle se sacara la infromacion de cada nota de la BD
             * y se inicizlizara cada atributo de Nota para despues añadirla al arrayList
             */
            do {

                nota = new Notas();
                nota.setId(cursorNotas.getInt(0));
                nota.setNombre(cursorNotas.getString(1));
                nota.setDescripcion(cursorNotas.getString(2));
                nota.setFecha(cursorNotas.getString(3));
                nota.setContacto(cursorNotas.getString(4));

                listaNotas.add(nota);

            }while(cursorNotas.moveToNext()); //Nos permite recorrer la BD.
        }

        cursorNotas.close();
        return listaNotas;
    }


    /**
     * Este metodo nos permite mediante una consulta seleccionar una nota de la BD gracias a su id para mas
     * tarde poder visualizarla.
     * @param id es el id de la nota que buscamos
     * @return devuelve la nota que buscamos
     */
    public Notas verNota(int id){
        dbHelper helper = new dbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Notas nota = null;
        Cursor cursorNotas = null;


        //Consulta que devuelve la nota con el id pasado por parametros
        cursorNotas = db.rawQuery("SELECT * FROM "+ TABLE_NOTAS +" WHERE ID = "+ id + " LIMIT 1",null);

        if (cursorNotas.moveToFirst()){

            /**
             * Inicilizamos los atrbutos de la nota con la informacion sacada de la consulta
             */
                nota = new Notas();
                nota.setId(cursorNotas.getInt(0));
                nota.setNombre(cursorNotas.getString(1));
                nota.setDescripcion(cursorNotas.getString(2));
                nota.setFecha(cursorNotas.getString(3));
                nota.setContacto(cursorNotas.getString(4));

        }

        cursorNotas.close();
        return nota; //Se devuelve la nota.
    }


    /**
     * Este metodo nos permite modificar una nota almacenada en la BD.
     * @param id es el id de la nota que queremos modificar
     * @param nombre es el nuevo nombre
     * @param descripcion el la nueva descripcion
     * @param fecha es la nueva fecha
     * @param contacto es el nuevo contacto asociado.
     */
    public void modficarEntrada(int id, String nombre, String descripcion, String fecha, String contacto ){

        dbHelper helper = new dbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_NOTAS+" SET nombre = '"+nombre+"', descripcion = '"+descripcion+"', fecha = '"+fecha+"', contacto = '"+contacto+"' WHERE id = '"+id+"' ");

        db.close();
    }


    /**
     * Este metodo nos permite eleminar una nota de la BD haciendo uso de consultas.
     * @param id
     */
    public void eliminarEntrada(int id){

        dbHelper helper = new dbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NOTAS+" WHERE id = "+id);

        db.close();
    }
}
