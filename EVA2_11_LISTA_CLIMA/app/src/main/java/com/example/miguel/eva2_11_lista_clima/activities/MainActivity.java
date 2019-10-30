/**
 * Created by Miguel Balderrama on 10/24/2019
 * MainActivity.java
 * Copyright © 2019 Miguel Balderrama. All rights reserved.
 */

package com.example.miguel.eva2_11_lista_clima.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.miguel.eva2_11_lista_clima.R;
import com.example.miguel.eva2_11_lista_clima.adapters.ClimaAdapter;
import com.example.miguel.eva2_11_lista_clima.models.Clima;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lstVwCiudades;
    private ClimaAdapter climaAdapter;

    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    private List<Clima> listClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstVwCiudades = findViewById(R.id.lstVwCiudades);
        listClima = new ArrayList<>();

        sqLiteDatabase = openOrCreateDatabase("myDB", MODE_PRIVATE, null);

        readInfoDB();

        climaAdapter = new ClimaAdapter(this, R.layout.list_item, listClima);
        lstVwCiudades.setAdapter(climaAdapter);
    }

    /**
     * Read all the information from the DB and create all the objects
     */
    private void readInfoDB(){
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM ciudades", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String ciudad = cursor.getString(cursor.getColumnIndex("ciudad"));
            String clima = cursor.getString(cursor.getColumnIndex("clima"));
            String desc_clima = cursor.getString(cursor.getColumnIndex("desc_clima"));
            String tempe = cursor.getString(cursor.getColumnIndex("tempe"));
            listClima.add(new Clima(ciudad,clima,desc_clima,tempe,MainActivity.this));
            cursor.moveToNext();
        }
    }

}
