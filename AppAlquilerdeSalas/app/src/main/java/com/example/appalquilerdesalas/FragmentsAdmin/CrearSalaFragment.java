package com.example.appalquilerdesalas.FragmentsAdmin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appalquilerdesalas.DataBase.DatabaseHelper;
import com.example.appalquilerdesalas.DataBase.DbSala;
import com.example.appalquilerdesalas.Modelo.Sala;
import com.example.appalquilerdesalas.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearSalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearSalaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CrearSalaFragment() {
        // Required empty public constructor
    }

    private static final int REQUEST_CODE_PICK_IMAGE = 1;

    private Button btnGuardar;
    private TextView txtNombreA;
    private TextView txtDescripcionA;
    private TextView txtPrecioA;
    private Button btnSubirImagen;
    private TextView txtTiempoA;
    private Bitmap photo;

    private ImageView imageView;
    private DatabaseHelper dbHelper;
    private Bitmap selectedImageBitmap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearSalaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearSalaFragment newInstance(String param1, String param2) {
        CrearSalaFragment fragment = new CrearSalaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_sala, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGuardar= view.findViewById(R.id.btnActualizarU);
        txtNombreA = view.findViewById(R.id.txtNombreU);
        txtDescripcionA = view.findViewById(R.id.txtDescripcionU);
        txtPrecioA = view.findViewById(R.id.txtPrecioU);
        txtTiempoA = view.findViewById(R.id.txtTiempoU);
        btnSubirImagen = view.findViewById(R.id.btnSubirImagenU);
        imageView = view.findViewById(R.id.agg_imagenU);

        btnSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirImagen(view);
            }
        });



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(view);
            }
        });
    }

    public void subirImagen(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, //ACTION_GET_CONTENT ACTION_PICK
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccione"),10);

    }



    public void guardar(View view){
        Sala sala= new Sala();
        sala.setNombre(txtNombreA.getText().toString());
        sala.setDescripcion(txtDescripcionA.getText().toString());
        sala.setPrecio(txtPrecioA.getText().length());
        sala.setImagen(bitmap2Bytes(photo));
        sala.setTiempo(txtTiempoA.getText().toString());

        DbSala Dbsala = new DbSala(getContext());
        Dbsala.insertarSala(sala);

        Toast.makeText(getContext(), "Guardado con exito", Toast.LENGTH_SHORT).show();
        txtNombreA.setText("");
        txtDescripcionA.setText("");
        txtPrecioA.setText("");
        txtTiempoA.setText("");
        imageView.setImageBitmap(null);

    }
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();
    }

    // Abrir la galería para seleccionar una imagen

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                Uri uri = data.getData();

                try {
                    photo = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                    System.out.println("hola");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(photo);
            }
        }
    }
}