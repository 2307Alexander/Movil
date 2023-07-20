package com.example.appalquilerdesalas.FragmentsAdmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appalquilerdesalas.DataBase.DbSala;
import com.example.appalquilerdesalas.Modelo.Sala;
import com.example.appalquilerdesalas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActualizarSalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActualizarSalaFragment extends Fragment {

    public  String nombre;
    public  String descripcion;
    public  Integer precio;
    public  String tiempo;
    public  Integer id;
    public byte imagen;
    public EditText txtNombreU;
    public EditText txtDescripcionU;
    public EditText txtPrecioU;
    public EditText txtTiempoU;
    public EditText imgFotoU;
    public Button btnSubirImagenU;
    public Button btnActualizarU;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    public ActualizarSalaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateSala.
     */
    // TODO: Rename and change types and number of parameters
    public static ActualizarSalaFragment newInstance(String param1, String param2) {
        ActualizarSalaFragment fragment = new ActualizarSalaFragment();
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
            nombre= getArguments().getString("nombre");
            descripcion= getArguments().getString("descripcion");
            precio= getArguments().getInt("precio");
            tiempo= getArguments().getString("tiempo");
            //imagen = getArguments().getByte("imagen");
            id=getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actualizar_sala, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNombreU=view.findViewById(R.id.txtNombreU);
        txtDescripcionU=view.findViewById(R.id.txtDescripcionU);
        txtPrecioU=view.findViewById(R.id.txtPrecioU);
        txtTiempoU=view.findViewById(R.id.txtTiempoU);
        imgFotoU=view.findViewById(R.id.agg_imagenU);
        btnSubirImagenU=view.findViewById(R.id.btnSubirImagenU);
        btnActualizarU=view.findViewById(R.id.btnActualizarU);
        txtNombreU.setText(nombre);
        txtDescripcionU.setText(descripcion);
        txtPrecioU.setText(precio);
        imgFotoU.setText(imagen);
        txtTiempoU.setText(tiempo);

        btnSubirImagenU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subirImagen(view);
            }
        });

        btnActualizarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar(view);
            }
        });


    }

    public void subirImagen(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, //ACTION_GET_CONTENT ACTION_PICK
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccione"),10);

    }
    /**
     Proceso de actualizar
     @autor lPreciado
     @since Marzo/22
     **/
    public void actualizar(View view){
        DbSala Dbsala = new DbSala(getContext());

        Sala sala= new Sala(
        );
        /*Cliente cliente =new Cliente();
        cliente.setId(id);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);*/
        sala.setId(id);
        Dbsala.actualizarSala(sala);
        Toast.makeText(getContext(), "Actualizar Ok", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.nav_catalogoSalas);

    }
}