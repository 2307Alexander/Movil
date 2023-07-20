package com.example.appalquilerdesalas.DataBase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.appalquilerdesalas.R;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alquilerSalas.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void createAdminUser(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("cedula", "0000000000");
        values.put("nombres", "Admin");
        values.put("username", "admin");
        values.put("password", "admin");
        values.put("tipoUser", "admin");

        db.insert("usuarios", null, values);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, cedula TEXT UNIQUE, nombres TEXT, username TEXT, password TEXT, tipoUser TEXT)";
        db.execSQL(createTableQuery);
        createAdminUser(db);

        String createSalasTableQuery = "CREATE TABLE salas " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nombre TEXT ," +
                " descripcion TEXT ," +
                " precio INTEGER ," +
                " imagen NONE ," +
                " tiempo TEXT ," +
                " estado TEXT  DEFAULT '1')";
        db.execSQL(createSalasTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}