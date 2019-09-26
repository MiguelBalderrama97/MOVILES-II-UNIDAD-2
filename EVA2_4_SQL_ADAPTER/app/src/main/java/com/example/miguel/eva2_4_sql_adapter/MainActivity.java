package com.example.miguel.eva2_4_sql_adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    private ListView listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listData = findViewById(R.id.listData);

        sqLiteDatabase = openOrCreateDatabase("bd_adapter", MODE_PRIVATE, null);

        try{
            sqLiteDatabase.execSQL("CREATE TABLE prueba(id INTEGER PRIMARY KEY AUTOINCREMENT, dato TEXT)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TACOS')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TORTAS')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TAMALES')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TLACOYOS')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TOSTADAS')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TAMARINDO')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TOREADOS')");
        sqLiteDatabase.execSQL("INSERT INTO prueba(dato) VALUES ('TORTILLAS')");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM prueba ORDER BY dato",null);
        listData.setAdapter(new MyCursorAdapter(this, cursor));
    }
}
