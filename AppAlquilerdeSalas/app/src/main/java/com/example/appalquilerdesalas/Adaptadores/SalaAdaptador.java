package com.example.appalquilerdesalas.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appalquilerdesalas.R;
import com.example.appalquilerdesalas.Modelo.Sala;
import com.example.appalquilerdesalas.FragmentsAdmin.ActualizarSalaFragment;

import java.util.ArrayList;

public class SalaAdaptador extends RecyclerView.Adapter<SalaAdaptador.ViewHolder>{

    public ArrayList<Sala> lSala = new ArrayList<Sala>();
    public ArrayList<Sala> lSalaOriginal = new ArrayList<Sala>();
    public Context context;

    public SalaAdaptador(ArrayList<Sala> lSala, Context context) {
        this.lSala = lSala;
        this.context = context;
        lSalaOriginal = new  ArrayList<Sala>();
        lSalaOriginal.addAll(lSala);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plantilla_carta
                        , parent,false);



        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Sala sala = lSala.get(i);
        holder.txtNombreA.setText(lSala.get(i).getNombre());
        holder.txtPrecioA.setText(lSala.get(i).getPrecio().toString());

        byte[] imagenBytes = lSala.get(i).getImagen();
        if (imagenBytes != null && imagenBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            holder.imgFotoA.setImageBitmap(bitmap);
            holder.imgFotoA.setVisibility(View.VISIBLE); // Mostrar el ImageView si hay imagen
        } else {
            holder.imgFotoA.setVisibility(View.GONE); // Ocultar el ImageView si no hay imagen
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                ActualizarSalaFragment updatesala = new ActualizarSalaFragment();
                bundle.putInt("id", sala.getId());
                bundle.putString("nombre", sala.getNombre());
                bundle.putString("descripcion", sala.getDescripcion());
                bundle.putInt("precio", sala.getPrecio());
                bundle.putByteArray("imagen", sala.getImagen());
                updatesala.setArguments(bundle);
                Navigation.findNavController(view).navigate(R.id.actualizarSalaFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lSala.size();
    }

    public  static class ViewHolder
            extends RecyclerView.ViewHolder{
        private TextView txtNombreA;
        private TextView txtPrecioA;
        private ImageView imgFotoA;

        private ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreA=itemView.findViewById(R.id.txt_nombreP);
            txtPrecioA=itemView.findViewById(R.id.txt_precioP);
            imgFotoA=itemView.findViewById(R.id.imagen_salaP);
            mainLayout=itemView.findViewById(R.id.main_layout);

        }
    }
}
