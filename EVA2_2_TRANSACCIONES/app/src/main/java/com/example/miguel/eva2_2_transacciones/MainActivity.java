package com.example.miguel.eva2_2_transacciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    //HOLA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteDatabase = openOrCreateDatabase("mi_bd", MODE_PRIVATE, null);

        try{
            sqLiteDatabase.execSQL("CREATE TABLE prueba(pruebaid INTEGER PRIMARY KEY AUTOINCREMENT, algo TEXT)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Miguel')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Martin')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Ruben')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Jorge')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Priscila')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Daniel')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Juan')");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM prueba", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Log.wtf("cursor:",cursor.getString(cursor.getColumnIndex("algo")));
            cursor.moveToNext();
        }
    }
}
