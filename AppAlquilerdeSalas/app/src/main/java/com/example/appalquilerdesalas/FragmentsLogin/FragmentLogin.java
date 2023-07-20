package com.example.appalquilerdesalas.FragmentsLogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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

import com.example.appalquilerdesalas.DataBase.DatabaseHelper;
import com.example.appalquilerdesalas.MainActivity;
import com.example.appalquilerdesalas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {
    Button Entrar;
    TextView Register, Usuario, Password;
    private DatabaseHelper databaseHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getContext());
        Usuario = view.findViewById(R.id.txt_user);
        Password = view.findViewById(R.id.txt_pass);
        Entrar = view.findViewById(R.id.btn_entrar);
        Register = view.findViewById(R.id.txt_crear);

        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Usuario.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (validateLogin(username, password)) {
                    String userType = getUserTypeFromDatabase(username);

                    if (userType.equals("user")) {
                        // Redirigir a la página para usuarios normales
                        Toast.makeText(getContext(), "Inicio de sesión exitoso (Usuario normal)", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        //finish();
                    } else if (userType.equals("admin")) {
                        // Redirigir a la página para administradores
                        Toast.makeText(getContext(), "Inicio de sesión exitoso (Administrador)", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.fragmentRegistroLogin);
                        //finish();
                    }
                } else {
                    Toast.makeText(getContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Crea cuenta", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.fragmentRegistroLogin);
            }
        });
    }
    private boolean validateLogin(String username, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] columns = {"id"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }

    @SuppressLint("Range")
    private String getUserTypeFromDatabase(String username) {
        String userType = "unknown";
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] columns = {"tipoUser"};
        String selection = "username = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            userType = cursor.getString(cursor.getColumnIndex("tipoUser"));
        }
        cursor.close();

        return userType;
    }
}