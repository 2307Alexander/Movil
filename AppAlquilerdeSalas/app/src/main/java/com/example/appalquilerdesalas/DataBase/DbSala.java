package com.example.appalquilerdesalas.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appalquilerdesalas.Modelo.Sala;

import java.util.ArrayList;

public class DbSala extends DatabaseHelper {
    private Context context;

    public DbSala(Context context) {
        super(context);
        this.context=context;
    }

    public Long insertarSala(Sala sala){
        long id=0L;

        try{
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",sala.getNombre());
            values.put("descripcion",sala.getDescripcion());
            values.put("precio",sala.getPrecio());
            values.put("imagen", sala.getImagen());
            values.put("tiempo", sala.getTiempo());

            db.insert("salas",null,values);
        }catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<Sala> consultarSala(){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ArrayList<Sala> lSala = new ArrayList<Sala>();
        Sala sala=null;
        Cursor cursorSala=null;
        cursorSala=db.rawQuery("SELECT * FROM "+"salas",null);
        if(cursorSala.moveToFirst()){
            do{
                sala= new Sala();
                sala.setId(cursorSala.getInt(0));
                sala.setNombre(cursorSala.getString(1));
                sala.setDescripcion(cursorSala.getString(2));
                sala.setPrecio(cursorSala.getInt(3));
                sala.setImagen(cursorSala.getBlob(4));
                sala.setTiempo(cursorSala.getString(5));
                lSala.add(sala);

            }while (cursorSala.moveToNext());
        }
        cursorSala.close();
        return lSala;
    }

    public  void actualizarSala(Sala sala){

        try{
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",sala.getNombre());
            values.put("descripcion",sala.getDescripcion());
            values.put("precio",sala.getPrecio());
            values.put("imagen", sala.getImagen());
            values.put("tiempo", sala.getTiempo());
            db.update("salas",
                    values,"id=?",
                    new String[]{sala.getId().toString()} );


        }catch (Exception e){
            e.toString();
        }

    }

}
