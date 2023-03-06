package com.example.t1_practica.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t1_practica.R;
import com.example.t1_practica.VerActivity;
import com.example.t1_practica.entidades.Notas;

import java.util.ArrayList;


/**
 * Esta clase nos permitira visualizar las notas almacenadas en la base de datos.
 */
public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotasViewHolder> {
    ArrayList<Notas> listaNotas; //ArrayList con las notas almacenadas

    /**
     * Constructor por defecto que se inicializa con las notas almacenadas.
     * @param notas ArrayList con notas almacenadas que inicializara el arrayList de la clase.
     */
    public ListaNotasAdapter(ArrayList<Notas> notas){
        this.listaNotas=notas;

    }


    /**
     * Este metodo nos permite asignarle un diseño a la visualizacion de las notas.
     * @return el objeto view que contiene toda la informacion.
     */
    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_notas,null,false);
        return new NotasViewHolder(view);
    }

    /**
     * Este metodo nos permite asignarle a cada editText del xml de lista_item_notas su casilla
     * correspondiente de la tabla para visualizar la informacion.
     */
    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder holder, int position) {

        holder.nombre.setText(listaNotas.get(position).getNombre());
        holder.descripcion.setText(listaNotas.get(position).getDescripcion());
        holder.fecha.setText(listaNotas.get(position).getFecha());
        holder.contacto.setText(listaNotas.get(position).getContacto());

    }


    /**
     * Este metodo devuelve el numero de notas en la BD.
     * @return el numero de notas.
     */
    @Override
    public int getItemCount() {
        return listaNotas.size();
    }


    /**
     * Este metodo asigna a los TextView creados en el metodo para cada valor de la tabla el valor
     * de los elementos de lista_item_notas.xml.
     * Tambien podemos pulsar sobre la nota para visualizarla y realizar otras acciones.
     */
    public class NotasViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, fecha, contacto;
        public NotasViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreView);
            descripcion = itemView.findViewById(R.id.descripcionView);
            fecha = itemView.findViewById(R.id.fechaView);
            contacto = itemView.findViewById(R.id.contactoView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class); //Este intent lleva a otra actividad para visualizar la nota.
                    intent.putExtra("id", listaNotas.get(getAdapterPosition()).getId()); //Le pasamos el id de la nota
                    context.startActivity(intent);
                }
            });


        }
    }
}
