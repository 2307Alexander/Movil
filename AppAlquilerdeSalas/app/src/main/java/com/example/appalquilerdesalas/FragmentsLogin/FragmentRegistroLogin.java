package com.example.appalquilerdesalas.FragmentsLogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appalquilerdesalas.DataBase.DbLogin;
import com.example.appalquilerdesalas.R;
import com.example.appalquilerdesalas.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegistroLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegistroLogin extends Fragment {
    private TextView Volver, CedulaR, NombreR, UsuarioR, PasswordR;
    private Button Crear;
    private DbLogin databaseHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRegistroLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegistroLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegistroLogin newInstance(String param1, String param2) {
        FragmentRegistroLogin fragment = new FragmentRegistroLogin();
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
        return inflater.inflate(R.layout.fragment_registro_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DbLogin(getContext());
        CedulaR = view.findViewById(R.id.txt_cedula);
        NombreR = view.findViewById(R.id.txt_nombres);
        UsuarioR = view.findViewById(R.id.txt_user);
        PasswordR = view.findViewById(R.id.txt_pass);
        Volver = view.findViewById(R.id.txt_volver);
        Crear = view.findViewById(R.id.btn_crear);

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Devuelto al inicio de sesión", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = CedulaR.getText().toString().trim();
                String nombre = NombreR.getText().toString().trim();
                String username = UsuarioR.getText().toString().trim();
                String password = PasswordR.getText().toString().trim();
                String tipoUser = "user";

                if (cedula.isEmpty() || nombre.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresa un nombre de usuario y contraseña válidos", Toast.LENGTH_SHORT).show();
                } else {
                    if (createUser(cedula, nombre, username, password, tipoUser)) {
                        Toast.makeText(getContext(), "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.fragmentLogin);
                    } else {
                        Toast.makeText(getContext(), "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean createUser(String cedula, String nombre, String username, String password, String typeuser) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("cedula", cedula);
        values.put("nombres", nombre);
        values.put("username", username);
        values.put("password", password);
        values.put("tipoUser", typeuser);

        long result = db.insert("usuarios", null, values);
        return result != -1;
    }

    @Override
    public void onDestroy() {
        databaseHelper.close();
        super.onDestroy();
    }
}