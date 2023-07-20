package com.example.appalquilerdesalas.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.appalquilerdesalas.Modelo.Login;

import java.util.ArrayList;

public class DbLogin extends DatabaseHelper {
    private Context context;

    public DbLogin(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public Long insertarUser(Login login){
        long id=0L;

        try{
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("cedula",login.getCedula());
            values.put("nombres",login.getNombres());
            values.put("username",login.getUsername());
            values.put("password",login.getPassword());
            values.put("tipoUser",login.getTipoUser());

            db.insert("usuarios",null,values);
        }catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<Login> consultarLogin(){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ArrayList<Login> lLogin = new ArrayList<Login>();
        Login login=null;
        Cursor cursorLogin=null;
        cursorLogin=db.rawQuery("SELECT * FROM "+"usuarios",null);
        if(cursorLogin.moveToFirst()){
            do{
                login= new Login();
                login.setId(cursorLogin.getInt(0));
                login.setCedula(cursorLogin.getString(1));
                login.setNombres(cursorLogin.getString(2));
                login.setUsername(cursorLogin.getString(3));
                login.setPassword(cursorLogin.getString(4));
                login.setTipoUser(cursorLogin.getString(5));
                lLogin.add(login);

            }while (cursorLogin.moveToNext());
        }
        cursorLogin.close();
        return lLogin;
    }
}