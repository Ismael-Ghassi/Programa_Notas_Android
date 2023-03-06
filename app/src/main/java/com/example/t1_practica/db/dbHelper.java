package com.example.t1_practica.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Esta clase nos permite crear y actualizar la base de datos.
 */
public class dbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; //Es la version de la BD.
    private static final String DATABASE_NOMBRE = "notas.db"; //Es el nombre de la base de datos
    public static final String TABLE_NOTAS = "t_notas"; //Es el nombre de la tabla donde se almacenaran las notas.


    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    /**
     * Este metodo crea la tabla en la base de datos.
     * @param sqLiteDatabase nos permite realizar la consulta en la base de datos.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_NOTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "descripcion TEXT,"+
                "fecha TEXT NOT NULL,"+
                "contacto TEXT )");

    }

    /**
     * Nos permite borrar la tabla de la BD.
     * @param sqLiteDatabase nos permite realizar la consulta en la base de datos.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_NOTAS);
        onCreate(sqLiteDatabase);
    }
}
