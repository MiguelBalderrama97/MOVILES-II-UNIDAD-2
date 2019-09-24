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

        sqLiteDatabase.beginTransaction();
        try{
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Miguel2')");
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Martin2')");
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Ruben2')");

//        SIMULAR UN ERROR
//            int i = 1 / 0;

            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Jorge2')");
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Priscila2')");
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Daniel2')");
            sqLiteDatabase.execSQL("INSERT INTO prueba(algo) VALUES ('Juan2')");
            sqLiteDatabase.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sqLiteDatabase.endTransaction();
        }

//        EL ARGS SIRVE PARA MANDAR ARGUMENTOS EN LA  SQL - CADA DATO DEL ARREGLO ES UN ? EN LA SQL
        String [] args = {"Miguel"};
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM prueba WHERE algo = ?", args);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Log.wtf("cursor",cursor.getString(cursor.getColumnIndex("algo")));
            cursor.moveToNext();
        }
    }
}
